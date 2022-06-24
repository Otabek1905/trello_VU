package uz.guava.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.guava.enums.TestLevel;
import uz.guava.vo.GenericVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTestUpdateVO extends GenericVO {
    private String body;
    private TestLevel level;
}
