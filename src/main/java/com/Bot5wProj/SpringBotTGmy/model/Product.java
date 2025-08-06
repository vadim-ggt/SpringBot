package com.Bot5wProj.SpringBotTGmy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Product {

    @Id
    private Long id;

    private String url;

    private String title;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
