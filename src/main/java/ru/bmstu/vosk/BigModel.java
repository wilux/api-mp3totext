package ru.bmstu.vosk;

import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

public class BigModel {
    private String result = null;

    BigModel (InputStream stream) {
        convertWavFile(stream);
    }

    public String getResult() {
        return result;
    }

    private void convertWavFile(InputStream stream) {
        LibVosk.setLogLevel(LogLevel.DEBUG);
        try (
                // Создадим модель для распознавания
                // "model" - директория, где находится модель
                Model model = new Model("model");
                // Преобразуем файл в нужный формат
                InputStream ais = AudioSystem.getAudioInputStream(stream);
                // Создадим обект распознавателя
                Recognizer recognizer = new Recognizer(model, 8000)
        ) {

            int nbytes;
            byte[] b = new byte[4096];
            while ((nbytes = ais.read(b)) >= 0) {
                recognizer.acceptWaveForm(b, nbytes);
            }
            result = recognizer.getFinalResult();
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
