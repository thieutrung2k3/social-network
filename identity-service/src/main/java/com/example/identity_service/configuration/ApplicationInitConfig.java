package com.example.identity_service.configuration;

import java.util.HashSet;

import com.example.identity_service.repository.PermissionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.identity_service.constant.PredefinedRole;
import com.example.identity_service.entity.Permission;
import com.example.identity_service.entity.Role;
import com.example.identity_service.entity.User;
import com.example.identity_service.repository.RoleRepository;
import com.example.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {
            if (userRepository.findByUsername(ADMIN_USERNAME).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("user role")
                        .build());

                var permissions = new HashSet<Permission>();
                Permission permission = permissionRepository.save(Permission.builder()
                        .name("ADMIN_ACCESS")
                        .description("admin access")
                        .build());
                permissions.add(permission);
                Role role = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("admin role")
                        .permissions(permissions)
                        .build());

                var roles = new HashSet<Role>();
                roles.add(role);

                User user = User.builder()
                        .username(ADMIN_USERNAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("Admin user has been created with default password 'admin', please change.");
            }
        };
    }
}
