package network.tcp.autocloseable;

public class ResourceCloseMainV2 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            e.printStackTrace();
        } catch (CloseException e) {
            // final문에서 closeEx() 호출로 인해 main에서 CloseException이 처리되며,
            // 핵심 예외인 CallException에 대한 예외처리가 되지 않음
            System.out.println("CloseException 예외 처리");
            e.printStackTrace();
        }
    }

    private static void logic() throws CloseException, CallException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx();
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e; // 핵심적인 예외처리는 main에서 수행
        } finally {
            if(resource2 != null) {
                resource2.closeEx();
            }
            if(resource1 != null) {
                resource1.closeEx();
            }
        }
    }
}
