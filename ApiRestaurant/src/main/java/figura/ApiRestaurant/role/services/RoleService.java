package figura.ApiRestaurant.role.services;

import figura.ApiRestaurant.response.Response;
import figura.ApiRestaurant.role.dto.RoleDto;

import java.util.List;

public interface RoleService {

    Response<RoleDto> createRole(RoleDto roleDto);

    Response<RoleDto> updateRole(RoleDto roleDto);

    Response<?> deleteRole(Long roleId);

    Response<List<RoleDto>> getAllRoles();

}
