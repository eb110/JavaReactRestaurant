package figura.ApiRestaurant.role.controller;

import figura.ApiRestaurant.response.Response;
import figura.ApiRestaurant.role.dto.RoleDto;
import figura.ApiRestaurant.role.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Response<RoleDto>> createRole(@RequestBody @Valid RoleDto role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @PutMapping
    public ResponseEntity<Response<RoleDto>> updateRole(@RequestBody @Valid RoleDto role) {
        return ResponseEntity.ok(roleService.updateRole(role));
    }

    @GetMapping
    public ResponseEntity<Response<List<RoleDto>>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.deleteRole(id));
    }
}
