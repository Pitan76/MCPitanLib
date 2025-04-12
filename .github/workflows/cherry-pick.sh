#!/bin/bash
set -e

git fetch --all
CURRENT_BRANCH="${GITHUB_REF##*/}"
SHA=$(git log -1 --pretty=format:"%H")
MSG=$(git log -1 --pretty=%B)

echo "Current: $CURRENT_BRANCH"
echo "SHA: $SHA"

BRANCHES=$(git for-each-ref --format='%(refname:short)' refs/remotes/origin | grep -E '^origin/1\.' | sed 's|origin/||' | sort -Vr)

TARGET_BRANCH=""
FOUND=0
while read -r b; do
  if [ "$FOUND" = "1" ]; then
    TARGET_BRANCH="$b"
    break
  fi
  if [ "$b" = "$CURRENT_BRANCH" ]; then
    FOUND=1
  fi
done <<< "$BRANCHES"

if [ -z "$TARGET_BRANCH" ]; then
  echo "No lower branch found. Done."
  exit 0
fi

echo "Trying to cherry-pick to $TARGET_BRANCH..."

git reset --hard
git checkout -f "origin/$TARGET_BRANCH"

echo "Cherry-picking $SHA from $CURRENT_BRANCH to $TARGET_BRANCH..."
if git cherry-pick "$SHA"; then
  git push origin "$TARGET_BRANCH"
  echo "Cherry-pick successful. Pushed to $TARGET_BRANCH."
  exit 0
fi
git cherry-pick --abort
gh auth setup-git
gh pr create \
  --base "$TARGET_BRANCH" \
  --head "$CURRENT_BRANCH" \
  --title "Manual Cherry-pick needed: $SHA" \
  --body "Cherry-pick of $SHA from $CURRENT_BRANCH to $TARGET_BRANCH failed due to conflicts."
