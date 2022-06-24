package uz.guava.vo.auth;


import lombok.*;
import uz.guava.enums.TestLevel;
import uz.guava.vo.GenericVO;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthTestVO extends GenericVO {
    private String text;
    private TestLevel level;
    private Timestamp createdAt;

    @Builder(builderMethodName = "childBuilder")
    public AuthTestVO(long id, String text, TestLevel level, Timestamp createdAt) {
        super(id);
        this.text = text;
        this.level = level;
        this.createdAt = createdAt;
    }
}
