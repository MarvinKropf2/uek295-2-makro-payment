package ch.noseryoung.uek_295_payment.Domain.User;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Role;

import ch.noseryoung.uek_295_payment.Domain.Role.Roles;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull
    @Length(min = 2, max = 15)
    @Column(name = "name")
    private String name;

    @NotNull
    @Length(min = 6, max = 70)
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "role_id")
    private Roles role;
}
