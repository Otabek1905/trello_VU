package uz.jl.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.domains.auth.AuthAnswers;
import uz.jl.domains.auth.AuthTest;
import uz.jl.vo.BaseVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthAnswerCreateVO extends AuthAnswers implements BaseVO {
    private String variant_A;
    private String variant_B;
    private String variant_D;
    private String answer;
    private AuthTestCreateVO test;
}
