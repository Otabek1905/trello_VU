package uz.jl.domains.auth;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import uz.jl.domains.Auditable;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthAnswers extends Auditable {

    @Column(nullable = false)
    private String variant_A;

    @Column(nullable = false)
    private String variant_B;

    @Column(nullable = false)
    private String variant_D;

    @Column(nullable = false)
    private String answer;

    @OneToOne(targetEntity = AuthTest.class,cascade = CascadeType.ALL)
    private AuthTest test;

    @Builder(builderMethodName = "childBuilder")
    public AuthAnswers(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String variant_A, String variant_B, String variant_D, String answer) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.variant_A = variant_A;
        this.variant_B = variant_B;
        this.variant_D = variant_D;
        this.answer = answer;
    }
}
