package co.idesoft.architetture.hexagonal.domain.valueobjects;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserPassword {

    private final String encodedPassword;

    public UserPassword(PasswordEncoder passwordEncoder, String rawPassword) {

        this.encodedPassword = passwordEncoder.encode(rawPassword);
    }

    public String get() {
        return encodedPassword;
    }
}
