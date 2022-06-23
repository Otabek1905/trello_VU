package uz.jl.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.vo.GenericVO;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthAnswerVO extends GenericVO {
    private String variant_A;
    private String variant_B;
    private String variant_D;
    private String answer;
    private Timestamp createdAt;
}
