package com.Bot5wProj.SpringBotTGmy.service;

import com.Bot5wProj.SpringBotTGmy.model.User;
import com.Bot5wProj.SpringBotTGmy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(Long chatId, String username) {
    if(!userRepository.existsById(chatId)) {
        User user = User.builder()
                .chatId(chatId)
                .username(username)
                .build();
        userRepository.save(user);
         }
    }

}
