package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Constructor;

public class ConstructV1 {

    public static void main(String[] args) throws NoSuchMethodException {
        Class<BasicData> basicDataClass = BasicData.class;

        System.out.println("===== constructors() =====");
        Constructor<?>[] constructors = basicDataClass.getConstructors();
        for(Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("===== declaredConstructors() =====");
        Constructor<?>[] declaredConstructors = basicDataClass.getDeclaredConstructors();
        for(Constructor<?> constructor : declaredConstructors){
            System.out.println(constructor);
        }
    }
}
