package uz.freyzan.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo = new UserRepository(); // создаём репозиторий сами

    public List<UserEntity> allUsers() {
        return repo.findAll();
    }

    public UserEntity createUser(UserEntity user) {
        return repo.save(user);
    }

    public UserEntity getUserById(Long id) {
        return repo.findById(id);
    }
}
