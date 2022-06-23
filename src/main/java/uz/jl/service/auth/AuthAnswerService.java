package uz.jl.service.auth;

import lombok.NonNull;
import uz.jl.dao.AbstractDAO;
import uz.jl.dao.auth.AuthAnswerDAO;
import uz.jl.domains.auth.AuthAnswers;
import uz.jl.service.GenericCRUDService;
import uz.jl.utils.BaseUtils;
import uz.jl.vo.auth.AuthAnswerCreateVO;

import uz.jl.vo.auth.AuthAnswerUpdateVO;
import uz.jl.vo.auth.AuthAnswerVO;
import uz.jl.vo.auth.AuthTestVO;
import uz.jl.vo.http.Response;

import java.util.List;
import java.util.Objects;

public class AuthAnswerService extends AbstractDAO<AuthAnswerDAO> implements GenericCRUDService<
        AuthAnswerVO,
        AuthAnswerCreateVO,
        AuthAnswerUpdateVO,
        Long
        > {

    private static AuthAnswerService instance;

    public AuthAnswerService() {
        super(null,null);
    }


    public static AuthAnswerService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthAnswerService();
        }
        return instance;
    }


    public AuthAnswerService(AuthAnswerDAO dao, BaseUtils utils) {
        super(dao, utils);
    }

    @Override
    public Response<Long> create(@NonNull AuthAnswerCreateVO vo) {
        AuthAnswers authAnswers = AuthAnswers.childBuilder()
                .variant_A(vo.getVariant_A())
                .variant_B(vo.getVariant_B())
                .variant_D(vo.getVariant_D())
                .answer(vo.getAnswer())
                .build();
        return new Response<>(dao.save(authAnswers).getId());
    }

    @Override
    public Response<Void> update(@NonNull AuthAnswerUpdateVO vo) {
        return null;
    }

    @Override
    public Response<Void> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<AuthAnswerVO> get(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<List<AuthAnswerVO>> getAll() {
        return null;
    }
}
