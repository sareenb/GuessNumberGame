@echo off
set local
#needs java 8 set java_home
#set JAVA_HOME =
mvn clean package exec:java
