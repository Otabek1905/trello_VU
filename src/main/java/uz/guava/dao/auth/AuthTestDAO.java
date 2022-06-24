package uz.guava.dao.auth;

import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.guava.configs.HibernateUtils;
import uz.guava.dao.GenericDAO;
import uz.guava.domains.auth.AuthTest;


import java.util.Objects;
import java.util.Optional;

public class AuthTestDAO extends GenericDAO<AuthTest, Long> {

    private static AuthTestDAO instance;

    public static AuthTestDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthTestDAO();
        }
        return instance;
    }

    public Optional<AuthTest> findByText(String in_text) {
        Session session = getSession();
        session.beginTransaction();
        Query<AuthTest> query = session
                .createQuery("select t from AuthTest t where lower(t.body) = lower(:in_text) ",
                        AuthTest.class);
        query.setParameter("in_text", in_text);
        return Optional.ofNullable(query.getSingleResultOrNull());
    }

    public Optional<AuthTest> findByTitle(String body) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<AuthTest> query = session
                .createQuery("select t from AuthTest t where lower(t.body) = lower(:body) ",
                        AuthTest.class);
        query.setParameter("body", body);
        Optional<AuthTest> result = Optional.ofNullable(query.getSingleResultOrNull());
        session.close();
        return result;

    }
}