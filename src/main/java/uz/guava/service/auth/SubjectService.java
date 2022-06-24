package uz.guava.service.auth;

import lombok.NonNull;
import uz.guava.configs.ApplicationContextHolder;
import uz.guava.dao.AbstractDAO;
import uz.guava.dao.auth.SubjectDAO;
import uz.guava.domains.auth.Subject;
import uz.guava.exceptions.CustomSQLException;
import uz.guava.service.GenericCRUDService;
import uz.guava.utils.BaseUtils;
import uz.guava.vo.Session;
import uz.guava.vo.auth.SubjectCreateVO;
import uz.guava.vo.auth.SubjectUpdateVO;
import uz.guava.vo.auth.SubjectVO;
import uz.guava.vo.http.Response;


import java.util.List;
import java.util.Optional;

public class SubjectService extends AbstractDAO<SubjectDAO> implements GenericCRUDService<
        SubjectVO,
        SubjectCreateVO,
        SubjectUpdateVO,
        Long
        > {

    private static SubjectService instance;
//    private final AuthUserValidator validator;

    private SubjectService() {
        super(
                ApplicationContextHolder.getBean(SubjectDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class)
        );
    }

    @Override
    public Response<Long> create(@NonNull SubjectCreateVO vo) {
        Optional<Subject> optionalSubject = dao.findByName(vo.getName());
        if (optionalSubject.isPresent()) {
            throw new RuntimeException("subject already exist!");
        }
        Subject subject = Subject
                .childBuilder()
                .name(vo.getName())
                .createdBy(vo.getCreatedBy())
                .build();
        return new Response<>(dao.save(subject).getId());
    }

    @Override
    public Response<Void> update(@NonNull SubjectUpdateVO vo) {
        Optional<Subject> optionalAuthUser = dao.findByName(vo.getCurrent_name());
        if (optionalAuthUser.isPresent()) {
            throw new RuntimeException("subject already exist!");
        }

        SubjectDAO dao1 = new SubjectDAO();
        try {
            dao1.update(vo.getCurrent_name(), vo.getNew_name(), Session.sessionUser.getId());
        } catch (CustomSQLException e) {
            throw new RuntimeException(e.getCause().getLocalizedMessage());
        }


        return null;
    }

    @Override
    public Response<Void> delete(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<SubjectVO> get(@NonNull Long aLong) {
        return null;
    }

    @Override
    public Response<List<SubjectVO>> getAll() {
        return null;
    }

    public static SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }
        return instance;
    }
}

