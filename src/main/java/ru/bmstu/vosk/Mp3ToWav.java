package ru.bmstu.vosk;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.File;
import java.io.IOException;

public class Mp3ToWav {
    private static String FFMPEG_PATH = "C:\\ffmpeg\\bin\\ffmpeg.exe";
    private static File output = new File("audio/demo.wav");
    public void convert(String file) throws IOException {

            FFmpeg ffmpeg = new FFmpeg(FFMPEG_PATH);
            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(file)
                    .overrideOutputFiles(true)
                    .addOutput(output.getAbsolutePath())
                    .setFormat("wav")
                    .setAudioChannels(2)
                    .setAudioSampleRate(22050)
                    .setAudioCodec("pcm_s16le")

                    .done();
            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg);
            executor.createJob(builder).run();
//            System.out.println("Generated wav file: " + output.getAbsolutePath());
        }
}
