package uz.jl.service.auth;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import uz.jl.dao.AbstractDAO;
import uz.jl.dao.auth.AuthTestDAO;
import uz.jl.dao.auth.AuthUserDAO;
import uz.jl.domains.auth.AuthTest;
import uz.jl.service.GenericCRUDService;
import uz.jl.utils.BaseUtils;
import uz.jl.vo.auth.AuthTestCreateVO;
import uz.jl.vo.auth.AuthTestUpdateVO;
import uz.jl.vo.auth.AuthTestVO;
import uz.jl.vo.http.Response;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class AuthTestService extends AbstractDAO<AuthTestDAO> implements GenericCRUDService<
        AuthTestVO,
        AuthTestCreateVO,
        AuthTestUpdateVO,
        Long> {

    private static AuthTestService instance;


    public static AuthTestService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthTestService();
        }
        return instance;
    }


    public AuthTestService(AuthTestDAO dao, BaseUtils utils) {
        super(dao, utils);
    }

    public AuthTestService(){
        super(null,null);
    }

    @Override
    public Response<Long> create(@NonNull AuthTestCreateVO vo) {
        Optional<AuthTest> optionalAuthTest =  dao.findByTitle(vo.getTitle());
        if (optionalAuthTest.isPresent()) {
            throw new RuntimeException("Test already taken");
        }

        AuthTest authTest = AuthTest.childBuilder()
                .title(vo.getTitle())
                .level(vo.getLevel())
//                .answers(vo.getAnswers())
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
