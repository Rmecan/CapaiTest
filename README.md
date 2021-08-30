# CapaiTest
[積水樹脂キャップアイシステム](https://www.cap-ai.jp/corporate/)さんのアンサーキットに電文を送るテストプログラム<br>
アドレス`0001`のアンサーキットに対して`12345`を表示させる<br>
アンサーキットには232Cケーブルで接続する<br>
実行時引数としてCOMポートを指定する。指定されていなかったらCOM1を使うようにしている<br>

# How to connect

パソコン<br>
↓232Cケーブル<br>
[コントローラー](https://www.cap-ai.jp/products/controller/rs232c/rs232c_1.html)<br>
↓モジュラーケーブル<br>
[ジャンクションボックス](https://www.cap-ai.jp/products/controller/rs232c/rs232c_2.html)<br>
↓見たことないケーブル<br>
[ダクトエンド](https://www.cap-ai.jp/products/other-machine/construction/construction_4.html)<br>
↓ダクトエンドをダクトベース直接はめ込む<br>
[ダクトベース](https://www.cap-ai.jp/products/other-machine/construction/construction_1.html)<br>
↓アンサーキットをダクトベース直接はめ込む<br>
[アンサーキット](https://www.cap-ai.jp/products/answerkit/steelrack/)<br>

# How to compile
1. `./lib`フォルダを作成する
2. [ここ](http://www.java2s.com/Code/Jar/r/Downloadrxtx217jar.htm)から`RXTXcomm.jar`をダウンロードしてくる
3. `RXTXcomm.jar`を`./lib`フォルダに格納する
4. `compile.bat`を実行する

# How to use
Windows 64bit x Java 64bit の場合
1. [ここ](http://www.java2s.com/Code/Jar/r/Downloadrxtxnativewindowsjar.htm)から`rxtxSerial64.dll`と`rxtxParallel64.dll`をダウンロードしてくる
2. `rxtxSerial64.dll`->`rxtxSerial.dll`、`rxtxParallel64.dll->`rxtxParallel.dll`にそれぞれリネームする
3. system32に`rxtxSerial.dll`と`rxtxParallel.dll`を格納する
4. `./run.bat`を実行する

Windows 64bit x Java 32bit の場合
1. [ここ](http://www.java2s.com/Code/Jar/r/Downloadrxtxnativewindowsjar.htm)から`rxtxSerial.dll`と`rxtxParallel.dll`をダウンロードしてくる
2. SysWOW64に`rxtxSerial.dll`と`rxtxParallel.dll`を格納する
3. `./run.bat`を実行する

Windows 32bit x Java 32bit で実行する場合
1. [ここ](http://www.java2s.com/Code/Jar/r/Downloadrxtxnativewindowsjar.htm)から`rxtxSerial.dll`と`rxtxParallel.dll`をダウンロードしてくる
2. system32に`rxtxSerial.dll`と`rxtxParallel.dll`を格納する
3. `./run.bat`を実行する
