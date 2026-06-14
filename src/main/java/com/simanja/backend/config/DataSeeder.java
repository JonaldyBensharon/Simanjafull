package com.simanja.backend.config;

import com.simanja.backend.entity.Role;
import com.simanja.backend.entity.UserEntity;
import com.simanja.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        if (userRepository.count() > 0) {
            return;
        }

        UserEntity admin = new UserEntity();
        admin.setNama("Admin SiManja");
        admin.setEmail("admin@simanja.com");
        admin.setPassword("admin123");
        admin.setRole(Role.ADMIN);
        admin.setUsername("adminsimanja");
        admin.setTelepon("0812-0000-0001");
        admin.setJenisKelamin("Laki-laki");
        admin.setTanggalLahir("01 Januari 2000");
        admin.setAlamat("Jl. Simanja No. 1, Jakarta Pusat");

        userRepository.save(admin);

        UserEntity budi = new UserEntity();
        budi.setNama("Budi Santoso");
        budi.setEmail("budi@simanja.com");
        budi.setPassword("budi123");
        budi.setRole(Role.USER);
        budi.setUsername("budisantoso");
        budi.setTelepon("0812-3456-7890");
        budi.setJenisKelamin("Laki-laki");
        budi.setTanggalLahir("15 Juni 1995");
        budi.setAlamat("Jl. Midnight Ledger No. 88, Jakarta Selatan");

        userRepository.save(budi);

        UserEntity siti = new UserEntity();
        siti.setNama("Siti Rahayu");
        siti.setEmail("siti@simanja.com");
        siti.setPassword("siti123");
        siti.setRole(Role.USER);
        siti.setUsername("sitirahayu");
        siti.setTelepon("0856-7890-1234");
        siti.setJenisKelamin("Perempuan");
        siti.setTanggalLahir("20 Maret 1998");
        siti.setAlamat("Jl. Mawar No. 12, Bandung");

        userRepository.save(siti);

        System.out.println("DATA SEEDER BERHASIL DIJALANKAN");
        System.out.println(
                "Jumlah user = " +
                        userRepository.count()
        );
    }
}