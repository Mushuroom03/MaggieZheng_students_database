javac -d bin -cp lib\postgresql-42.7.8.jar src\App.java src\PostgreConnection.java

jar cfm App.jar META-INF\MANIFEST.MF -C bin .

