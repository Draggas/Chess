@echo off
REM Vérifier si Maven est installé
where mvn >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Maven n'est pas installé. Veuillez l'installer et réessayer.
    exit /b 1
)

REM Aller dans le répertoire du projet
cd /d %~dp0

REM Construire le projet
mvn clean install
IF %ERRORLEVEL% NEQ 0 (
    echo La construction a échoué. Vérifiez les erreurs ci-dessus.
    exit /b 1
)

echo La compilation a réussi !