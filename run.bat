@echo off
REM Vérifier si le fichier JAR existe
IF NOT EXIST "target\Chess-2.0-SNAPSHOT.jar" (
    echo Le fichier JAR n'a pas été trouvé. Veuillez d'abord compiler le projet avec compile.bat.
    exit /b 1
)

REM Aller dans le répertoire du projet
cd /d %~dp0

REM Exécuter le projet
java -jar "target\Chess-2.0-SNAPSHOT.jar"
IF %ERRORLEVEL% NEQ 0 (
    echo L'exécution du projet a échoué. Vérifiez les erreurs ci-dessus.
    exit /b 1
)

echo Le jeu a démarré avec succès !
