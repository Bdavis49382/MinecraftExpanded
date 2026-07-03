@echo off
setlocal

set APP_BASE_NAME=%~n0

set DEFAULT_JVM_OPTS=-Xmx1g

set DIRNAME=%~dp0
set APP_HOME=%DIRNAME%
set CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar

if defined JAVA_HOME goto findJavaFromJavaHome
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.11

:findJavaFromJavaHome
if exist "%JAVA_HOME%\bin\java.exe" (
    set JAVACMD=%JAVA_HOME%\bin\java.exe
) else (
    set JAVACMD=java
)

if not exist "%JAVACMD%" (
    echo ERROR: JAVA_HOME is not set and no java executable could be found in PATH.
    exit /b 1
)

"%JAVACMD%" %DEFAULT_JVM_OPTS% -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
