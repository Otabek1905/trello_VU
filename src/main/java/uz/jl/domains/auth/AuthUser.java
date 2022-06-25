package uz.jl.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import uz.jl.domains.Auditable;
import uz.jl.enums.AuthRole;
import uz.jl.enums.Status;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends Auditable {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AuthRole role;

    @Convert(converter = Status.StatusConvertor.class)
    private Status status;

    @OneToOne(mappedBy = "authUser")
    private AuthSubject subject;

//    @OneToOne(orphanRemoval = true)
//    @JoinTable(name = "AuthSubject_authUser",
//            joinColumns = @JoinColumn(name = "authUser_id"),
//            inverseJoinColumns = @JoinColumn(name = "authSubject_id"))
//    private AuthSubject authSubject;



    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String username, String password, String email, AuthRole role, Status status) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.username = username;
        this.password = password;
        this.email = email;

        if(Objects.isNull(role))
            role = AuthRole.USER;
        this.role = role;

        if(Objects.isNull(status))
            status = Status.ACTIVE;
        this.status = status;

    }
}
