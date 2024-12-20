package reflection;

import reflection.data.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MethodV3 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        System.out.println("호출 메서드: ");
        String methodName = sc.nextLine();

        System.out.println("숫자1: ");
        int num1 = sc.nextInt();

        System.out.println("숫자2: ");
        int num2 = sc.nextInt();

        Calculator calculator = new Calculator();

        // 호출할 메서드를 methodName 변수로 동적 선택
        Class<Calculator> calculatorClass = Calculator.class;
        Method method = calculatorClass.getDeclaredMethod(methodName, int.class, int.class);
        Object returnValue = method.invoke(calculator, num1, num2);
        System.out.println("returnValue = " + returnValue);
    }
}
