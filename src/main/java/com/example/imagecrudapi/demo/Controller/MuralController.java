package com.example.imagecrudapi.demo.Controller;

import com.example.imagecrudapi.demo.DTO.ImagemDto;
import com.example.imagecrudapi.demo.DTO.MuralDto;
import com.example.imagecrudapi.demo.Model.Imagem;
import com.example.imagecrudapi.demo.Model.Mural;
import com.example.imagecrudapi.demo.Repository.ImagemRepository;
import com.example.imagecrudapi.demo.Repository.MuralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/murais")
public class MuralController {

    @Autowired
    private MuralRepository muralRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping
    public ResponseEntity<Mural> criarMural(@RequestBody MuralDto muralDto) {
        Mural mural = new Mural();
        mural.setNome(muralDto.getNome());

        List<Imagem> imagens = muralDto.getImagens().stream().map(imgDto -> {
            Imagem imagem = new Imagem();
            imagem.setBase64Data(imgDto.getBase64Data());
            imagem.setMural(mural);
            return imagem;
        }).collect(Collectors.toList());

        mural.setImagens(imagens);
        muralRepository.save(mural);
        return ResponseEntity.status(HttpStatus.CREATED).body(mural);
    }

    @GetMapping
    public ResponseEntity<List<MuralDto>> buscarTodosMurais() {
        List<Mural> murais = muralRepository.findAll();
        List<MuralDto> resposta = murais.stream().map(m -> {
            MuralDto dto = new MuralDto();
            dto.setId(m.getId());
            dto.setNome(m.getNome());
            dto.setImagens(m.getImagens().stream().map(img -> {
                ImagemDto i = new ImagemDto();
                i.setId(img.getId());
                i.setBase64Data(img.getBase64Data());
                i.setDescricao(img.getDescricao());
                return i;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mural> buscarMuralPorId(@PathVariable Long id) {
        return muralRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarMural(@PathVariable Long id) {
        if (muralRepository.existsById(id)) {
            muralRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mural> alterarNomeMural(@PathVariable Long id, @RequestBody String novoNome) {
        return muralRepository.findById(id)
                .map(mural -> {
                    mural.setNome(novoNome);
                    muralRepository.save(mural);
                    return ResponseEntity.ok(mural);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

