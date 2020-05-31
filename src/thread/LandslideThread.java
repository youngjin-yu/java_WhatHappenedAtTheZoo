package thread;

import user.Human;

import java.util.ArrayList;
//산사태스레드
public class LandslideThread implements Runnable{

    private int listIndex;
    private ArrayList<Human> kindOfCharacter;

    public LandslideThread(int plistIndex, ArrayList<Human> pkindOfCharacter) {
        this.listIndex=plistIndex;
        this.kindOfCharacter=pkindOfCharacter;
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
        System.out.println("산사태가 일어났습니다.");
        System.out.println("체력이 2 감소합니다.");
        (kindOfCharacter.get(listIndex)).setHP(((kindOfCharacter.get(listIndex)).getHP())-2);
        System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ", 유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ",     유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
    }
}
