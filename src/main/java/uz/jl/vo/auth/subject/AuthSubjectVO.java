package uz.jl.vo.auth.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.dao.BaseDAO;
import uz.jl.vo.GenericVO;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthSubjectVO extends GenericVO implements BaseDAO {
    private String subject_name;

    @Builder(builderMethodName = "childBuilder")
    public AuthSubjectVO(long id, String subject_name) {
        super(id);
        this.subject_name = subject_name;
    }
}
