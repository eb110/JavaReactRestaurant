package figura.ApiRestaurant.role.services;

import figura.ApiRestaurant.exceptions.BadRequestException;
import figura.ApiRestaurant.exceptions.NotFoundException;
import figura.ApiRestaurant.response.Response;
import figura.ApiRestaurant.role.dto.RoleDto;
import figura.ApiRestaurant.role.entity.Role;
import figura.ApiRestaurant.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response<RoleDto> createRole(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);

        Role savedRole = roleRepository.save(role);

        return Response.<RoleDto>builder()
            .statusCode(HttpStatus.CREATED.value())
            .message("Role created successfully")
            .data(modelMapper.map(savedRole, RoleDto.class))
            .build();
    }

    @Override
    public Response<RoleDto> updateRole(RoleDto roleDto) {
        Role existingRole = roleRepository.findById(roleDto.getId()).orElseThrow(() -> new NotFoundException("Role not found"));

        if(roleRepository.findByName(roleDto.getName()).isPresent()) {
            throw new BadRequestException(String.format("Role with name '%s' already exists",  roleDto.getName()));
        }

        existingRole.setName(roleDto.getName());

        Role updatedRole = roleRepository.save(existingRole);

        return Response.<RoleDto>builder()
            .statusCode(HttpStatus.OK .value())
            .message("Role updated successfully")
            .data(modelMapper.map(updatedRole, RoleDto.class))
            .build();
    }

    @Override
    public Response<?> deleteRole(Long roleId) {
        if(!roleRepository.existsById(roleId)) {
            throw new NotFoundException("Role not found");
        }
        roleRepository.deleteById(roleId);

        return Response.builder()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .message("Role deleted successfully")
            .build();
    }

    @Override
    public Response<List<RoleDto>> getAllRoles() {
        List<Role> entityRoles = roleRepository.findAll();
        List<RoleDto> roles = entityRoles.stream().map(r -> modelMapper.map(r, RoleDto.class)).toList();

        return Response.<List<RoleDto>>builder()
            .statusCode(HttpStatus.OK.value())
            .message("List of all roles")
            .data(roles)
            .build();
    }
}
