package ru.bmstu.vosk;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

@RestController
public class MainController {

    @PostMapping("/getText")
    public String getText(@RequestParam("file") MultipartFile file) throws IOException, UnsupportedAudioFileException {
        BigModel model = new BigModel(new BufferedInputStream(file.getInputStream()));
        return model.getResult();
    }
}
