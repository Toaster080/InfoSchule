import javax.sound.sampled.*;
import java.io.*;

public class SoundPlayer {
    public static void playSound(String filename) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.err.println("⚠️ Sound konnte nicht abgespielt werden: " + filename);
        }
    }
}
