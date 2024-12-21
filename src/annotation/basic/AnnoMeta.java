package annotation.basic;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // CLASS 타입으로 적용해서도 MetaData 실행시켜보기
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface AnnoMeta {
}
