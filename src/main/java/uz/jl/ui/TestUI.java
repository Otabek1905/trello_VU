package uz.jl.ui;

import lombok.NoArgsConstructor;
import uz.jl.BaseUtils;
import uz.jl.Colors;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dao.auth.AuthUserDAO;
import uz.jl.enums.TestLevel;
import uz.jl.service.auth.AuthAnswerService;
import uz.jl.service.auth.AuthTestService;

import uz.jl.vo.auth.AuthAnswerCreateVO;
import uz.jl.vo.auth.AuthTestCreateVO;
import uz.jl.vo.http.Response;

import java.util.Objects;


public class TestUI {
    static AuthTestService testService = ApplicationContextHolder.getBean(AuthTestService.class);
    static AuthAnswerService answerService = ApplicationContextHolder.getBean(AuthAnswerService.class);

//    private static TestUI instance;
//
//    public static TestUI getInstance() {
//        if (instance == null) {
//            instance = new TestUI();
//        }
//        return instance;
//    }

    protected static void getAll() {

    }

    protected static void create() {
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


        print_response(testService.create(testVO));
        print_response(answerService.create(answerVO));
    }

    public static void delete() {

    }

    public static void update() {

    }

    protected static void testCrud() {
        BaseUtils.println("\nCreate test -> 1");
        BaseUtils.println("Delete test -> 2");
        BaseUtils.println("Update test -> 3");
        BaseUtils.println("Show test list-> 4");
        BaseUtils.println("Go back -> 0");

        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> TestUI.create();
            case "2" -> TestUI.delete();
            case "3" -> TestUI.update();
            case "4" -> TestUI.getAll();
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    public static void print_response(Response response) {
        String color = response.isOk() ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response.getBody()), color);
    }

    public static void createTest() {
    }
}
