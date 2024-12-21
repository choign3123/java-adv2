package annotation.validator;

import static util.MyLogger.log;

public class ValidatorV1Main {

    public static void main(String[] args) {
        User user = new User("가나", 0);
        Team team = new Team("", 0);

        try {
            log("===== user 검증 =====");
            validateUser(user);
        } catch (Exception e) {
            log(e);
        }

        try {
            log("===== team 검증 =====");
            validateTeam(team);
        } catch (Exception e) {
            log(e);
        }
    }

    private static void validateTeam(Team team) {
        if(team.getName() == null || team.getName().isBlank()) {
            throw new RuntimeException("이름이 비었습니다");
        }
        if(team.getMemberCount() < 1 || 999 < team.getMemberCount()){
            throw new RuntimeException("회원수는 1~999 벙위여야 합니다");
        }
    }

    private static void validateUser(User user) {
        if(user.getName() == null || user.getName().isBlank()) {
            throw new RuntimeException("이름이 비었습니다");
        }
        if(user.getAge() < 1 || 100 < user.getAge()){
            throw new RuntimeException("회원수는 1~100 벙위여야 합니다");
        }
    }
}
