package uz.guava.vo.auth;

import lombok.*;
import uz.guava.vo.BaseVO;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCreateVO implements BaseVO {
    private String name;
    private Long createdBy;
}
