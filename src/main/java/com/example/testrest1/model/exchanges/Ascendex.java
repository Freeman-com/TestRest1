package com.example.testrest1.model.exchanges;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ascendex")
@Data
public class Ascendex implements Serializable {

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

    @Column(name = "group")
    public int group;

    @Column(name = "email")
    public String email;
}
