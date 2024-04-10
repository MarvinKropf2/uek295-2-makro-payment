package ch.noseryoung.uek_295_payment.Domain.Role;


import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import ch.noseryoung.uek_295_payment.Domain.User.User;
import ch.noseryoung.uek_295_payment.Domain.authority.Authorities;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Length(min = 5, max = 20)
    @Column(name = "role_name")
    private String roleName;

    @JoinTable(name = "roles_authorities", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authorities> authorities;

    @OneToMany(mappedBy = "role")
    private Set<User> users;                                
}