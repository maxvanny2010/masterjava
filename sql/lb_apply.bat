set LB_HOME=C:\apps\masterjava\liquibase
call %LB_HOME%\liquibase.bat --driver=org.postgresql.Driver ^
--classpath=%LB_HOME%\lib ^
--changeLogFile=databaseChangeLog.sql ^
--url="jdbc:postgresql://localhost:5432/masterjavaHW" ^
--username=postgres ^
--password=password ^
migrate