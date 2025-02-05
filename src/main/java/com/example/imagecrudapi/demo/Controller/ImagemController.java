package com.example.imagecrudapi.demo.Controller;

import com.example.imagecrudapi.demo.DTO.ImagemDto;
import com.example.imagecrudapi.demo.Model.Imagem;
import com.example.imagecrudapi.demo.Model.Mural;
import com.example.imagecrudapi.demo.Repository.ImagemRepository;
import com.example.imagecrudapi.demo.Repository.MuralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private MuralRepository muralRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @GetMapping("/{muralId}")
    public ResponseEntity<List<Imagem>> buscarImagensPorMural(@PathVariable Long muralId) {
        List<Imagem> imagens = imagemRepository.findByMuralId(muralId);
        if (imagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagens);
    }

    @PostMapping("/{muralId}")
    public ResponseEntity<Object> adicionarImagem(@PathVariable Long muralId, @RequestBody ImagemDto imagemDto) {
        Optional<Mural> muralOpt = muralRepository.findById(muralId);
        if (muralOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Mural não encontrado"));
        }

        Mural mural = muralOpt.get();
        Imagem imagem = new Imagem();

        String base64Data = imagemDto.getBase64Data();
        String descricao = imagemDto.getDescricao();
        if (base64Data == null || base64Data.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Base64 da imagem está vazio"));
        }

        imagem.setBase64Data(base64Data);
        imagem.setDescricao(descricao);
        imagem.setMural(mural);

        try {
            imagemRepository.save(imagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(imagem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Erro ao salvar a imagem", "error", e.getMessage()));
        }
    }

    @DeleteMapping("/{muralId}/{id}")
    public ResponseEntity<Void> deletarImagemDeMural(@PathVariable Long muralId, @PathVariable Long id) {
        if (imagemRepository.existsById(id)) {
            imagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{imagemId}")
    public ResponseEntity<String> atualizarDescricao(@PathVariable Long imagemId,
                                                     @RequestBody String descricao) {
        Optional<Imagem> imagemOptional = imagemRepository.findById(imagemId);
        if (!imagemOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada");
        }

        Imagem imagem = imagemOptional.get();
        imagem.setDescricao(descricao);
        imagemRepository.save(imagem);
        return ResponseEntity.status(HttpStatus.OK).body("Descrição atualizada com sucesso");
    }
}

