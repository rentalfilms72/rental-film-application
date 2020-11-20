#Initialisez un nouveau dépôt GIT local que nous allons pousser plus tard vers le distant :
#@echo "Initialisation of local repository GIT..."
git init

#Ajoutez les fichiers du dossier au GIT :
#@echo "Adding files on repository..."
git add .

#Ajoutez l'URL du dépôt distant à celui-ci :
#@echo "adding url of distant repository"
git remote add origin https://rentalfilms72:Programmation2020@github.com/rentalfilms72/rental-film-application

#Faites un commit du contenu :
#@echo "Our first commit..."
git commit -m "First commit"

#Poussez enfin le tout vers le dépôt distant :
#@echo "pushing..."
git push -u origin master