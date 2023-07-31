package com.example.audio;
import java.io.IOException;
import java.io.InputStream;

import com.codename1.components.MediaPlayer;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.CN;
import com.codename1.ui.Display;

public class AudioPlayer {
    private Media MEDIA = null;
    Media video = null;
    
    /*public AudioPlayer() {
        media = loadSounds("/fluffingADuck.mp3");
        media.setVolume(100);
        media.play();
    }
    public Media loadSounds(String file) {
        Media media = null;
        try {
            InputStream is = CN.getResourceAsStream(file);
            System.out.println(is);
            Media sound = MediaManager.createMedia(is, "audio/mpeg");
            return sound;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return media;
    } */

    public void playAudio(String fileName) {
        try {
            if (MEDIA == null) {
                InputStream is = CN.getResourceAsStream(fileName);
                System.out.println(is);
                MEDIA = MediaManager.createMedia(is, "audio/wav", new Runnable() {
                    @Override
                    public void run() {
                        MEDIA = null;
                    }
                });
            if (MEDIA != null && MEDIA.isPlaying() == false) {
                MEDIA.setVolume(100);
                MEDIA.play();
            } }
        }catch (IOException ioe) { 
            if(ioe.getCause() != null) {
                ioe.getCause().printStackTrace();
            }
            ioe.printStackTrace(); 
         }
    } }

