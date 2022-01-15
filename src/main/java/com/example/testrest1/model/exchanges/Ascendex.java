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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "secret")
    private String secret;

    @Column(name = "users_id")
    private long usersId;

    @Column(name = "group")
    public int group;
}
