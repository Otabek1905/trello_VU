package uz.jl.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.domains.auth.AuthAnswers;
import uz.jl.enums.TestLevel;
import uz.jl.vo.BaseVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthTestCreateVO implements BaseVO {
    private String title;
    private TestLevel level;
    private AuthAnswerCreateVO answers;
}
