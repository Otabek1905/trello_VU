package uz.jl.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.enums.TestLevel;
import uz.jl.vo.GenericVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTestUpdateVO extends GenericVO {
    private String title;
    private TestLevel level;
}
