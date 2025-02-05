package com.example.imagecrudapi.demo.Repository;

import com.example.imagecrudapi.demo.Model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    List<Imagem> findByMuralId(Long muralId);
}
