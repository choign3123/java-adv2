package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandler implements Runnable{

    private final Socket socket;
    private final ServletManager servletManager;

    public HttpRequestHandler(Socket socket, ServletManager servletManager) {
        this.socket = socket;
        this.servletManager = servletManager;
    }


    @Override
    public void run() {
        try {
            process();
        }
        catch (Exception e) {
            log(e);
            e.printStackTrace();
        }
    }

    private void process() throws IOException {
        try (socket;
             InputStreamReader isr = new InputStreamReader(socket.getInputStream(), UTF_8);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)
        ) {

            HttpRequest request = new HttpRequest(reader);
            log("HTTP 요청 정보 출력");
            System.out.println(request);

            HttpResponse response = new HttpResponse(writer);
            servletManager.execute(request, response);
            response.flush();
            log("HTTP 응답 전달 완료");
        }
    }
}
