package org.example.eventmanagement.entity;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.XmlTransient;
import org.example.eventmanagement.config.DateAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.*;
import java.util.stream.Collectors;


public class User implements UserDetails {

    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String roles;

    private Date createdAt;
    private Date updatedAt;

    private Collection<? extends GrantedAuthority> authorities;


    public User(String email, String password, String roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.authorities = Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public User() {}

    public User(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities();
        this.roles = user.getRoles();
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement // Exclude password from XML
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class) // Adapter for Date fields
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class) // Adapter for Date fields
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @XmlTransient
    @Override
    public String getUsername() {
        return email;
    }

    @XmlTransient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @XmlTransient
    @Override
    public boolean isEnabled() {
        return true;
    }
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
