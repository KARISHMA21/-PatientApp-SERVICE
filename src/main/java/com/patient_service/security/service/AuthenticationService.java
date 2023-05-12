package com.patient_service.security.service;



import com.patient_service.bean.entity.PatientEntity;
import com.patient_service.security.model.AuthenticationRequest;
import com.patient_service.security.entity.Token;
import com.patient_service.security.entity.TokenType;
import com.patient_service.security.response.AuthenticationResponse;
import com.patient_service.repository.PatientRepository;

import com.patient_service.security.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PatientRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//        var savedUser = repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        saveUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
//        if(!request.getPassword().equals(user.getPassword()))
//            throw new RuntimeException("Incorrect Credentials");
//        else
//            System.out.println("Valid Credentials ... Generating Token ");
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        tokenRepository.deleteTokenByExpiredAndRevoked();
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .pid(user.getPid())
                .name(user.getName())
                .build();
    }

    private void saveUserToken(PatientEntity user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);



    }

    private void revokeAllUserTokens(PatientEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getPid());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}