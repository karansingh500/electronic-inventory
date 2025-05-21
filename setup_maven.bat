@echo off
setx MAVEN_HOME "C:\Program Files\Apache\maven"
setx PATH "%PATH%;%MAVEN_HOME%\bin"
echo Maven environment variables have been set up.
echo Please restart your command prompt and run 'mvn -version' to verify the installation.
pause 