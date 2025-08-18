package com.Bot5wProj.SpringBotTGmy.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // автоинкремент

    private String article; // артикул WB
    private String url;
    private String title;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
