package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: "  + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // 서버 -> 클라이언트: FIN
        Thread.sleep(1000); // 서버가 close()를 호출할 때까지 잠시 대기

        // 서버 -> 클라이언트: PUSH[1]
        output.write(1);

        // 서버 -> 클라이언트: RST
        Thread.sleep(1000); // RST 메시지 수신 대기

        try {
            // RST를 수신했음에도 데이터 읽기
            // SocketException의 Connection reset 발생
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            // RST를 수신했음에도 데이터 쓰기
            // SocketException의 Broken pipe 발생
            output.write(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
