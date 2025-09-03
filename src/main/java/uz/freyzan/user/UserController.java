package uz.freyzan.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import uz.freyzan.user.dto.CreateUserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "CRUD операции с пользователями")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Получить всех пользователей")
    public List<UserEntity> all() {
        return service.allUsers();
    }

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public UserEntity create(@RequestBody CreateUserDTO dto) {
        UserEntity user = UserMapper.dtoToEntity(dto);
        return service.createUser(user);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID")
    public UserEntity getById(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
