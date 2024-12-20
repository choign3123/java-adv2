package was.httpserver;

import java.io.IOException;

// HttpServlet = Http, Server, Applet(애플릿 / HTTP 서버에서 실행되는 작은 자바 프로그램)의 줄임말
public interface HttpServlet {

    void service(HttpRequest request, HttpResponse response) throws IOException;
}
