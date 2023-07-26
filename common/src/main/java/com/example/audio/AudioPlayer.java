package com.example.audio;
import java.io.IOException;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.CN;
import com.codename1.ui.Display;

public class AudioPlayer {
    private Media MEDIA = null;
    public Media media;

    public AudioPlayer() {
        media = loadSounds("/fluffingADuck.wav");
        media.setVolume(100);
        media.play();
    }
    public Media loadSounds(String file) {
        Media media = null;
        try {
            InputStream is = CN.getResourceAsStream(file);
            System.out.println(is);
            media = MediaManager.createMedia(is, "audio/wav");
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return media;
    }
    /*public void playAudio(String fileName) {
        try {
            if (MEDIA == null) {
                InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
                System.out.println(is);
                MEDIA = MediaManager.createMedia(is, "audio/mp3", new Runnable() {
                    @Override
                    public void run() {
                        MEDIA = null;
                    }
                });
            if (MEDIA != null && MEDIA.isPlaying() == false) {
                MEDIA.setVolume(100);
                MEDIA.play();
            } }
        } catch (IOException ioe) { ioe.printStackTrace(); }
    }*/
}
