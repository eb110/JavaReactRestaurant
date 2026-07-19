package figura.ApiRestaurant.security;

import figura.ApiRestaurant.auth_users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser implements UserDetails {

    private User user;

    @NullMarked
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @NullMarked
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isEnabled(){
        return user.isActive();
    }
}
