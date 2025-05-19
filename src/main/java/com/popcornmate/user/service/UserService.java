package com.popcornmate.user.service;

import com.popcornmate.entity.User;
import com.popcornmate.security.repository.UserRepository;
import com.popcornmate.user.dto.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto updateUserById(Integer userCode, UserDto updateData) {
        User userEntity = userRepository.findById(userCode).orElseThrow();
        // 프로필 사진만 변경할 때
        if(updateData.getNickname()==null){
            userEntity.setProfileImage(updateData.getProfileImage());
        // 닉네임만 변경할 때
        } else if(updateData.getProfileImage()==null){
            userEntity.setNickname(updateData.getNickname());
        // 둘 다 동시에 변경할 때
        } else {
            userEntity.setNickname(updateData.getNickname());
            userEntity.setProfileImage(updateData.getProfileImage());
        }
        userEntity = userRepository.save(userEntity);
        return new UserDto(
                userEntity.getUserCode(),
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getProfileImage(),
                userEntity.getLastLoginTime(),
                userEntity.getNickname()
        );
    }
}
