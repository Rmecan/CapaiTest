@rem @chcp 65001
@echo off
@cd %~dp0


rem =========
rem ���ϐ��̃Z�b�g
rem =========
set CLASSES=%~dp0classes
set CLASSPATH=%CLASSES%/RXTXcomm.jar;%CLASSES%;

rem =========
rem java���s
rem =========
for %%i in (COM1 COM2 COM3 COM4 COM5 COM6) do (
  start "%%i�̃e�X�g" cmd /k "java -cp "%CLASSPATH%" Test %%i & pause"
)

@endlocal
@exit /b
