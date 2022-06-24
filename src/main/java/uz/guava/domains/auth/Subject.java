package uz.guava.domains.auth;

import jakarta.persistence.*;
import lombok.*;
import uz.guava.domains.Auditable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends Auditable {
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<AuthTest> testList = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OrderColumn(name = "type")
    private AuthUser authUser;

    @Builder(builderMethodName = "childBuilder")
    public Subject(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean deleted, String name) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, deleted);
        this.name = name;
    }
}
