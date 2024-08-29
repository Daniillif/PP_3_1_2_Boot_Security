package ru.kata.spring.boot_security.demo.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name",unique = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name) && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, users);
    }
}