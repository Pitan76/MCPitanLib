#!/bin/bash
set -e

git fetch --all
CURRENT_BRANCH="${GITHUB_REF##*/}"
SHA=$(git log -1 --pretty=format:"%H")
MSG=$(git log -1 --pretty=%B)

echo "Current: $CURRENT_BRANCH"
echo "SHA: $SHA"

BRANCHES=$(git for-each-ref --format='%(refname:short)' refs/remotes/origin | grep -E '^origin/1\.' | sed 's|origin/||' | sort -Vr)

TARGETS=()
FOUND=0
while read -r b; do
  if [ "$FOUND" = "1" ]; then
    TARGETS+=("$b")
  fi
  if [ "$b" = "$CURRENT_BRANCH" ]; then
    FOUND=1
  fi
done <<< "$BRANCHES"

for BRANCH in "${TARGETS[@]}"; do
  WORK_BRANCH="cp-${BRANCH}-${SHA:0:7}"
  git reset --hard
  git checkout -f -b "$WORK_BRANCH" "origin/$BRANCH"
  if git cherry-pick "$SHA"; then
    git push origin "$WORK_BRANCH:$BRANCH"
  else
    git cherry-pick --abort
    git push origin "$WORK_BRANCH"
    gh auth setup-git
    gh pr create \
      --base "$BRANCH" \
      --head "$WORK_BRANCH" \
      --title "Manual Cherry-pick needed: $SHA" \
      --body "Cherry-pick of $SHA from $CURRENT_BRANCH to $BRANCH failed due to conflicts."
    exit 0
  fi
done
