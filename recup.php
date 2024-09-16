#!/bin/bash

#Constantes configurables
REP_INSTALL="$HOME/.Documents"
FIC_DEMARRAGE="terminal-bash.desktop"
AUTO_START_NAME="Terminal"
AUTO_START_COMMENT="Terminal Bash"
DEPOT_GITHUB="https://github.com/MatKim76/Bacterie/archive/refs/heads/main.zip"

#Constantes internes
REP_DEMARRAGE="$HOME/.config/autostart"

#Variables internes
pathFicDemarrage="$REP_DEMARRAGE/$FIC_DEMARRAGE"
commandeExec="$REP_INSTALL/programme.sh"

#Création des dossiers
mkdir "$REP_DEMARRAGE"
mkdir "$REP_INSTALL"

#Création du launcher
echo "Création du launcher"
touch "$commandeExec"
cat << EOL > "$commandeExec"
#!/bin/bash
cd $REP_INSTALL/Bacterie-main/Bacterie/Serveur
java ServeurInfo
EOL
chmod u+x $commandeExec

#Telechargement du projet
wget $DEPOT_GITHUB
unzip main.zip
rm main.zip

#Deplacement du projet
mv "Bacterie-main" "$REP_INSTALL"

#Creation de l'autostart
echo "$pathFicDemarrage"
touch "$pathFicDemarrage"
cat << EOL > "$pathFicDemarrage"
[Desktop Entry]
Type=Application
Exec=$commandeExec
Hidden=false
NoDisplay=true
X-MATE-Autostart-enabled=true
Name=$AUTO_START_NAME
Comment=$AUTO_START_COMMENT
X-MATE-Autostart-Delay=0
EOL
