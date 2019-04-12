package org.tykfa90.microbloggingapp.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "accounts")
@Where(clause = "status <> 'BLOCKED'")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    private String username;

    private String password;

    private String displayName;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != AccountStatus.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status ==  AccountStatus.ACTIVE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public AccountRole getRole() {
        return role;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
