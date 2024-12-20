package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequest {

    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);

        // 메시지 바디는 이후 처리
    }

    // ex) GET /search?q=hello HTTP/1.1
    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();

        if(requestLine == null) {
            throw new IOException("EOF: No request line received");
        }

        String[] requestParts = requestLine.split(" ");
        if(requestParts.length != 3){
            throw new IOException("Invalid request line: " + requestLine);
        }

        method = requestParts[0];
        String[] pathParts = requestParts[1].split("\\?");
        path = pathParts[0];
        if(1 < pathParts.length){
            parseQueryParameters(pathParts[1]);
        }
    }

    // ex)  Host: localhost:12345
    private void parseQueryParameters(String queryString) {
        for (String param : queryString.split("&")) {
            String[] keyValue = param.split("=");

            String key = URLDecoder.decode(keyValue[0], UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], UTF_8) : "";

            queryParameters.put(key, value);
        }
    }

    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;
        while(!(line = reader.readLine()).isBlank()){
            String[] headerParts = line.split(":");
            String key = headerParts[0].strip();
            String value = headerParts[1].strip();
            headers.put(key, value);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name){
        return queryParameters.get(name);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}
