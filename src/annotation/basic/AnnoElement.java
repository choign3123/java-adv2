package annotation.basic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import reflection.data.BasicData;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {

    String value();
    int count() default 0;
    String[] tags() default {};

    Class<? extends BasicData> basicData() default BasicData.class; // BasicData는 불가능하나, 클래스 정보는 가능

    // 정의할 수 있는 데이터 타입
    // - 기본 타입
    // - String
    // - Class(메타데이터) 또는 인터페이스
    // - enum
    // - 다른 어노테이션 타입
    // - 위 타입들의 배열
}
