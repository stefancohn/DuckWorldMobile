package com.example.audio;
import java.io.IOException;
import java.io.InputStream;

import com.codename1.io.FileSystemStorage;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class AudioPlayer {
    private Media MEDIA = null;
    public void playAudio(String fileName) {
        try {
            if (MEDIA == null) {
                final InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
                System.out.println(is);
                MEDIA = MediaManager.createMedia(is, "audio/wav", new Runnable() {
                    @Override
                    public void run() {
                        MEDIA = null;
                    }
                });
            }
            if (MEDIA != null && MEDIA.isPlaying() == false) {
                MEDIA.setVolume(100);
                MEDIA.play();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
