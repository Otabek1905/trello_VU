package uz.jl.configs;

import uz.jl.dao.auth.AuthSubjectDAO;
import uz.jl.dao.auth.AuthTestDAO;
import uz.jl.dao.auth.AuthUserDAO;
import uz.jl.service.auth.AuthSubjectService;
import uz.jl.service.auth.AuthTestService;
import uz.jl.service.auth.AuthUserService;
import uz.jl.utils.BaseUtils;

public class ApplicationContextHolder {

    @SuppressWarnings("unchecked")
    public static  <T> T getBean(String beanName) {
        return switch (beanName) {
            case "AuthUserDAO" -> (T) AuthUserDAO.getInstance();
            case "BaseUtils" -> (T) BaseUtils.getInstance();
            case "AuthUserService" -> (T) AuthUserService.getInstance();
            //Davronov Otabek
            case "AuthSubjectDAO"->(T) AuthSubjectDAO.getInstance();
            case "AuthSubjectService"->(T) AuthSubjectService.getInstance();

//            case "TestUI" -> (T) TestUI.getInstance();
            case "AuthTestDAO" -> (T) AuthTestDAO.getInstance();
            case "AuthTestService" -> (T) AuthTestService.getInstance();
            default -> throw new RuntimeException("Bean Not Found");
        };
    }

    public static  <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);
    }

}
