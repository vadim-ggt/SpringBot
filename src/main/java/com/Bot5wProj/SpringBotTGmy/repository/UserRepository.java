package com.Bot5wProj.SpringBotTGmy.repository;

import com.Bot5wProj.SpringBotTGmy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
