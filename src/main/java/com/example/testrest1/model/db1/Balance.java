package com.example.testrest1.model.db1;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "balance")
@Data
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset")
    private String asset;

    @Column(name = "Ascendex")
    private String Ascendex;

    @Column(name = "Binance")
    private String Binance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "asset_userid",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "asset_id", referencedColumnName = "id")})
    private List<Asset> assets;
}
