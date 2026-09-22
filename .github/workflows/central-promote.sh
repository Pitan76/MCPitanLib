#!/usr/bin/env bash
# OSSRH Staging API に deploy 済みの staging リポジトリを Central Portal へ引き渡す。
#
# gradlew publishAllPublicationsToSonatypeRepository は staging に置くところまでしかやらず、
# そのままだと Portal の Deployments にも現れない。この引き渡しPOSTまでが1セット。
#
# staging の default repository は (アカウント, 送信元IP, namespace) で決まるため、
# deploy したのと同じホストから実行すること。別ジョブ/別ランナーからでは見つからない。
#
# 必要な環境変数: SONATYPE_USERNAME, SONATYPE_PASSWORD
# PUBLISHING_TYPE=automatic  … バリデーション後そのまま公開 (既定)
# PUBLISHING_TYPE=user_managed … Portal UI の Publish ボタン待ちで止める
set -euo pipefail

API="https://ossrh-staging-api.central.sonatype.com"
NAMESPACE="${1:-net.pitan76}"
PUBLISHING_TYPE="${PUBLISHING_TYPE:-automatic}"

: "${SONATYPE_USERNAME:?SONATYPE_USERNAME is not set}"
: "${SONATYPE_PASSWORD:?SONATYPE_PASSWORD is not set}"

token="$(printf '%s:%s' "$SONATYPE_USERNAME" "$SONATYPE_PASSWORD" | base64 -w0)"

api() {
  curl -sS --fail-with-body -H "Authorization: Bearer $token" "$@"
}

echo "--- open staging repositories ---"
repos="$(api "$API/manual/search/repositories?state=open")"
echo "$repos"

if ! echo "$repos" | grep -q "$NAMESPACE"; then
  echo "no open staging repository for $NAMESPACE; nothing to promote" >&2
  exit 1
fi

# warnings が出ているものは Portal 側で弾かれるので先に止める
if echo "$repos" | grep -qE '"warnings":\[[^]]'; then
  echo "staging repository has warnings; aborting" >&2
  exit 1
fi

echo "--- promoting $NAMESPACE (publishing_type=$PUBLISHING_TYPE) ---"
api -X POST "$API/manual/upload/defaultRepository/${NAMESPACE}?publishing_type=${PUBLISHING_TYPE}"
echo

echo "--- result ---"
api "$API/manual/search/repositories?state=open" || true
echo
echo "https://central.sonatype.com/publishing/deployments で状態を確認できます"
