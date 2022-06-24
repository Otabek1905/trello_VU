package uz.guava.vo.auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.guava.enums.TestLevel;
import uz.guava.vo.BaseVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthTestCreateVO implements BaseVO {
    private String body;
    private TestLevel level;
    private String variant_A;
    private String variant_B;
    private String variant_D;
    private String answer;

}
