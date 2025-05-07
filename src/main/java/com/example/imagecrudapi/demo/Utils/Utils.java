package com.example.imagecrudapi.demo.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class Utils {
    public static String converterHeicParaJpegBase64(String base64Heic) throws IOException, InterruptedException {
        // Criar arquivos temporários
        File heicFile = File.createTempFile("imagem", ".heic");
        File jpgFile = File.createTempFile("imagem_convertida", ".jpg");

        // Salvar o Base64 decodificado como .heic
        byte[] heicBytes = Base64.getDecoder().decode(base64Heic);
        Files.write(heicFile.toPath(), heicBytes);

        ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", heicFile.getAbsolutePath(), jpgFile.getAbsolutePath());
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();

        byte[] jpegBytes = Files.readAllBytes(jpgFile.toPath());
        String jpegBase64 = Base64.getEncoder().encodeToString(jpegBytes);

        heicFile.delete();
        jpgFile.delete();

        return jpegBase64;
    }
}
