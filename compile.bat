@rem @chcp 65001
@echo off
@cd %~dp0


rem =========
rem 環境変数のセット
rem =========
set CLASSPATH=./src/RXTXcomm.jar;

rem =========
rem java実行
rem =========
if not exist ".\classes" mkdir ".\classes"
copy /Y ".\src\*.dll" ".\classes"
copy /Y ".\src\*.jar" ".\classes"
javac -cp "%CLASSPATH%" -d "./classes" -encoding sjis "./src/Test.java"
if %errorlevel% neq 0 pause

@endlocal
@exit /b
