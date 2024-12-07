package com.sahil.stock.info.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahil.stock.info.model.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    public Optional<Model> findByName(String name);
}
