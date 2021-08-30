@rem @chcp 65001
@echo off
@cd %~dp0


rem =========
rem 環境変数のセット
rem =========
set CLASSPATH=./lib/RXTXcomm.jar;

rem =========
rem java実行
rem =========
if not exist ".\classes" mkdir ".\classes"
copy /Y ".\lib\*.dll" ".\classes"
copy /Y ".\lib\*.jar" ".\classes"
javac -cp "%CLASSPATH%" -d "./classes" -encoding utf8 "./src/Test.java"
if %errorlevel% neq 0 pause

@endlocal
@exit /b