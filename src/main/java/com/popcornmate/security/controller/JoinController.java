package com.popcornmate.security.controller;

import com.popcornmate.security.dto.JoinDTO;
import com.popcornmate.security.service.JoinService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinUser(JoinDTO joinDTO){
        try {
            joinService.joinUser(joinDTO);
            return ResponseEntity.ok().body("회원가입 성공");
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("회원가입에 실패했습니다. : "+e.getMessage());
        }
    }
    @GetMapping("/join/check-id")
    public ResponseEntity<Boolean> checkIdDuplicate(@RequestParam String id){
        return ResponseEntity.ok().body(joinService.checkIdDuplicate(id));
    }
    @GetMapping("/join/check-nickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@RequestParam String nickname){
        return ResponseEntity.ok().body(joinService.checkNicknameDuplicate(nickname));
    }
}
