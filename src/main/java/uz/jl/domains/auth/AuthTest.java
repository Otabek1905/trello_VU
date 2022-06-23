package uz.jl.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import uz.jl.domains.Auditable;
import uz.jl.enums.TestLevel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTest extends Auditable {

    @Column(unique = true, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private TestLevel level;

    @OneToOne(targetEntity = AuthAnswers.class,cascade = CascadeType.ALL)
    private AuthAnswers answers;

    @Builder(builderMethodName = "childBuilder")
    public AuthTest(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String title, TestLevel level, AuthAnswers answers) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.title = title;
        this.level = level;
        this.answers = answers;
    }
}
