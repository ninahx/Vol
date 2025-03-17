@echo off
setlocal


@REM temp folder
set "temp=C:\Users\Userr\Documents\temp_MVC"

@REM lib folder
set "lib=%cd%\lib"
 
@REM web.xml file                          
set "xml=%cd%\web.xml" 

@REM dispatcher file
set "dispatcher=%cd%\WEB-INF\dispatcherServlet.xml"

@REM web folder   
set "web=%cd%\pages"  

@REM SRC folder
set "src=%cd%\src"

@REM assets folder   
set "assets=%cd%\assets"  

@REM webapps folder
set "webapps=C:\apache-tomcat-10.0.27\webapps"

@REM tags folder
set "tags=%cd%\tags"



@REM Supprime le dossier temporaire 
if exist "%temp%" rmdir /s /q "%temp%"
echo Dossier temporaire supprimé. 

@REM Recrée le dossier temporaire
mkdir "%temp%"

@REM Copie lib vers WEB-INF/lib
xcopy /s /e /y "%lib%" "%temp%\WEB-INF\lib\"

@REM Copie web vers le dossier temporaire
xcopy /s /e /y "%web%" "%temp%"

@REM Copie xml vers WEB-INF
copy /Y "%xml%" "%temp%\WEB-INF\"

@REM Copie dispatcher vers WEB-INF
copy /Y "%dispatcher%" "%temp%\WEB-INF\"

@REM Copie le dossier tags dans WEB-INF
xcopy /s /e /y "%tags%" "%temp%\WEB-INF\tags\"

@REM Copie les fichiers assets dans le dossier temporaire
xcopy /s /e /y "%assets%" "%temp%\assets\"

@REM Crée le dossier classes dans WEB-INF
mkdir "%temp%\WEB-INF\classes\"

@REM Compiler les fichiers .java
for /R "%src%" %%f in (*.java) do (
    javac -cp "%lib%\*;%src%" -d "%temp%\WEB-INF\classes" "%%f"
)

@REM Récupérer le nom du dossier actuel
for %%i in ("%cd%") do set "myfolder=%%~nxi"

@REM Créer un fichier .war à partir du dossier temp
jar -cvf "%myfolder%.war" -C "%temp%" .

@REM Déplacer le fichier .war vers le dossier webapps de Tomcat
move "%myfolder%.war" "%webapps%"

endlocal
