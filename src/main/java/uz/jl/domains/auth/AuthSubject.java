package uz.jl.domains.auth;

import jakarta.persistence.*;
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

//    @OneToOne(mappedBy = "authSubject")
//    private AuthUser user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id",unique = true)
    private AuthUser authUser;

//
//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "AuthSubject_authUser",
//            joinColumns = @JoinColumn(name = "authSubject_id"),
//            inverseJoinColumns = @JoinColumn(name = "authUser_id"))
//    private AuthUser authUser;

    @Builder(builderMethodName = "childBuilder")
    public AuthSubject(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String subject_name) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.subjectName = subject_name;
    }
}
