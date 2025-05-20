package com.popcornmate.repository;

import com.popcornmate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 유저가 존재하는지 확인하는 메서드
     * @param accountId 확인할 ID
     * @return true or false
     */
    Boolean existsById(String accountId);

    @Query("SELECT u FROM User u WHERE id=:username")
    User findByAccountId(String username);

    Boolean existsByNickname(String nickname);
}
