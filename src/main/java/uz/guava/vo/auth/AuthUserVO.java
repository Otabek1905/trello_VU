package uz.guava.vo.auth;

import lombok.*;
import uz.guava.enums.AuthRole;
import uz.guava.vo.GenericVO;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserVO extends GenericVO {
    private String username;
    private String email;
    private AuthRole role;
    private Timestamp createdAt;

    @Builder(builderMethodName = "childBuilder")
    public AuthUserVO(long id, String username, String email, AuthRole role, Timestamp createdAt) {
        super(id);
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }
}
