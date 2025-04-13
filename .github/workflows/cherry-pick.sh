#!/bin/bash
set -e

git fetch --all
CURRENT_BRANCH="${GITHUB_REF##*/}"
SHA=$(git log -1 --pretty=format:"%H")
MSG=$(git log -1 --pretty=%B)

echo "Current: $CURRENT_BRANCH"
echo "SHA: $SHA"

BRANCHES=$(git for-each-ref --format='%(refname:short)' refs/remotes/origin | grep -E '^origin/1\.' | sed 's|origin/||' | sort -Vr)

TARGETS=[]
FOUND=0
while read -r b; do
  if [ "$FOUND" = "1" ]; then
    TARGETS+=("$b")
  fi
  if [ "$b" = "$CURRENT_BRANCH" ]; then
    FOUND=1
  fi
done <<< "$BRANCHES"

for TARGET_BRANCH in "${TARGETS[@]}"; do
  if [ -z "$TARGET_BRANCH" ]; then
    continue
  fi

  echo "Trying to cherry-pick to $TARGET_BRANCH..."

  git reset --hard
  git switch -c "$TARGET_BRANCH" "origin/$TARGET_BRANCH"

  echo "Cherry-picking $SHA from $CURRENT_BRANCH to $TARGET_BRANCH..."
  if git cherry-pick "$SHA"; then
    git push origin "$TARGET_BRANCH"
    echo "Cherry-pick successful. Pushed to $TARGET_BRANCH."
    continue
  fi

  echo "Cherry-pick failed. Attempting to resolve conflicts..."

  git cherry-pick --abort
  gh auth setup-git
  gh pr create \
    --base "$TARGET_BRANCH" \
    --head "$CURRENT_BRANCH" \
    --title "Manual Cherry-pick needed: $SHA" \
    --body "Cherry-pick of $SHA from $CURRENT_BRANCH to $TARGET_BRANCH failed due to conflicts."
  exit 0
done