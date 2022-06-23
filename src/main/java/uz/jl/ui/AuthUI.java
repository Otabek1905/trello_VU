package uz.jl.ui;


import uz.jl.BaseUtils;
import uz.jl.Colors;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.enums.AuthRole;
import uz.jl.enums.TestLevel;
import uz.jl.service.auth.AuthAnswerService;
import uz.jl.service.auth.AuthTestService;
import uz.jl.service.auth.AuthUserService;
import uz.jl.vo.auth.AuthAnswerCreateVO;
import uz.jl.vo.auth.AuthTestCreateVO;
import uz.jl.vo.auth.AuthUserCreateVO;
import uz.jl.vo.auth.Session;
import uz.jl.vo.http.Response;

import java.util.Objects;

public class AuthUI {
    static AuthUserService service = ApplicationContextHolder.getBean(AuthUserService.class);
    static AuthTestService testService = ApplicationContextHolder.getBean(AuthTestService.class);
    static AuthAnswerService answerService = ApplicationContextHolder.getBean(AuthAnswerService.class);

    static AuthUI authUI = new AuthUI();


    public static void main(String[] args) {

        if (Objects.isNull(Session.sessionUser)) {
            BaseUtils.println("Login -> 1");
            BaseUtils.println("Register -> 2");
        } else if (Session.sessionUser.getRole().equals(AuthRole.USER)){
            BaseUtils.println("\nStart quiz -> 3");
            BaseUtils.println("Settings -> 4");
            BaseUtils.println("Logout -> 0");
        } else if (Session.sessionUser.getRole().equals(AuthRole.TEACHER)){
            BaseUtils.println("\nSettings -> 4");
            BaseUtils.println("Test CRUD -> 5");
            BaseUtils.println("Show statistics -> 6");
            BaseUtils.println("Logout -> 0");
        } else {
            BaseUtils.println("\nSettings -> 4");
            BaseUtils.println("Test CRUD -> 5");
            BaseUtils.println("Show statistics -> 6");
            BaseUtils.println("Subject CRUD -> 7");
            BaseUtils.println("Change role -> 8");
            BaseUtils.println("Logout -> 0");
        }

        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> authUI.login();
            case "2" -> authUI.register();
            case "3" -> authUI.startQuiz();
            case "4" -> authUI.Settings();
            case "5" -> authUI.testCrud();
            case "6" -> authUI.showStatistics();
            case "7" -> authUI.subjectCrud();
            case "8" -> authUI.changeRole();
            case "0" -> authUI.logout();
            case "q" -> {
                BaseUtils.println("Bye", Colors.CYAN);
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
        main(args);
    }

    private void testCrud() {
        BaseUtils.println("\nCreate test -> 1");
        BaseUtils.println("Delete test -> 2");
        BaseUtils.println("Update test -> 3");
        BaseUtils.println("Show test list-> 4");
        BaseUtils.println("Go back -> 0");

        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> authUI.createTest();
            case "2" -> authUI.deleteTest();
            case "3" -> authUI.updateTest();
            case "4" -> authUI.getAllTest();
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    private void getAllTest() {

    }

    private void updateTest() {

    }

    private void deleteTest() {

    }

    private void createTest() {
        String title = BaseUtils.readText("Enter body : ");

        BaseUtils.println("EASY -> 1");
        BaseUtils.println("MEDIUM -> 2");
        BaseUtils.println("HARD -> 3");
        String level = BaseUtils.readText("Choose level");
        switch (level) {
            case "1" -> level = TestLevel.EASY.name();
            case "2" -> level = TestLevel.MEDIUM.name();
            case "3" -> level = TestLevel.HARD.name();
            default -> level = TestLevel.MEDIUM.name();
        }

        String variant_A = BaseUtils.readText("Enter variant A : ");
        String variant_B = BaseUtils.readText("Enter variant B : ");
        String variant_D = BaseUtils.readText("Enter variant D : ");

        BaseUtils.println("A -> 1");
        BaseUtils.println("B -> 2");
        BaseUtils.println("D -> 3");
        String answer = BaseUtils.readText("Choose answer");
        switch (answer) {
            case "1" -> answer = variant_A;
            case "2" -> answer = variant_B;
            case "3" -> answer = variant_D;
            default -> answer = variant_A;
        }

        AuthTestCreateVO testVO = AuthTestCreateVO.builder()
                .title(title)
                .level(TestLevel.valueOf(level))
                .build();

        AuthAnswerCreateVO answerVO = AuthAnswerCreateVO.builder()
                .variant_A(variant_A)
                .variant_B(variant_B)
                .variant_D(variant_D)
                .answer(answer)
                .build();

        testVO.setAnswers(answerVO);
        answerVO.setTest(testVO);


        print_response(testService.create(testVO));
        print_response(answerService.create(answerVO));
    }

    private void changeRole() {

    }

    private void subjectCrud() {

    }

    private void showStatistics() {

    }


    private void Settings() {
    }

    private void startQuiz() {

    }


    private void logout() {
        Session.sessionUser = null;
    }

    private void register() {
        AuthUserCreateVO vo = AuthUserCreateVO.builder()
                .username(BaseUtils.readText("Enter username: "))
                .email(BaseUtils.readText("Enter email: "))
                .password(BaseUtils.readText("Enter password: "))
                .build();
        print_response(service.create(vo));
    }

    private void login() {
        String username = BaseUtils.readText("Enter username: ");
        String password = BaseUtils.readText("Enter password: ");
        print_response(service.login(username, password));
    }

    public void print_response(Response response) {
        String color = response.isOk() ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response.getBody()), color);
    }
}
