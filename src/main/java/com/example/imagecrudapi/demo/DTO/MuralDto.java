package com.example.imagecrudapi.demo.DTO;

import java.util.List;

public class MuralDto {
    private String nome;
    private List<ImagemDto> imagens;

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
}
