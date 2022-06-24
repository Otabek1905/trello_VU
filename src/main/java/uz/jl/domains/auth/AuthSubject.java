package uz.jl.domains.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.jl.domains.Auditable;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthSubject extends Auditable {
    @Column(nullable = false)
    private String subjectName;

    @Builder(builderMethodName = "childBuilder")
    public AuthSubject(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String subject_name) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.subjectName = subject_name;
    }
}
