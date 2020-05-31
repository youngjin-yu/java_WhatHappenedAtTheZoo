package animal.birds;

import animal.Animal;
import user.Human;

import java.util.Random;

//조류
public class Birds extends Animal {

    private int beak;                     //새의 돌출되어 나온 입의 크기 //1: 작다 2. 중간 3. 크다
    private int claw;                     //갈고리 발톱 크기              1: 작다 2: 중간 3: 크다
    private int wing;                     //날개의 크기                //1: 작다 2: 중간 3:크다
    private int hair;                     //털이 많은지 여부 1: 털이 거의 없다 2: 털이 적당히 있다. 3: 털이 많다

    public Birds() {
        this.setBeak();
        this.setClaw();
        this.setWing();
        this.setHair();
        this.setType("조류");
        this.setAnimalAvoid();
        this.setAnimalDefense();
        this.setAnimalOffense();
    }

    public int getBeak() {
        return beak;
    }

    public void setBeak() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;
        this.beak=randomNumber;
    }

    public int getWing() {
        return wing;
    }

    public void setWing() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;
        this.wing=randomNumber;
    }

    public int getClaw() {
        return claw;
    }

    public void setClaw() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;
        this.claw = randomNumber;
    }

    public int getHair() {
        return hair;
    }

    public void setHair() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;
        this.hair = randomNumber;
    }

    public void setAnimalDefense(){
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+10;
        this.animalDefense = ((this.getSkinFirmness())+(this.getHair())+(this.getWing()))*randomNumber;
    }
    public void setAnimalOffense(){
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+15;
        this.animalOffense = (this.getBeak()+this.getClaw())*randomNumber;
    }

    public void setAnimalAvoid(){
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        this.animalAvoid = (3-(this.getWing()))*randomNumber;
    }


    public void Growl(Birds birds, Human human){//울음소리 공격
        System.out.println("울음소리 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getAnimalOffense())*(randomNumber))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
