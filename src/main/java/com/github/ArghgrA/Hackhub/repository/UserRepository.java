package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository<T extends AbstractUser> extends JpaRepository<T, UUID> {
}
