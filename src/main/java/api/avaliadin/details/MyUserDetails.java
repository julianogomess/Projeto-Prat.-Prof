package api.avaliadin.details;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import api.avaliadin.model.User;
 
public class MyUserDetails implements UserDetails {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
     
    public MyUserDetails(User user) {
        this.user = user;
    }
    public User getUser(){
    	return user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
    }
    
    @Override
    public String getPassword() {
        return user.getSenha();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
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
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
}