package com.nc.g49_smartcrm.service;

import com.nc.g49_smartcrm.dto.UserRequest;
import com.nc.g49_smartcrm.dto.UserResponse;
import com.nc.g49_smartcrm.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse findById(Long id);

    UserResponse create(UserRequest userRequest);

    UserResponse update(Long id, UserRequest userRequest);

    void delete(Long id);

    User getCurrentUser();
}
