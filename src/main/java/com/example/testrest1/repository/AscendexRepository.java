package com.example.testrest1.repository;

import com.example.testrest1.model.exchanges.Ascendex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AscendexRepository extends JpaRepository<Ascendex, Integer> {

    List<Ascendex> findByUsersId(long userId);
}
