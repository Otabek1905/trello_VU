package uz.guava.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.guava.vo.GenericVO;
import uz.guava.enums.AuthRole;
import uz.guava.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserUpdateVO extends GenericVO {
    private String username;
    private String email;
    private AuthRole role;
    private Status status;
}
