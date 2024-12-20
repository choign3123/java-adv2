package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<BasicData> basicDataClass = BasicData.class;

        Constructor<?> constructor = basicDataClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Object instance = constructor.newInstance("hello");
        System.out.println("instance = " + instance);

        Method method = basicDataClass.getDeclaredMethod("call");
        method.invoke(instance);
    }
}
