package uz.jl.vo.auth.subject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.jl.vo.BaseVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthSubjectCreateVO /* extends AuthAnswers */ implements BaseVO  {
    private String subjectName;
}
