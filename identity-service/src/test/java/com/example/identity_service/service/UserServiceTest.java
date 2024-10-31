package com.example.identity_service.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.User;
import com.example.identity_service.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2003, 12, 19);
        request = UserCreationRequest.builder()
                .username("trung2003")
                .firstName("Trung")
                .lastName("Tran")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("a2su3hdu5awd3af1")
                .username("trung2003")
                .firstName("Trung")
                .lastName("Tran")
                .dob(dob)
                .build();

        user = User.builder()
                .id("a2su3hdu5awd3af1")
                .username("trung2003")
                .firstName("Trung")
                .lastName("Tran")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("a2su3hdu5awd3af1");
        Assertions.assertThat(response.getUsername()).isEqualTo("trung2003");
    }

    //    @Test
    //    void createUser_userExisted_fail() {
    //        // GIVEN
    //        when(userRepository.existsByUsername(anyString())).thenReturn(true);
    //        // WHEN
    //        var exception = assertThrows(AppException.class, () -> userService.createUser(request));
    //        // THEN
    //        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    //    }
}
