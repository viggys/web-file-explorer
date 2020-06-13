package com.viggys.explorer.test.configuration;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordEncoderTest {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void encodePassword() {
        String password = "test";
        String encoded = passwordEncoder.encode(password);
        log.info("Encoded Password :: {}", encoded);
    }
}
