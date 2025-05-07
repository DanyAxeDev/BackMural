package com.example.imagecrudapi.demo.DTO;

import com.example.imagecrudapi.demo.Model.Imagem;

public class ImagemDto {
    private Long id;
    private String base64Data;
    private String descricao;

    public ImagemDto() {
    }

    public ImagemDto(Imagem imagem) {
        this.id = imagem.getId();
        this.base64Data = imagem.getBase64Data();
        this.descricao = imagem.getDescricao();
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
