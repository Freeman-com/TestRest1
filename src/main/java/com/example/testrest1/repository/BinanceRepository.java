package com.example.testrest1.repository;

import com.example.testrest1.model.exchanges.Binance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinanceRepository extends JpaRepository<Binance, Integer> {
    List<Binance> findByUsersId(long userId);
}
