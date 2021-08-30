import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

class Test {
    public static void main(String[] arg) {
        String targetComPort = "COM1";
        if (arg != null && arg.length > 0){
            targetComPort = arg[0];
        }

        outputLog("/_/_/_/_/_/_/_/_/_/_/_/_");
        outputLog(targetComPort + "のテスト");
        outputLog("アドレス0001に12345を表示する");
        outputLog("/_/_/_/_/_/_/_/_/_/_/_/_");

        new Test().doStart(targetComPort);
    }

    /**
     * テスト開始
     * @param targetComPort COMポート
     */
    void doStart(String targetComPort) {
        SerialPort serialport = null;
        try {
            serialport = openComPort(targetComPort);
            if (serialport == null) return;
            // DTRをOFFにする。
            serialport.setDTR(false);
            // 入力/出力ストリームを取得
            try (InputStream in = serialport.getInputStream();
                 OutputStream out = serialport.getOutputStream();) {
                // 通信バッファ内のデータをすべてクリアする。
                clearBuffer(in);
                // DTRをONにする。
                serialport.setDTR(true);
                // 初期データ送信
                outputLog("初期データ送信を試みる");
                send(out, "Z");
                // テストデータ送信
                outputLog("テストデータ送信を試みる");
                String command = "P2";
                String address = "0001";
                String display = "12345";
                String message = command + address + display;
                send(out, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serialport != null) {
                serialport.close();
            }
        }
    }

    /**
     * 表示器に電文を送信する。
     * @param message 送信電文
     * @throws IOException ファイルI/Oエラーが発生した場合に通知されます。
     */
    void send(OutputStream out, String message) throws IOException {
        String logProcName = "電文送信処理";
        String logParam = "電文内容=" + message;
        try {
            outputLog(logProcName + " 開始:" + logParam);

            byte[] messageBytes = message.getBytes("iso-8859-1");

            //電文本体を送信
            out.write(messageBytes, 0, messageBytes.length);
            if (messageBytes.length > 1) {
                out.write(getCheckSum(messageBytes, messageBytes.length));
            }
            out.write(0x0D); // CRを付与
            out.flush();

            outputLog(logProcName + " 正常終了:" + logParam);
        } catch (Exception e) {
            outputLog(logProcName + " 失敗:" + logParam);
            e.printStackTrace();
        }
    }

    /**
     * 対象のCOMポート開く（通信可能にする）
     * @param comPort
     * @return
     */
    SerialPort openComPort(String comPort) {
        String logProcName = "openComPort";
        String logParam = "comPort=" + comPort;
        try {
            outputLog(logProcName + " 開始:" + logParam);
            // RS232Cポートをオープンする。
            CommPortIdentifier RSport = CommPortIdentifier.getPortIdentifier(comPort);
            // ユニークな名前でシリアルポートを開く
            String UniquePortName = "Test-" + comPort;
            SerialPort serialport = (SerialPort) RSport.open(UniquePortName, 30000);
            // シリアルポートのパラメータを設定
            serialport.setSerialPortParams(
                    9600,                         // Baudrate
                    SerialPort.DATABITS_8,        // Data Bits
                    SerialPort.STOPBITS_1,        // Stop Bits
                    SerialPort.PARITY_NONE);      // Parity Bit
            // フロー制御を設定する。
            serialport.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            outputLog(logProcName + " 正常終了:" + logParam);
            return serialport;
        } catch (Exception e) {
            outputLog(logProcName + " 失敗:" + logParam);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RS-232Cバッファをクリアする。
     * @throws IOException
     */
    void clearBuffer(InputStream in) throws IOException {
        if (!Optional.of(in).isPresent()) {
            return;
        }
        byte ret = 0;
        while (true) {
            ret = (byte) in.read();
            if (ret == -1) {
                break;
            }
        }
    }

    /**
     * チェックサムを計算する。
     * @param checkString チェックサム計算文字列
     * @param length 文字列長
     */
    int getCheckSum(byte[] checkString, int length) {
        int checkSum = 0;

        // チェックサムの計算
        for (int i = 0; i < length; ++i) {
            //４ビット単位に分割
            int highVal = (checkString[i] >> 4) & 0x0F;
            int lowVal = checkString[i] & 0x0F;
            // ０～９までに丸める
            if (highVal >= 10){
                highVal = highVal - 10;
            }
            if (lowVal >= 10){
                lowVal = lowVal - 10;
            }
            // チェックサムを加算
            checkSum += (highVal + lowVal);
        }

        // ０～９までに丸める
        checkSum %= 10;

        return (checkSum + 0x30);
    }

    /**
     * ログに吐き出すときはここをいじる
     * @param str 出力する文字
     */
    static void outputLog(String str) {
        System.out.println(str);
    }
}