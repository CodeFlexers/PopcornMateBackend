package com.popcornmate.track.controller;


import com.popcornmate.security.dto.CustomUserDetails;
import com.popcornmate.track.dto.PageOnTime;
import com.popcornmate.track.service.UserActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json; charset=utf8")
public class UserActivityController {


    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @PostMapping("/movies/{movieCode}/enter")
    public ResponseEntity<?> recordUserEnter(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long movieCode, @RequestBody PageOnTime pageOnTime){

        // 등록 성공, 실패 메시지
        String result = userActivityService.recordUserEnter(movieCode, user.getUserCode(), pageOnTime);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/watched-movies/{movieCode}")
    public ResponseEntity<?> recordUserMovie(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long movieCode){

        String result = userActivityService.recordUserMovie(user.getUserCode(), movieCode);

        return ResponseEntity.ok().body(result);

    }

}
