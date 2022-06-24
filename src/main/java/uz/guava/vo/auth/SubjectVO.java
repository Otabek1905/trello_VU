package uz.guava.vo.auth;

import lombok.*;
import uz.guava.vo.GenericVO;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectVO extends GenericVO {
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public SubjectVO(long id, String name) {
        super(id);
        this.name = name;
    }
}
