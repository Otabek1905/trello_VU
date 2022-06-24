package uz.guava.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.guava.vo.BaseVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserCreateVO implements BaseVO {
    private String username;
    private String password;
    private String email;
}
