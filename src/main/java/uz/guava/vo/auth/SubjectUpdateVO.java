package uz.guava.vo.auth;

import lombok.*;
import uz.guava.vo.GenericVO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectUpdateVO extends GenericVO  {
    private String current_name;
    private String new_name;

    @Builder(builderMethodName = "childBuilder")
    public SubjectUpdateVO(long id, String current_name, String new_name) {
        super(id);
        this.current_name = current_name;
        this.new_name = new_name;
    }
}
