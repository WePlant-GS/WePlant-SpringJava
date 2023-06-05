package br.com.api.weplant.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.dto.UserRegisterDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complete_name", length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "user_email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false, length = 100)
    private String password;

    @Column(name = "user_status", length = 1, nullable = false)
    private Character status;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Address address;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private Phone phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Garden> gardenList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public void addGarden(Garden garden) {
        gardenList.add(garden);
    }

    public User(UserRegisterDTO userRegisterDTO) {
        this.name = userRegisterDTO.name();
        this.birthday = userRegisterDTO.birthday();
        this.username = userRegisterDTO.username();
        this.email = userRegisterDTO.email();
        this.password = userRegisterDTO.password();
        this.status = 'A';
        setAddress(userRegisterDTO.address());
        setPhone(userRegisterDTO.phone());
    }

    public User(UserNoProtectedDataDTO userNoProtectedDataDTO) {
        this.name = userNoProtectedDataDTO.name();
        this.birthday = userNoProtectedDataDTO.birthday();
        this.username = userNoProtectedDataDTO.username();
        this.email = userNoProtectedDataDTO.email();
        this.address = userNoProtectedDataDTO.address();
        this.phone = userNoProtectedDataDTO.phone();
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
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
