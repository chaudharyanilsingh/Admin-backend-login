package com.testing.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.testing.enums.AllEnums.Role;

import lombok.Data;

@Entity
@Data
public class Admin extends Basetimes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Email
	private String email;
	private String password;
	
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
	
	

}
