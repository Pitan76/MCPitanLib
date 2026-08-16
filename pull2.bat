@echo off
echo Fetching all branches from origin...
git fetch origin

echo Updating tracking branches without checkout...
git fetch origin 1.26.2:1.26.2
git fetch origin 1.26.1:1.26.1
git fetch origin 1.21.11:1.21.11
git fetch origin 1.21.9:1.21.9
git fetch origin 1.21.6:1.21.6
git fetch origin 1.21.5:1.21.5
git fetch origin 1.21.4:1.21.4
git fetch origin 1.21.3:1.21.3
git fetch origin 1.21.1:1.21.1
git fetch origin 1.20.4:1.20.4
git fetch origin 1.20.1:1.20.1
git fetch origin 1.19.2:1.19.2
git fetch origin 1.18.2:1.18.2
git fetch origin 1.16.5:1.16.5

echo.
echo Pulling current branch...
git pull

pause
