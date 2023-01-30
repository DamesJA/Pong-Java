import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundClip {

    // all sounds will a File (the actual sound file), an AudioInputStream which is the audio input stream which is gotten from AudioSystem, and Clip which is what we will pass the AudioInputStream into when opening it
    private File file;
    private AudioInputStream audioStream;
    private Clip clip;

    public SoundClip(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.file = new File(filePath);
        this.audioStream = AudioSystem.getAudioInputStream(this.file);
        this.clip = AudioSystem.getClip();
        this.clip.open(this.audioStream);
    }

    public void startClip() {
        this.clip.start();
        // this will cause the sound to play one time and then stop. If we put 0 as an argument in this method it will let the playback go until the end of the clip and not play again afterwards
        this.clip.loop(1);

    }

    // setters
    // getters
}
