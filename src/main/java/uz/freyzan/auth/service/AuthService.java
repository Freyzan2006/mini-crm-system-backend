package uz.freyzan.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.freyzan.auth.dto.AuthRequest;
import uz.freyzan.auth.dto.AuthResponse;
import uz.freyzan.auth.dto.RegisterRequest;
import uz.freyzan.user.UserEntity;
import uz.freyzan.user.UserService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userService.createUser(user);

        UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
        var jwtToken = jwtService.generateToken(userDetails);
        return new AuthResponse(jwtToken, user.getEmail(), user.getName());
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        var jwtToken = jwtService.generateToken(userDetails);
        
        // Получаем UserEntity для имени через UserService
        UserEntity user = userService.getUserByEmail(request.getEmail());
        return new AuthResponse(jwtToken, user.getEmail(), user.getName());
    }
}