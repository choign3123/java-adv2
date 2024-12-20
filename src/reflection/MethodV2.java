package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 정적 메소드 호출 - 일반적인 메서드 호출
        BasicData helloInstance = new BasicData();
        helloInstance.call(); // 이 부분을 변경하지 않는 이상 정적이다.

        // 동적 메소드 호출 - 리플렉션 사용
        Class<? extends BasicData> helloClass = helloInstance.getClass();
        String methodName = "hello";

        // methodName 변경시 호출되는 메소드를 변경할 수 있음
        Method method = helloClass.getDeclaredMethod(methodName, String.class);
        Object returnValue = method.invoke(helloInstance, "hi");
        System.out.println("returnValue = " + returnValue);
    }
}
