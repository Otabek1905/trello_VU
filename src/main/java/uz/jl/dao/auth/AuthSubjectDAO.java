package uz.jl.dao.auth;

import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.jl.dao.GenericDAO;
import uz.jl.domains.auth.AuthSubject;
import uz.jl.domains.auth.AuthUser;
import uz.jl.vo.auth.subject.AuthSubjectVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthSubjectDAO extends GenericDAO<AuthSubject, Long> {
    private static AuthSubjectDAO instance;

    public static AuthSubjectDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthSubjectDAO();
        }
        return instance;
    }

    public Optional<AuthSubject> findBySubjectName(String subjectName) {

        Session session = getSession();
        session.beginTransaction();
        Query<AuthSubject> query = session
                .createQuery("select t from AuthSubject t where lower(t.subjectName) = lower(:subjectName) ",
                        AuthSubject.class);
        query.setParameter("subjectName", subjectName);
        Optional<AuthSubject> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }

    public Optional<AuthUser> findByUserName(String username) {
        Session session = getSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Query<AuthUser> query = session
                .createQuery("select t from AuthUser t where lower(t.username) = lower(:username) ",
                        AuthUser.class);
        query.setParameter("username", username);
        Optional<AuthUser> result = Optional.ofNullable(query.getSingleResultOrNull());


        session.close();
        return result;
    }

    public List<AuthSubjectVO> findAllSubjects() {
        Session session = getSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Query<AuthSubject> query = session
                .createQuery("select t from AuthSubject t where t.deleted=false ",
                        AuthSubject.class);
        List<AuthSubject> resultList = query.getResultList();
        List<AuthSubjectVO> voList = new ArrayList<>();
        for (AuthSubject authSubject : resultList) {
            AuthSubjectVO authSubjectVO = new AuthSubjectVO();
            authSubjectVO.setId(authSubject.getId());
            authSubjectVO.setSubject_name(authSubject.getSubjectName());
            voList.add(authSubjectVO);
        }

        session.close();
        return voList;
    }

    public Optional<AuthSubject> deleteSubjectById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthSubject> query = session
                .createQuery("select t from AuthSubject t where (t.id) = (:id) ",
                        AuthSubject.class);
        query.setParameter("id", id);
        Optional<AuthSubject> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }

    public void saveDeletedSubject(Long id) {
        Session session = getSession();
        session.beginTransaction();
        String qryString2 = "update AuthSubject s set s.deleted=true where s.id=:id";
        Query query2 = session.createQuery(qryString2);

        query2.setParameter("id", id);
        query2.executeUpdate();
        session.close();

    }
}

