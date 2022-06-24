package uz.jl.service.auth;

import lombok.NonNull;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dao.AbstractDAO;
import uz.jl.dao.auth.AuthSubjectDAO;
import uz.jl.domains.auth.AuthSubject;
import uz.jl.service.GenericCRUDService;
import uz.jl.utils.BaseUtils;
import uz.jl.vo.auth.Session;
import uz.jl.vo.auth.subject.AuthSubjectCreateVO;
import uz.jl.vo.auth.subject.AuthSubjectUpdateVO;
import uz.jl.vo.auth.subject.AuthSubjectVO;
import uz.jl.vo.http.Response;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthSubjectService extends AbstractDAO<AuthSubjectDAO> implements GenericCRUDService<AuthSubjectVO, AuthSubjectCreateVO, AuthSubjectUpdateVO, Long> {

    public AuthSubjectService() {
        super(

                ApplicationContextHolder.getBean(AuthSubjectDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class)

        );
    }

    private static AuthSubjectService instance;


    public static AuthSubjectService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthSubjectService();

        }
        return instance;
    }


    @Override
    public Response<Long> create(@NonNull AuthSubjectCreateVO vo) {

        Optional<AuthSubject> optionalAuthSubject = dao.findBySubjectName(vo.getSubjectName());
        if (optionalAuthSubject.isPresent()) {
            throw new RuntimeException("Subject already exist");
        }
        AuthSubject authSubject = AuthSubject
                .childBuilder()
                .subject_name(vo.getSubjectName())
                .createdBy(dao.findByUserName(Session.sessionUser.getUsername()).get().getId())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        dao.save(authSubject);
        return new Response<>(authSubject.getId());

    }

    @Override
    public Response<Void> update(@NonNull AuthSubjectUpdateVO vo) {
        Optional<AuthSubject> authSubject = Optional.ofNullable(dao.findById(vo.getId()));

        if (authSubject.isEmpty()) {
            throw new RuntimeException("Subject does not exist");
        }
        String newSubjectNAme = uz.jl.BaseUtils.readText("Write new subject name : ");
        Optional<AuthSubject> newSubject = dao.findBySubjectName(newSubjectNAme);
        if (newSubject.isPresent()) {
            throw new RuntimeException("Subject already exist");
        }
        AuthSubject subject = authSubject.get();
        subject.setSubjectName(newSubjectNAme);
        subject.setUpdatedBy(dao.findByUserName(Session.sessionUser.getUsername()).get().getId());
        subject.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));


        dao.update(subject);
        return new Response<>(null);
    }

    @Override
    public Response<Void> delete(@NonNull Long id) {
        Optional<AuthSubject> authSubject = Optional.ofNullable(dao.findById(id));

        if (authSubject.isEmpty()) {
            throw new RuntimeException("Subject does not exist");
        }
        AuthSubject subject = authSubject.get();
        subject.setDeleted(true);
        subject.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        subject.setUpdatedBy(dao.findByUserName(Session.sessionUser.getUsername()).get().getId());
        dao.update(subject);

        return new Response<>(null);
    }

    @Override
    public Response<AuthSubjectVO> get(@NonNull Long id) {
        Optional<AuthSubject> authSubject = Optional.ofNullable(dao.findById(id));

        if (authSubject.isEmpty()) {
            throw new RuntimeException("Subject does not exist");
        }
        AuthSubjectVO authSubjectVO = AuthSubjectVO.childBuilder()
                .subject_name(authSubject.get().getSubjectName())
                .id(authSubject.get().getId())
                .build();

        return new Response<>(authSubjectVO);
    }

    @Override
    public Response<List<AuthSubjectVO>> getAll() {
        List<AuthSubjectVO> allSubjects = dao.findAllSubjects();
        if (allSubjects.isEmpty()) {
            throw new RuntimeException("There is not any subjects");
        }


        return new Response<>(allSubjects);
    }



}
