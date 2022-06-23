package uz.jl.dao.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.jl.dao.GenericDAO;
import uz.jl.domains.auth.AuthAnswers;
import uz.jl.domains.auth.AuthTest;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthAnswerDAO extends GenericDAO<AuthAnswers, Long> {
    private static AuthAnswerDAO instance;

    public static AuthAnswerDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthAnswerDAO();
        }
        return instance;
    }
}
