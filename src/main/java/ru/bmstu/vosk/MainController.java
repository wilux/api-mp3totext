package ru.bmstu.vosk;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class MainController {
    /**
     * Разпознавание текста из аудиофайла на русском языке
     * @param file аудиофайл
     *             ВАЖНО: Файл должен быть 8khz, mono, 16 бит
     * @return Распознанная строка
     * @throws IOException
     */
    @PostMapping("/getText")
    public String getText(@RequestParam("file") MultipartFile file) throws IOException {
        // Важно обернуть именно в BufferedInputStream, иначе при распозновании будут ошибки
        BigModel model = new BigModel(new BufferedInputStream(file.getInputStream()));
        return model.getResult();
    }
}
