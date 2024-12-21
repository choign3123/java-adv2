package annotation.basic;

@AnnoMeta // TYPE에 적용
public class MetaData {

    // @AnnoMeta // FIELD에 적용 - 컴파일 오류
    private String id;

    @AnnoMeta
    public void call(){
        System.out.println("MetaData.call");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnoMeta typeAnno = MetaData.class.getAnnotation(AnnoMeta.class);
        System.out.println("typeAnno = " + typeAnno);

        AnnoMeta methodAnno = MetaData.class.getDeclaredMethod("call")
                                            .getAnnotation(AnnoMeta.class);
        System.out.println("methodAnno = " + methodAnno);
    }
}
