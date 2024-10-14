package Battleship_1_0;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.IOException;
import java.io.InputStream;


public class Sound {
    /**
     *used to play sounds
     * @param resourcePath filepath
     */
    public void playSound(String resourcePath) {
        try {
            //loads audio_input stream from resources
            InputStream audioSrc = getClass().getResourceAsStream(resourcePath);
            if (audioSrc == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);

            //generates a clip and opens it with the audio input stream
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            //plays the clip
            clip.start();

            //waits till the clip is fully played
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *used to loop sound
     * @param resourcePath filepath
     */
    public void loopSound(String resourcePath) {
        try {
            //loads audio_input stream from resources
            InputStream audioSrc = getClass().getResourceAsStream(resourcePath);
            if (audioSrc == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);

            //generates a clip and opens it with the audio input stream
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            //sets clip to loop constantly
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            //plays the clip
            clip.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

