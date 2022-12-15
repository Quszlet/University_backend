package com.example.backend_university.service;

import com.example.backend_university.config.jwt.JwtUtil;
import com.example.backend_university.repository.UserDao;
import com.example.backend_university.request_response.LoginRequest;
import com.example.backend_university.request_response.MessageResponse;
import com.example.backend_university.request_response.ProfileInfoResponse;
import com.example.backend_university.request_response.SignupRequest;
import com.example.backend_university.exceptions.UserRegistrationException;
import com.example.backend_university.models.ERole;
import com.example.backend_university.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(PasswordEncoder passwordEncoder, UserDao userDao,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String loginUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        User user = userDao.findByEmail(loginRequest.getEmail()).get();
        user.setLoginsCount(user.getLoginsCount() + 1);
        userDao.save(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtil.generateToken(loginRequest.getEmail());
    }

    public MessageResponse registerUser(SignupRequest signupRequest) throws UserRegistrationException {
        if (userDao.existsByEmail(signupRequest.getEmail())) {
            throw new UserRegistrationException("Такая почта уже занята");
        }

        User user = new User(signupRequest.getFirstName(), signupRequest.getLastName(),
                signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()), ERole.ROLE_USER.name());
        user.setLoginsCount(0);
        user.setAvatarFilePath(null);
        userDao.save(user);
        return new MessageResponse("User CREATED");
    }

    public ProfileInfoResponse getProfile() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        return new ProfileInfoResponse(userDetails.getFirstName(), userDetails.getLastName(),
                    userDetails.getLoginsCount(), userDetails.getAuthorities().toString());
    }
}
