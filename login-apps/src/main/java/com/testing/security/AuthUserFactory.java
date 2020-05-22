package com.testing.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.testing.model.Admin;
import com.testing.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public final class AuthUserFactory {
    private AuthUserFactory() {
    }
    
    public static AuthUser create(Admin admin) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new AuthUser(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPassword(),
                authorities
        );
    }

    public static AuthUser create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new AuthUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    

	public static AuthUser create(User user, Map<String, Object> attributes) {
		AuthUser authUser=AuthUserFactory.create(user);
		authUser.setAttributes(attributes);
		return authUser;
	}
	
}
