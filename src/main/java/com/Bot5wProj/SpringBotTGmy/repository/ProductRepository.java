package com.Bot5wProj.SpringBotTGmy.repository;

import com.Bot5wProj.SpringBotTGmy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserChatId(Long chatId);
}
