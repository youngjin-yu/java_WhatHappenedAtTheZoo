package thread;

import animal.Pisces.Shark;
import main.Control;
import place.Hospital;
import place.Sea;
import user.Human;
import user.Knight;
import user.Samurai;
import user.Viking;

import java.util.ArrayList;
import java.util.Random;

//상어를 공격하는 스레드
public class FightSharkThread implements Runnable{

    private Shark shark;
    private Sea sea;
    private int listIndex;
    private Boolean flag;
    private Random generator;
    private Control control;
    private Knight knight ;
    private Viking viking ;
    private Samurai samurai ;
    private Hospital hospital;
    private ArrayList<Human> kindOfCharacter;

    public FightSharkThread(Shark pshark, Sea psea, int plistIndex, Boolean pflag, Random pgenerator, Control pcontrol, Knight pknight, Viking pviking, Samurai psamurai, Hospital phospital, ArrayList<Human> pkindOfCharacter) {

        this.shark=pshark;
        this.sea=psea;
        this.listIndex=plistIndex;
        this.flag=pflag;
        this.generator=pgenerator;
        this.control=pcontrol;
        this.knight=pknight;
        this.viking=pviking;
        this.samurai=psamurai;
        this.hospital=phospital;
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

        sea.comeToSea((kindOfCharacter.get(listIndex)));//바다로 맵 변경
        System.out.println("바다로 가는 도중에 "+shark.getName() + " 이(가) " + shark.getNumber() + "마리가 습격하였습니다!");
        while (flag) {
            //50% 확률로 폭풍우 발생
            int randomStorm = generator.nextInt(2);
            if(randomStorm==0){
                Runnable rainStormRunnable = new RainStormThread(listIndex,kindOfCharacter);
                Thread rainStormThread = new Thread(rainStormRunnable);
                rainStormThread.start();
                try {
                    rainStormThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int randomNumber = generator.nextInt(2);        //0부터 1까지 랜덤 숫자 생성  //사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            int randomAvoid = generator.nextInt(40) + 1;    //1부터 40까지 랜덤 숫자 생성 //공격을 피할 확률
            int randomHumanSkill = generator.nextInt(2)+1;  //1부터 2까지 랜덤 숫자 생성    // 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
            int randomAnimalSkill = generator.nextInt(3)+1; //1부터 3까지 랜덤 숫자 생성   // 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
            //50% 확률로 사람이 동물을 공격
            if (randomNumber == 0) {
                if(randomAvoid<shark.getAnimalAvoid()){
                    System.out.println(shark.getName() + " 이(가) 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println(shark.getName() + " 이(가) 공격을 피하지 못하였습니다.");
                if(((kindOfCharacter.get(listIndex)).getCharacter())==0){//기사인 경우
                    if(randomHumanSkill==1){
                        knight.sprintAttack(knight,shark);}
                    else{
                        knight.hawkDash(knight,shark);}
                }else if(((kindOfCharacter.get(listIndex)).getCharacter())==1){
                    if(randomHumanSkill==1){
                        viking.slap(viking,shark);}
                    else{
                        viking.focusedStrike(viking,shark);}
                }else {
                    if(randomHumanSkill==1){
                        samurai.assaultSweep(samurai,shark);}
                    else{
                        samurai.swiftStrike(samurai,shark);}
                }
            }
            //50% 확률로 동물이 사람을 공격
            else {
                if(randomAvoid<((kindOfCharacter.get(listIndex)).getHumanAvoid())){
                    System.out.println("사람이 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println("사람이 공격을 피하지 못하였습니다.");
                if(((kindOfCharacter.get(listIndex)).getCharacter())==0){   //기사인 경우
                    if(randomAnimalSkill==1){ shark.Cramp(shark, knight);
                    }else if(randomAnimalSkill==2){ shark.WaterGun(shark, knight);
                    }else{ shark.Bubble(shark, knight);}
                }else if(((kindOfCharacter.get(listIndex)).getCharacter())==1){     //바이킹인 경우
                    if(randomAnimalSkill==1){ shark.Cramp(shark, viking);
                    }else if(randomAnimalSkill==2){ shark.WaterGun(shark, viking);
                    }else{ shark.Bubble(shark, viking);}
                }else {
                    if(randomAnimalSkill==1){ shark.Cramp(shark, samurai);
                    }else if(randomAnimalSkill==2){ shark.WaterGun(shark, samurai);
                    }else{ shark.Bubble(shark, samurai);}
                }
            }
            System.out.println("현재 사람의 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + ", 동물의 체력은 " + shark.getHP() + "입니다.");
            if ((kindOfCharacter.get(listIndex)).getHP() <= 0) {
                System.out.println("동물과 싸워서 사람이 졌습니다.");
                hospital.comeToHospital((kindOfCharacter.get(listIndex)));
                Runnable recoverRunnable = new RecoverThread(listIndex, kindOfCharacter);
                Thread recoverThread = new Thread(recoverRunnable);
                recoverThread.start();
                try {
                    recoverThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            if (shark.getHP() <= 0) {
                System.out.println(shark.getName()+" 이(가) 죽었습니다.");
                System.out.println("사람이 " + shark.getEarnMoney() + "원의 돈을 얻었습니다!");
                (kindOfCharacter.get(listIndex)).setMoney((kindOfCharacter.get(listIndex)).getMoney() + shark.getEarnMoney());
                shark.setSharkDead(true);
                break;
            }
            control.pressEnter();
        }
    }
}
