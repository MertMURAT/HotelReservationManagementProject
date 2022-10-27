package com.example.roombookingapp.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.roombookingapp.entities.Role;
import com.example.roombookingapp.entities.User;


public class UserDetailsUser implements UserDetails{
	Boolean isEnabled;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    
    public UserDetailsUser(User user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setIsEnabled(user.isEnabled());
        setAuthorities(getGrantedAuthorities(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *
     * @param role
     * @return
     */
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(
            Role role) {
        return getGrantedAuthorities(getPrivileges(role));
    }

    /**
     * @param role Role
     * @return privileges
     */
    private List<String> getPrivileges(Role role) {

        List<String> privileges = new ArrayList<>();
        privileges.add(role.getCode());

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {

            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
