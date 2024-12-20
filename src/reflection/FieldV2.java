package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "가나", 23);
        System.out.println("기존 이름 = " + user.getName());

        Class<? extends User> userClass = user.getClass();
        Field nameField = userClass.getDeclaredField("name");

        // private 필드에 접근 허용
        // private 메소드도 동일한 방식으로 호출 가능
        nameField.setAccessible(true);

        nameField.set(user, "다라");
        System.out.println("변경되 이름 = " + user.getName());
    }
}
