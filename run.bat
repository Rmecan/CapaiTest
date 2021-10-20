@rem @chcp 65001
@echo off
@cd %~dp0


rem =========
rem 環境変数のセット
rem =========
set CLASSES=%~dp0classes
set CLASSPATH=%CLASSES%/RXTXcomm.jar;%CLASSES%;

rem =========
rem java実行
rem =========

rem *** usage ***
rem 
rem Test [COMポート] [アドレス] [表示内容]
rem 
rem Parameter
rem ----------------------------------------------------------
rem [COMポート]   接続しているCOMポートを指定する。
rem               指定できるポートは`RXTXcomm.jar`に準拠する。
rem               省略時、`COM1`が設定される。
rem 
rem [アドレス]    テストするアンサーキットを指定する。
rem               指定できるアドレスはアンサーキットの仕様に準拠する。
rem               省略時、`0001`が設定される。
rem 
rem [表示内容]    アンサーキットに表示する内容を指定する。
rem               表示できる内容はアンサーキットの仕様に準拠する。
rem               省略時、`12345`が設定される。


java -cp "%CLASSPATH%" Test COM3 5391 11223
if %errorlevel% neq 0 pause

@endlocal
@exit /b
