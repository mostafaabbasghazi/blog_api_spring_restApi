package com.example.demo.configrations.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDeatailsApp implements UserDetails {
    private Long id;
    private String email;
    private String password;

    private Collection<GrantedAuthority> authorities;

    public static UserDeatailsApp userDeatailsApp(User user){
        List<GrantedAuthority>authorities=user.getRoles()
        .stream().map(role->new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());

        return new UserDeatailsApp(
            user.getId(), user.getEmail(), user.getPassword(), authorities);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return this.authorities;
    }

    @Override
    public String getPassword() {
       return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
