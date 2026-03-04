package com.example.Calendar.model.entities;

// Spring packages
import com.example.Calendar.model.enums.Role;
import jakarta.persistence.*;

// Custom packages
import com.example.Calendar.model.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@Data
//@Builder как вариант
@EqualsAndHashCode(callSuper = true)


public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(name = "normalized_username", nullable = false)
    private String normalizedUsername;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender = Gender.OTHER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(nullable = false, name = "is_verified")
    private Boolean isVerified = false;

    public User(){ }

}
