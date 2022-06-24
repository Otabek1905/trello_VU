package uz.guava.service.auth;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import uz.guava.configs.ApplicationContextHolder;
import uz.guava.dao.AbstractDAO;
import uz.guava.dao.auth.AuthUserDAO;
import uz.guava.domains.auth.AuthUser;
import uz.guava.enums.AuthRole;
import uz.guava.enums.Status;
import uz.guava.exceptions.CustomSQLException;
import uz.guava.service.GenericCRUDService;
import uz.guava.utils.BaseUtils;
import uz.guava.vo.Session;
import uz.guava.vo.auth.AuthUserCreateVO;
import uz.guava.vo.auth.AuthUserUpdateVO;
import uz.guava.vo.auth.AuthUserVO;
import uz.guava.vo.http.Response;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthUserService extends AbstractDAO<AuthUserDAO> implements GenericCRUDService<
        AuthUserVO,
        AuthUserCreateVO,
        AuthUserUpdateVO,
        Long> {

    private static AuthUserService instance;

    private AuthUserService() {
        super(
                ApplicationContextHolder.getBean(AuthUserDAO.class),
                ApplicationContextHolder.getBean(BaseUtils.class)
        );
    }

    @Override
    @Transactional
    public Response<Long> create(@NonNull AuthUserCreateVO vo) {
        // TODO: 6/21/2022 validate input
        Optional<AuthUser> optionalAuthUser = dao.findByUserName(vo.getUsername());
        if (optionalAuthUser.isPresent()) {
            throw new RuntimeException("Username already exist!");
        }

        AuthUser authUser = AuthUser
                .childBuilder()
                .username(vo.getUsername())
                .password(utils.encode(vo.getPassword()))
                .email(vo.getEmail())
                .role(AuthRole.USER)
                .status(Status.ACTIVE)
                .build();
        return new Response<>(dao.save(authUser).getId());
    }

    @Override
    public Response<Void> update(@NonNull AuthUserUpdateVO vo) {
        return null;
    }

    @Override
    public Response<Void> delete(@NonNull Long id) {
        return null;
    }

    @Override
    public Response<AuthUserVO> get(@NonNull Long id) {
        return null;
    }

    @Override
    public Response<List<AuthUserVO>> getAll() {
        return null;
    }

    public static AuthUserService getInstance() {
        if (instance == null) {
            instance = new AuthUserService();
        }
        return instance;
    }

    public Response<AuthUserVO> login(String username, String password) {
        Optional<AuthUser> response = dao.findByUserName(username);

        if (response.isEmpty()) {
            throw new RuntimeException("Username does not exist!");
        }

        AuthUser authUser = response.get();
        if (!utils.matchPassword(password, authUser.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }
        AuthUserVO authUserVO = AuthUserVO.builder()
                .username(authUser.getUsername())
                .email(authUser.getEmail())
                .createdAt(authUser.getCreatedAt())
                .role(authUser.getRole())
                .build();

        Session.setSessionUser(authUserVO);
        return new Response<>(authUserVO);
    }

}
