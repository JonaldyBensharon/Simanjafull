package com.simanja.backend.service;

import com.simanja.backend.dto.LoginRequest;
import com.simanja.backend.dto.RegisterRequest;
import com.simanja.backend.dto.UserResponse;
import com.simanja.backend.entity.Role;
import com.simanja.backend.entity.UserEntity;
import com.simanja.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse login(LoginRequest request) {

        UserEntity user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Email atau password salah."
                        ));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException(
                    "Email atau password salah."
            );
        }

        return toUserResponse(user);
    }

    @Transactional
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email sudah terdaftar."
            );
        }

        if (!request.getPassword()
                .equals(request.getKonfirmPassword())) {

            throw new IllegalArgumentException(
                    "Konfirmasi password tidak cocok."
            );
        }

        UserEntity user = new UserEntity();

        user.setNama(request.getNama());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole(Role.USER);

        user.setUsername(
                request.getEmail().split("@")[0]
        );

        UserEntity savedUser =
                userRepository.save(user);

        return toUserResponse(savedUser);
    }

    public UserResponse toUserResponse(
            UserEntity user
    ) {

        UserResponse response =
                new UserResponse();

        response.setId(user.getId());
        response.setNama(user.getNama());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setUsername(user.getUsername());
        response.setTelepon(user.getTelepon());
        response.setJenisKelamin(user.getJenisKelamin());
        response.setTanggalLahir(user.getTanggalLahir());
        response.setAlamat(user.getAlamat());
        response.setProfileImagePath(
                user.getProfileImagePath()
        );

        return response;
    }
}