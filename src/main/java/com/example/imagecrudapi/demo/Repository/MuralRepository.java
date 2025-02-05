package com.example.imagecrudapi.demo.Repository;

import com.example.imagecrudapi.demo.Model.Mural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuralRepository extends JpaRepository<Mural, Long> {}
