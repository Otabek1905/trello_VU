package uz.jl.dao.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.jl.dao.GenericDAO;
import uz.jl.domains.auth.AuthSubject;
import uz.jl.domains.auth.AuthTest;
import uz.jl.domains.auth.AuthUser;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthTestDAO extends GenericDAO<AuthTest, Long> {
    private static AuthTestDAO instance;

    public static AuthTestDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthTestDAO();
        }
        return instance;
    }

    public Optional<AuthTest> findByBody(String body) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthTest> query = session
                .createQuery("select t from AuthTest t where lower(t.body) = lower(:body) ",
                        AuthTest.class);
        query.setParameter("body", body);
        Optional<AuthTest> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }

    public Optional<AuthSubject> findSubjectById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthSubject> query = session
                .createQuery("select t from AuthSubject t where t.authUser.id = :id ",
                        AuthSubject.class);
        query.setParameter("id", id);
        Optional<AuthSubject> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }
}
