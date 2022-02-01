package com.example.testrest1.model.exchanges;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "binance")
@Data
public class Binance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "apikey")
    private String apiKey;

    @Column(name = "secret")
    private String secret;

    @Column(name = "user_id")
    private long usersId;

    @Column(name = "email")
    public String email;
}
