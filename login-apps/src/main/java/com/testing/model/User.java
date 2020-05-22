package com.testing.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testing.enums.AllEnums.AuthProvider;
import com.testing.enums.AllEnums.Role;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
public class User extends Basetimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;
    
   
    private Boolean status;


	

}