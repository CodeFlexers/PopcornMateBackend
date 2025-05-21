package com.popcornmate.security.service;

import com.popcornmate.entity.User;
import com.popcornmate.security.dto.JoinDTO;
import com.popcornmate.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Transactional
    public void joinUser(JoinDTO joinDTO) throws Exception {
        try{
            User data = new User();
            data.setId(joinDTO.getId());
            data.setEmail(joinDTO.getEmail());
            data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));    //비밀번호 인코딩
            data.setUserRole("ROLE_USER");
            data.setNickname(joinDTO.getNickname());
            data.setActive(true);
            userRepository.save(data);
        } catch (Exception e){
            throw new Exception("회원가입 중 알 수 없는 에러가 발생했습니다. : "+e.getMessage());
        }
    }

    public Boolean checkIdDuplicate(String id) {
        return userRepository.existsById(id);
    }
    public Boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
