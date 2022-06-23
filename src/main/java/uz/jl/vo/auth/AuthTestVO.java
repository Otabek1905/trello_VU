package uz.jl.vo.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.dao.BaseDAO;
import uz.jl.enums.TestLevel;
import uz.jl.vo.GenericVO;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTestVO extends GenericVO implements BaseDAO {
    private String title;
    private TestLevel level;
    private Timestamp createdAt;
}
