package uz.freyzan.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для создания пользователя")
public class CreateUserDTO {

    @Schema(description = "Имя пользователя", example = "Timur")
    private String name;

    @Schema(description = "Email пользователя", example = "timur@example.com")
    private String email;
}
