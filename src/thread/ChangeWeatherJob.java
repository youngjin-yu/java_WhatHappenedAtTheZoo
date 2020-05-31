package thread;

import animal.Pisces.Shark;
import animal.birds.Eagle;
import user.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
//날씨가 바뀌는 스레드
public class ChangeWeatherJob extends TimerTask {
    private Viking viking;
    private Knight knight;
    private Samurai samurai;
    private Eagle eagle;
    private Shark shark;
    private int listIndex;
    private ArrayList<Human> kindOfCharacter;

    public ChangeWeatherJob(Knight pknight, Viking pviking, Samurai psamurai, Eagle peagle, Shark pshark, int plistIndex, ArrayList<Human> pkindOfCharacter) {
        this.knight = pknight;
        this.viking = pviking;
        this.samurai = psamurai;
        this.eagle = peagle;
        this.shark = pshark;
        this.listIndex = plistIndex;
        this.kindOfCharacter = pkindOfCharacter;
    }


    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        Random generator = new Random();
        int randomWeather = generator.nextInt(3);//0부터 2까지 랜덤 숫자 생성
        if(randomWeather==0) {
            System.out.println();
            System.out.println("비가 내리고 있습니다. 유저의 방어력이 2 감소합니다.");
            (kindOfCharacter.get(listIndex)).setHumanDefense(((kindOfCharacter.get(listIndex)).getHumanDefense())-2);
            System.out.println();
            System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ",   유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ",   유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
            System.out.println("독수리의 현재 상태: 1.독수리의 방어력: " + eagle.getAnimalDefense() + ",   독수리의 현재 공격력: " + eagle.getAnimalOffense() + ",  독수리의 현재 체력: " + eagle.getHP());
            System.out.println("  상어의 현재 상태:  1.상어의 방어력: " + shark.getAnimalDefense() + ",   상어의 현재 공격력: " + shark.getAnimalOffense() + ",    상어의 현재 체력: " + shark.getHP());
            System.out.println();
        }else if(randomWeather==1){ //2. 눈이 오는 경우 -> 유저 및 독수리 방어 능력 3 감소
            System.out.println();
            System.out.println("눈이 오고 있습니다. 유저 및 독수리의 방어력이 3 감소합니다.");
            (kindOfCharacter.get(listIndex)).setHumanDefense(((kindOfCharacter.get(listIndex)).getHumanDefense())-3);
            eagle.setAnimalDefense((eagle.getAnimalDefense())-3);
            System.out.println();
            System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ", 유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ",     유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
            System.out.println("독수리의 현재 상태: 1.독수리의 방어력: " + eagle.getAnimalDefense() + ", 독수리의 현재 공격력: " + eagle.getAnimalOffense() + ",    독수리의 현재 체력: " + eagle.getHP());
            System.out.println("  상어의 현재 상태:  1.상어의 방어력: " + shark.getAnimalDefense() + ",   상어의 현재 공격력: " + shark.getAnimalOffense() + ",    상어의 현재 체력: " + shark.getHP());
            System.out.println();
        }else{ //3. 햇빛이 강하게 비추는 경우 -> 상어 및 독수리 공격 능력 3 증가
            System.out.println();
            System.out.println("햇빛이 강하게 내리쬐고 있습니다. 상어, 독수리의 공격 능력이 3 증가합니다.");
            shark.setAnimalOffense((shark.getAnimalOffense())+3);
            eagle.setAnimalOffense((eagle.getAnimalOffense())+3);
            System.out.println();
            System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ",   유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ",    유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
            System.out.println("독수리의 현재 상태: 1.독수리의 방어력: " + eagle.getAnimalDefense() + ", 독수리의 현재 공격력: " + eagle.getAnimalOffense() + ",    독수리의 현재 체력: " + eagle.getHP());
            System.out.println("  상어의 현재 상태:  1.상어의 방어력: " + shark.getAnimalDefense() + ",   상어의 현재 공격력: " + shark.getAnimalOffense() + ",    상어의 현재 체력: " + shark.getHP());
            System.out.println();
        }
    }
}
