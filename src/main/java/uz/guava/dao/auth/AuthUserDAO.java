package uz.guava.dao.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.guava.dao.GenericDAO;
import uz.guava.domains.auth.AuthUser;



import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUserDAO extends GenericDAO<AuthUser, Long> {
    private static AuthUserDAO instance;

    public static AuthUserDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthUserDAO();
        }
        return instance;
    }

    public Optional<AuthUser> findByUserName(String username) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthUser> query = session
                .createQuery("select t from AuthUser t where t.username = :username",
                        AuthUser.class);
        query.setParameter("username", username);
        return Optional.ofNullable(query.getSingleResultOrNull());
    }



}
