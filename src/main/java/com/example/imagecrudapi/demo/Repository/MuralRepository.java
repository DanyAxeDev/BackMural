package com.example.imagecrudapi.demo.Repository;

import com.example.imagecrudapi.demo.Model.Mural;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MuralRepository extends JpaRepository<Mural, Long> {

    @Query("SELECT m FROM Mural m LEFT JOIN FETCH m.imagens")
    List<Mural> findAllWithImagens();
}
