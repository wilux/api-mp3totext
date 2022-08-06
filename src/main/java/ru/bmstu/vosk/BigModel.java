package ru.bmstu.vosk;

import org.apache.commons.io.FileUtils;
import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class BigModel {
    private String result = null;

    BigModel (InputStream stream) throws IOException, UnsupportedAudioFileException {
        convertWavFile(stream);
    }

    public String getResult() {
        return result;
    }

    private void convertWavFile(InputStream stream) throws IOException, UnsupportedAudioFileException {
        LibVosk.setLogLevel(LogLevel.DEBUG);


            File targetFile = new File("audio/demo.mp3");

            FileUtils.copyInputStreamToFile(stream, targetFile);

        Mp3ToWav mp3ToWav = new Mp3ToWav();
        String file = "audio/demo.mp3";
        mp3ToWav.convert(file);

        try (Model model = new Model("model/small-en-us");
             InputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("audio/demo.wav")));
             Recognizer recognizer = new Recognizer(model, 44050)) {

            int nbytes;
            byte[] b = new byte[4096];
            while ((nbytes = ais.read(b)) >= 0) {
                if (recognizer.acceptWaveForm(b, nbytes)) {
                    // System.out.println(recognizer.getResult());
                } else {
                    // System.out.println(recognizer.getPartialResult());
                }
            }

            result = recognizer.getFinalResult();
        }
    }
}
