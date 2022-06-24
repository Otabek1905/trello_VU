package uz.guava.service.auth;

import lombok.NonNull;
import uz.guava.configs.ApplicationContextHolder;
import uz.guava.dao.AbstractDAO;
import uz.guava.dao.auth.AuthTestDAO;
import uz.guava.domains.auth.AuthTest;
import uz.guava.service.GenericCRUDService;
import uz.guava.utils.BaseUtils;
import uz.guava.vo.auth.AuthTestCreateVO;
import uz.guava.vo.auth.AuthTestUpdateVO;
import uz.guava.vo.auth.AuthTestVO;
import uz.guava.vo.http.Response;


import java.util.List;
import java.util.Optional;

public class AuthTestService extends AbstractDAO<AuthTestDAO> implements GenericCRUDService<
        AuthTestVO,
        AuthTestCreateVO,
        AuthTestUpdateVO,
        Long> {


    private static AuthTestService instance;

    private AuthTestService() {
        super(
                ApplicationContextHolder.getBean(AuthTestDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class)
        );
    }

    public static AuthTestService getInstance() {
        if (instance == null) {
            instance = new AuthTestService();
        }
        return instance;
    }

    @Override
    public Response<Long> create(@NonNull AuthTestCreateVO vo) {
        Optional<AuthTest> optionalAuthTest =  dao.findByTitle(vo.getBody());
        if (optionalAuthTest.isPresent()) {
            throw new RuntimeException("Test already exist");
        }

        AuthTest authTest = AuthTest.childBuilder()
                .body(vo.getBody())
                .level(vo.getLevel())
                .variant_A(vo.getVariant_A())
                .variant_B(vo.getVariant_B())
                .variant_D(vo.getVariant_D())
                .answer(vo.getAnswer())
                .build();
        return new Response<>(dao.save(authTest).getId());
    }

    @Override
    public Response<Void> update(@NonNull AuthTestUpdateVO vo) {
        return null;
    }

    @Override
    public Response<Void> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<AuthTestVO> get(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<List<AuthTestVO>> getAll() {
        return null;
    }
}
