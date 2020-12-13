package io.baselogic.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * Main Spring boot Application class
 *
 * @author mickknutson
 * @since chapter01.00 created
 * @since chapter03.01 Added passwordEncoding(ApplicationContext) to display encoded passwords.
 * @since chapter03.01 Now students can see the encoded user passwords for adding into the DB.
 */
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


