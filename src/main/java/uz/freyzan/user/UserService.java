package uz.freyzan.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repo = new UserRepository();

    public List<UserEntity> allUsers() {
        return repo.findAll();
    }

    public UserEntity createUser(UserEntity user) {
        return repo.save(user);
    }

    public UserEntity getUserById(Long id) {
        return repo.findById(id);
    }

    public UserEntity getUserByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = getUserByEmail(email);
        return new UserDetailsAdapter(user);
    }
}