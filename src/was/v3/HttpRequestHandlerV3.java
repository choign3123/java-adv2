package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(Socket socket) throws IOException {
        try (socket;
             InputStreamReader isr = new InputStreamReader(socket.getInputStream(), UTF_8);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)
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
            if(requestMsg.startsWith("GET /site1")){
                site1(writer);
            } else if (requestMsg.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestMsg.startsWith("GET /search")) {
                search(writer, requestMsg);
            } else if (requestMsg.startsWith("GET /")) {
                home(writer);
            } else {
                notFound(writer);
            }

            log("HTTP 응답 전달 완료");
        }
    }

    private static void home(PrintWriter writer) {
        // v3에서는 편의를 위해 length 헤더 미포함
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>home</h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'>site1</a></li>");
        writer.println("<li><a href='/site2'>site2</a></li>");
        writer.println("<li><a href='/search?q=hello'>검색</a></li>");
        writer.println("</ul>");
        writer.flush();
    }

    private static void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex+2);
        String query = requestString.substring(startIndex+2, endIndex);
        String decode = URLDecoder.decode(query, UTF_8);

        // v3에서는 편의를 위해 length 헤더 미포함
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>Search</h1>");
        writer.println("<ul>");
        writer.println("<li>query: " + query + "</li>");
        writer.println("<li>decode: " + decode + "</li>");
        writer.println("</ul>");
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        // v3에서는 편의를 위해 length 헤더 미포함
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>site2</h1>");
        writer.flush();
    }

    private void site1(PrintWriter writer) {
        // v3에서는 편의를 위해 length 헤더 미포함
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>site1</h1>");
        writer.flush();
    }

    private static void notFound(PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>404 페이지를 찾을 수 없습니다.</h1>");
        writer.flush();
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