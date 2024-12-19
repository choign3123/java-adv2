package network.tcp.autocloseable;

public class ResourceV1 {

    private String name;

    public ResourceV1(String name) {
        this.name = name;
    }

    // 일반 요청 처리
    public void call() {
        System.out.println(name + " call");
    }

    // 일반 요청 처리 중 비정상 종료
    public void callEx() throws CallException {
        System.out.println(name + " callEx");
        throw new CallException(name + " ex");
    }

    // 자원 정리
    public void close() {
        System.out.println(name + " close");
    }

    // 자원 정리 중 비정상 종료
    public void closeEx() throws CloseException {
        System.out.println(name + " closeEx");
        throw new CloseException(name + " ex");
    }
}
