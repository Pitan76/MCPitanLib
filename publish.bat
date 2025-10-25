git fetch origin

git checkout 1.20.4
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenForgePublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :forge:modrinth
call gradlew.bat :neoforge:modrinth

git checkout 1.16.5
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :forge:modrinth

git checkout 1.18.2
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :forge:modrinth

git checkout 1.19.2
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :forge:modrinth

git checkout 1.20.1
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :forge:modrinth

git checkout 1.21.1
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :neoforge:modrinth

git checkout 1.21.3
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :neoforge:modrinth

git checkout 1.21.4
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :neoforge:modrinth

git checkout 1.21.5
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :neoforge:modrinth

git checkout 1.21.6
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :neoforge:modrinth


git checkout 1.21.9
timeout /T 1
call gradlew.bat build
call gradlew.bat publish publishMavenCommonPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenFabricPublicationToGitHubPackagesRepository
call gradlew.bat publish publishMavenNeoForgePublicationToGitHubPackagesRepository
call gradlew.bat curseforge
call gradlew.bat :fabric:modrinth
call gradlew.bat :neoforge:modrinth

pause