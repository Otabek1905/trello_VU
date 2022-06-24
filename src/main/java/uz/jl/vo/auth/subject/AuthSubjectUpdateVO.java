package uz.jl.vo.auth.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.vo.GenericVO;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthSubjectUpdateVO extends GenericVO {
    private String subjectName;

    @Builder(builderMethodName = "childBuilder")
    public AuthSubjectUpdateVO(long id, String subjectName) {
        super(id);
        this.subjectName = subjectName;
    }
}
