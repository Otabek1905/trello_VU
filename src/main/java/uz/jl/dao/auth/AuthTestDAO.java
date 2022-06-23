package uz.jl.dao.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.jl.dao.GenericDAO;
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

    public Optional<AuthTest> findByTitle(String title) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthTest> query = session
                .createQuery("select t from AuthTest t where lower(t.title) = lower(:title) ",
                        AuthTest.class);
        query.setParameter("title", title);
        Optional<AuthTest> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;
    }
}
