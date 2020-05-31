package thread;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
//음악재생스레드
public class MusicThread implements Runnable {

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    //생성자


    public MusicThread(String name, boolean isLoop) {
        try {

            this.isLoop = isLoop;
            file = new File(("./music/"+name+".mp3"));
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //메소드
    public void close() {
        isLoop = false;
        player.close();
        //this.interrupt();// 헤당 thread를 중지 상태로 만든다. 즉 음악 을 틀어주는 작은 프로그램을 중지하는 역활

    }

    @Override
    public void run() {

        try {
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}



