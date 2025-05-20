package com.popcornmate.user.service;

import com.popcornmate.common.Tool;
import com.popcornmate.entity.DeletedUser;
import com.popcornmate.entity.User;
import com.popcornmate.repository.UserRepository;
import com.popcornmate.user.dto.UserDto;
import com.popcornmate.repository.DeletedUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DeletedUserRepository deletedUserRepository;
    private final Tool tool;
    public UserService(UserRepository userRepository, DeletedUserRepository deletedUserRepository, Tool tool) {
        this.userRepository = userRepository;
        this.deletedUserRepository = deletedUserRepository;
        this.tool = tool;
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
    @Transactional
    public void updateUserProfileImageById(Integer userCode, MultipartFile profileImage) throws Exception{
        if(tool.isImage(profileImage)){
            User user = userRepository.findById(userCode).orElseThrow();
            user.setProfileImage(tool.upload(profileImage));
            userRepository.save(user);
        } else {
            throw new Exception("이미지가 아닙니다.");
        }
    }
    @Transactional
    public void deleteUser(Integer userCode, String reason) {
        User user = userRepository.findById(userCode).orElseThrow();
        user.setActive(false);
        DeletedUser deletedUser = new DeletedUser();
        deletedUser.setUser(user);
        deletedUser.setDeletedAt(LocalDateTime.now());
        deletedUser.setReason(reason);
        userRepository.save(user);
        deletedUserRepository.save(deletedUser);
    }
    @Transactional
    public void updateLastLoginTime(Integer userCode) {
        User user = userRepository.getReferenceById(userCode);
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);
    }
}
