package uz.jl.configs;

import uz.jl.dao.auth.AuthAnswerDAO;
import uz.jl.dao.auth.AuthTestDAO;
import uz.jl.dao.auth.AuthUserDAO;
import uz.jl.service.auth.AuthAnswerService;
import uz.jl.service.auth.AuthTestService;
import uz.jl.service.auth.AuthUserService;
import uz.jl.ui.TestUI;
import uz.jl.utils.BaseUtils;

public class ApplicationContextHolder {

    @SuppressWarnings("unchecked")
    public static  <T> T getBean(String beanName) {
        return switch (beanName) {
            case "AuthUserDAO" -> (T) AuthUserDAO.getInstance();
            case "BaseUtils" -> (T) BaseUtils.getInstance();
            case "AuthUserService" -> (T) AuthUserService.getInstance();
//            case "TestUI" -> (T) TestUI.getInstance();
            case "AuthTestDAO" -> (T) AuthTestDAO.getInstance();
            case "AuthAnswerDAO" -> (T) AuthAnswerDAO.getInstance();
            case "AuthTestService" -> (T) AuthTestService.getInstance();
            case "AuthAnswerService" -> (T) AuthAnswerService.getInstance();
            default -> throw new RuntimeException("Bean Not Found");
        };
    }

    public static  <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);
    }

}
