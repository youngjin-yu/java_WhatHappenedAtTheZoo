package animal.Pisces;

import animal.Animal;
import user.Human;

import java.util.Random;

public class Pisces extends Animal {

    private int scales;                     //비늘의 수 //1: 적다 2: 중간 3: 많다
    private int fin;                        //지느러미의 크기          //1: 작다 2: 중간 3: 크다

    public Pisces() {
        this.setScales();
        this.setFin();
        this.setType("어류");
        this.setAnimalAvoid();
        this.setAnimalDefense();
        this.setAnimalOffense();
    }

    public int getScales() {
        return scales;
    }

    public void setScales() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;    //1부터 3까지 랜덤 숫자 생성
        this.scales = randomNumber;
    }

    public int getFin() {
        return fin;
    }

    public void setFin() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;    //1부터 3까지 랜덤 숫자 생성
        this.fin = randomNumber;
    }

    public void setAnimalDefense(){
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+10;
        this.animalDefense = ((this.getSkinFirmness())+(this.getScales())+(this.getFin()))*randomNumber;
    }
    public void setAnimalOffense(){
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+15;
        this.animalOffense = (this.getStrengthOfTeeth()+this.getScales())*randomNumber;
    }

    public void setAnimalAvoid(){
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        this.animalAvoid = (this.getFin())*randomNumber;
    }

    public void Bubble(Pisces pisces, Human human){//거품공격
        System.out.println("거품 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = (this.getAnimalOffense())*(randomNumber);
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
