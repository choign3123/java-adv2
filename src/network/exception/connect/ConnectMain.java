package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {

    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();
        connectionRefused();
    }

    // 호스트를 알 수 없음
    private static void unknownHostEx1() throws IOException {
        try {
            Socket socket = new Socket("999.999.999.999", 80);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // 호스트를 알 수 없음
    private static void unknownHostEx2() throws IOException {
        try {
            Socket socket = new Socket("google.gogo", 80);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // 연결이 거절됨
    private static void connectionRefused() throws IOException {
        try {
            Socket socket = new Socket("localhost", 45678); // 미사용 포트
        }
        catch (ConnectException e) {
            // ConnectException: Connection refused
            e.printStackTrace();
        }
    }
}
