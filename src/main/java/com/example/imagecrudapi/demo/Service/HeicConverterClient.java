package com.example.imagecrudapi.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class HeicConverterClient {

    @Value("${converter.heic.url}")
    private String converterUrl;

    public String converterHeicParaJpegBase64(String base64Heic) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> requestBody = Map.of("base64", base64Heic);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(converterUrl, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return (String) response.getBody().get("jpegBase64");
            } else {
                throw new RuntimeException("Erro na conversão: " + response.getStatusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao se comunicar com o conversor HEIC", e);
        }
    }
}

