package com.kir.profile_service.controller;

import com.kir.profile_service.dto.request.UserProfileCreationRequest;
import com.kir.profile_service.dto.response.UserProfileResponse;
import com.kir.profile_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/users")
    UserProfileResponse createUserProfile(@RequestBody UserProfileCreationRequest request){
        return userProfileService.createUserProfile(request);
    }

    @GetMapping("/users/{profileId}")
    UserProfileResponse getUserProfile(@PathVariable String profileId){
        return userProfileService.getUserProfile(profileId);
    }
}
