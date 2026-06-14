package com.simanja.backend.service;

import com.simanja.backend.entity.UserEntity;
import com.simanja.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("User tidak ditemukan"));
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Email tidak ditemukan"));
    }

    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("Username tidak ditemukan"));
    }

    public UserEntity createUser(UserEntity user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email sudah digunakan");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username sudah digunakan");
        }

        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity updatedUser) {

        UserEntity existing =
                getUserById(updatedUser.getId());

        existing.setNama(updatedUser.getNama());
        existing.setEmail(updatedUser.getEmail());
        existing.setUsername(updatedUser.getUsername());
        existing.setTelepon(updatedUser.getTelepon());
        existing.setJenisKelamin(updatedUser.getJenisKelamin());
        existing.setTanggalLahir(updatedUser.getTanggalLahir());
        existing.setAlamat(updatedUser.getAlamat());
        existing.setProfileImagePath(updatedUser.getProfileImagePath());

        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}