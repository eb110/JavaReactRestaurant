package figura.ApiRestaurant.auth_users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "password must contain at least 4 characters")
    private String password;

    @NotBlank(message = "address is required")
    private String address;

    private List<String> roles;

}
