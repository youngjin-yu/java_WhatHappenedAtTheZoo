package thread;

import animal.Pisces.Shark;
import animal.birds.Eagle;
import main.Control;
import user.Human;
import user.Knight;
import user.Samurai;
import user.Viking;

import java.util.ArrayList;
import java.util.TimerTask;
//밤, 낮 스레드
public class DayAndNightJob extends TimerTask {

    private Viking viking;
    private Knight knight;
    private Samurai samurai;
    private Eagle eagle;
    private Shark shark;
    private int listIndex;
    private ArrayList<Human> kindOfCharacter;
    private Control control;

    public DayAndNightJob(Knight pknight, Viking pviking, Samurai psamurai, Eagle peagle, Shark pshark, int plistIndex, ArrayList<Human> pkindOfCharacter, Control pcontrol) {
        this.knight = pknight;
        this.viking = pviking;
        this.samurai = psamurai;
        this.eagle = peagle;
        this.shark = pshark;
        this.listIndex = plistIndex;
        this.kindOfCharacter = pkindOfCharacter;
        this.control = pcontrol;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        //낮인 경우
        if((control.getDayOrNight())==0) {
            (kindOfCharacter.get(listIndex)).setHumanDefense(((kindOfCharacter.get(listIndex)).getHumanDefense())+2);
            eagle.setAnimalDefense((eagle.getAnimalDefense())+2);
            System.out.println();
            System.out.println("낮 입니다. 유저 및 독수리의 방어력이 2 증가합니다.");
            System.out.println();
            System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ",   유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ",   유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
            System.out.println("독수리의 현재 상태: 1.독수리의 방어력: " + eagle.getAnimalDefense() + ",   독수리의 현재 공격력: " + eagle.getAnimalOffense() + ",  독수리의 현재 체력: " + eagle.getHP());
            System.out.println("  상어의 현재 상태:  1.상어의 방어력: " + shark.getAnimalDefense() + ",   상어의 현재 공격력: " + shark.getAnimalOffense() + ",    상어의 현재 체력: " + shark.getHP());
            System.out.println();
            control.setDayOrNight(1);
        }else{ //밤인 경우
            (kindOfCharacter.get(listIndex)).setHumanDefense(((kindOfCharacter.get(listIndex)).getHumanDefense())-2);
            eagle.setAnimalDefense((eagle.getAnimalDefense())-2);
            System.out.println();
            System.out.println("밤 입니다. 유저 및 독수리의 방어력이 2 감소합니다.");
            System.out.println();
            System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ", 유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ",     유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
            System.out.println("독수리의 현재 상태: 1.독수리의 방어력: " + eagle.getAnimalDefense() + ", 독수리의 현재 공격력: " + eagle.getAnimalOffense() + ",    독수리의 현재 체력: " + eagle.getHP());
            System.out.println("  상어의 현재 상태:  1.상어의 방어력: " + shark.getAnimalDefense() + ",   상어의 현재 공격력: " + shark.getAnimalOffense() + ",    상어의 현재 체력: " + shark.getHP());
            System.out.println();
            control.setDayOrNight(0);
        }
    }
}
