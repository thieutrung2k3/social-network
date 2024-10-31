package com.example.identity_service.mapper;

import com.example.identity_service.dto.request.ProfileCreationRequest;
import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.repository.httpClient.ProfileClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
