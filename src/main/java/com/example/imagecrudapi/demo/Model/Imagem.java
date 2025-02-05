package com.example.imagecrudapi.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String base64Data;

    @Column(length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "mural_id", nullable = false)
    @JsonBackReference
    private Mural mural;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public Mural getMural() {
        return mural;
    }

    public void setMural(Mural mural) {
        this.mural = mural;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
