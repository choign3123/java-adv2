package annotation.basic.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited // 해당 어노테이션을 적용한 클래스 상속 시, 자식에게도 이 어노테이션이 적용됨 (인터페이스는 상속시에도 적용 X)
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {
}
