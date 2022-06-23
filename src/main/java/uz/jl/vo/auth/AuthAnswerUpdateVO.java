package uz.jl.vo.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.vo.GenericVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthAnswerUpdateVO extends GenericVO {
    private String variant_A;
    private String variant_B;
    private String variant_D;
    protected String answer;
}
