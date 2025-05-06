package com.example.imagecrudapi.demo.DTO;

import com.example.imagecrudapi.demo.Model.Mural;

import java.util.List;
import java.util.stream.Collectors;

public class MuralDto {
    private Long id;
    private String nome;
    private List<ImagemDto> imagens;

    public MuralDto(Mural mural) {
        this.id = mural.getId();
        this.nome = mural.getNome();
        this.imagens = mural.getImagens().stream()
                .map(ImagemDto::new)
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ImagemDto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemDto> imagens) {
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
