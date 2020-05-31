package thread;

import user.Human;

import java.util.ArrayList;
//회복하는 스레드
public class RecoverThread implements Runnable{

    private int listIndex;
    private ArrayList<Human> kindOfCharacter;

    public RecoverThread(int listIndex, ArrayList<Human> kindOfCharacter) {
        this.listIndex = listIndex;
        this.kindOfCharacter = kindOfCharacter;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("잠을 자기 시작합니다.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        (kindOfCharacter.get(listIndex)).setHP((kindOfCharacter.get(listIndex)).getMaxHP());
        System.out.println("체력이 " + (kindOfCharacter.get(listIndex)).getHP() + " 으로 회복되었습니다.");
    }
}
