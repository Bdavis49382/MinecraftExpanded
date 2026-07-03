@echo off
REM Stop any existing Gradle daemons and run the Fabric client from the project root.
cd /d "%~dp0"
call gradlew.bat --stop
call gradlew.bat runClient --console=plain
