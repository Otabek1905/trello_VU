package uz.guava.dao.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.guava.configs.HibernateUtils;
import uz.guava.configs.HibernateUtils;
import uz.guava.dao.GenericDAO;
import uz.guava.domains.auth.Subject;
import uz.guava.exceptions.CustomSQLException;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SubjectDAO extends GenericDAO<Subject, Long> {

    private static SubjectDAO instance;

    public static SubjectDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubjectDAO();
        }
        return instance;
    }

    public Optional<Subject> findByName(String in_name) {
        Session session = getSession();
        session.beginTransaction();
        Query<Subject> query = session
                .createQuery("select t from Subject t where lower(t.name) = lower(:in_name) ",
                        Subject.class);
        query.setParameter("in_name", in_name);
        return Optional.ofNullable(query.getSingleResultOrNull());
    }

    public Optional<String> update(String current_name, String new_name, Long updater) throws CustomSQLException {
       return null;
    }
}
