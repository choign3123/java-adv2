package annotation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for(Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);

            // NotEmpty
            if(field.isAnnotationPresent(NotEmpty.class)) {
                String value = (String) field.get(obj);
                NotEmpty annotation = field.getAnnotation(NotEmpty.class);

                if(value == null || value.isBlank()){
                    throw new RuntimeException(annotation.message());
                }
            }

            // Range
            if(field.isAnnotationPresent(Range.class)) {
                long value = field.getLong(obj);
                Range annotation = field.getAnnotation(Range.class);

                if(value < annotation.min() || 999 < annotation.max()){
                    throw new RuntimeException(annotation.message());
                }
            }
        }
    }
}
