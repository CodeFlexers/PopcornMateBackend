package com.popcornmate.repository;

import com.popcornmate.entity.DeletedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedUserRepository extends JpaRepository<DeletedUser, Integer> {
}
