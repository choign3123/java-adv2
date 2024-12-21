package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {

    public static void main(String[] args) {
        TestController controller = new TestController();

        Class<? extends TestController> controllerClass = controller.getClass();
        for(Method method : controllerClass.getDeclaredMethods()){
            SimpleMapping annotation = method.getAnnotation(SimpleMapping.class);

            if(annotation != null){
                System.out.printf("[%s] -> %s\n", annotation.value(), method.getName());
            }
        }
    }
}
