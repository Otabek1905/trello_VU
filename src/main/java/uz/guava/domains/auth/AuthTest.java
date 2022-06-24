package uz.guava.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import uz.guava.domains.Auditable;
import uz.guava.enums.TestLevel;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthTest extends Auditable {
    @Column(nullable = false, unique = true)
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
    private Subject subject;



    @Builder(builderMethodName = "childBuilder")
    public AuthTest(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String body, TestLevel level, String variant_A, String variant_B, String variant_D, String answer, Subject subject) {
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