package com.kir.profile_service.mapper;

import com.kir.profile_service.dto.request.UserProfileCreationRequest;
import com.kir.profile_service.dto.response.UserProfileResponse;
import com.kir.profile_service.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileCreationRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
