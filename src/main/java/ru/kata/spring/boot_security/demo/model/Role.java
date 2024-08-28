package ru.kata.spring.boot_security.demo.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
   @Transient
   @ManyToMany(mappedBy = "roles")
   private Set<User> users;


    public Role(long l, String roleUser) {
        this.id = l;
        this.name = roleUser;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}