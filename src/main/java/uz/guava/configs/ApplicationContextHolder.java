package uz.guava.configs;

import uz.guava.dao.auth.AuthUserDAO;
import uz.guava.dao.auth.AuthTestDAO;
import uz.guava.dao.auth.SubjectDAO;
import uz.guava.service.auth.AuthTestService;
import uz.guava.service.auth.AuthUserService;
import uz.guava.service.auth.SubjectService;
import uz.guava.utils.BaseUtils;

public class ApplicationContextHolder {

    @SuppressWarnings("unchecked")
    public static  <T> T getBean(String beanName) {
        return switch (beanName) {
            case "AuthUserDAO" -> (T) AuthUserDAO.getInstance();
            case "AuthUserService" -> (T) AuthUserService.getInstance();
            case "BaseUtils" -> (T) BaseUtils.getInstance();
            case "AuthTestDAO" -> (T) AuthTestDAO.getInstance();
            case "AuthTestService" -> (T) AuthTestService.getInstance();
            case "SubjectDAO" -> (T) SubjectDAO.getInstance();
            case "SubjectService" -> (T) SubjectService.getInstance();
            default -> throw new RuntimeException("Bean Not Found");
        };
    }

    public static  <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);
    }

}

