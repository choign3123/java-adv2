package was.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpServerV1 {

    private final int port;

    public HttpServerV1(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 port: " + port);

        while(true) {
            Socket socket = serverSocket.accept();
            process(socket);
        }
    }

    private void process(Socket socket) throws IOException {
        try (socket;
             InputStreamReader isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8)
        ) {

            String requestMsg = requestToString(reader);

            // 파비콘 요청 무시
            if(requestMsg.contains("/favicon.ico")){
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println(requestMsg);

            log("HTTP 응답 생성중...");
            sleep(5000); // 임의 서버 처리 시간 부과
            responseToClient(writer);

            log("HTTP 응답 전달 완료");
        }
    }

    private void responseToClient(PrintWriter writer) {
        String body = "<h1>Hello World</h1>";
        int length = body.getBytes(StandardCharsets.UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(sb);
        writer.flush();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String requestToString(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = br.readLine()) != null){
            if(line.isEmpty()) {
                break; // body 빼고 받기
            }
            sb.append(line)
                    .append("\n");
        }

        return sb.toString();
    }
}
