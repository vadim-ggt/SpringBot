package com.Bot5wProj.SpringBotTGmy.service;

import com.Bot5wProj.SpringBotTGmy.repository.ProductRepository;
import com.Bot5wProj.SpringBotTGmy.repository.UserRepository;
import org.jvnet.hk2.annotations.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }



}
