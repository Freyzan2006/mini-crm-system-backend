package uz.freyzan.user;

import uz.freyzan.user.dto.CreateUserDTO;

public class UserMapper {

    // DTO -> Entity
    public static UserEntity dtoToEntity(CreateUserDTO dto) {
        if (dto == null) return null;

        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    // Entity -> DTO (если понадобится)
    public static CreateUserDTO entityToDto(UserEntity user) {
        if (user == null) return null;

        CreateUserDTO dto = new CreateUserDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
