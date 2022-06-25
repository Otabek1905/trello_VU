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
    private String body;

    @Enumerated(EnumType.STRING)
    private TestLevel level;

    @Column(nullable = false)
    private String variant_A;

    @Column(nullable = false)
    private String variant_B;

    @Column(nullable = false)
    private String variant_D;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private AuthSubject subject;

    @Builder(builderMethodName = "childBuilder")
    public AuthTest(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String body, TestLevel level, String variant_A, String variant_B, String variant_D, String answer, AuthSubject subject) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.body = body;
        this.level = level;
        this.variant_A = variant_A;
        this.variant_B = variant_B;
        this.variant_D = variant_D;
        this.answer = answer;
        this.subject = subject;
    }
}
