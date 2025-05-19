package com.popcornmate.user.controller;

import com.popcornmate.security.dto.CustomUserDetails;
import com.popcornmate.user.dto.UserDto;
import com.popcornmate.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/users", produces = "application/json; charset=utf8")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PatchMapping("/profile")
    public ResponseEntity<?> updateUserById(@AuthenticationPrincipal CustomUserDetails user, @ModelAttribute UserDto updateData){
        if(updateData.getUserCode().equals(user.getUserCode())){    //수정하려는 유저와, 토큰의 유저 코드를 비교한다.
            return ResponseEntity.ok().body(userService.updateUserById(user.getUserCode(),updateData));
        } else {
            //토큰의 유저와, 수정하려는 유저의 코드가 다름
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }
    }
    @PatchMapping("/profile-image")
    public ResponseEntity<String> updateUserProfileImageById(@AuthenticationPrincipal CustomUserDetails user, @RequestPart MultipartFile profileImage){
        try {
            userService.updateUserProfileImageById(user.getUserCode(), profileImage);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{userCode}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userCode, @RequestParam String reason){
        userService.deleteUser(userCode, reason);
        return ResponseEntity.ok().build();
    }
}
