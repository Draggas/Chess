#!/bin/bash

# Vérifiez si Maven est installé
if ! command -v mvn &> /dev/null
then
    echo "Maven n'est pas installé. Veuillez installer Maven et réessayer."
    exit
fi

# Construisez le projet
mvn clean install

# Vérifiez si la construction a réussi
if [ $? -ne 0 ]; then
    echo "La construction a échoué. Vérifiez les erreurs ci-dessus."
    exit 1
fi

# Exécutez le projet avec le plugin JavaFX
mvn javafx:run