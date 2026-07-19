package figura.ApiRestaurant.security;

import figura.ApiRestaurant.auth_users.entity.User;
import figura.ApiRestaurant.auth_users.repository.UserRepository;
import figura.ApiRestaurant.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//class to convert custom user to UserDetails
//UserDetails is required by spring security
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {

        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new NotFoundException("User not found with username: " + username));

        return AuthUser.builder().user(user).build();
    }
}
