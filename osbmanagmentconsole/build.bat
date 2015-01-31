if exist build.log del build.log
tools\apache-maven-3.2.5\bin\mvn clean | tools\wtee.exe clean.log 
tools\apache-maven-3.2.5\bin\mvn  install | tools\wtee.exe build.log 
pause 
