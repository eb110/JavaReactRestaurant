package figura.ApiRestaurant.auth_users.services;

import figura.ApiRestaurant.auth_users.dto.LoginRequest;
import figura.ApiRestaurant.auth_users.dto.LoginResponse;
import figura.ApiRestaurant.auth_users.dto.RegistrationRequest;
import figura.ApiRestaurant.response.Response;

public interface AuthService {
    Response<?> register(RegistrationRequest registrationRequest);
    Response<LoginResponse> login(LoginRequest loginRequest);
}
