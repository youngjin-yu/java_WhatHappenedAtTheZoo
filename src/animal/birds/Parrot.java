package animal.birds;

import user.Human;

import java.util.Random;

//앵무새
public class Parrot extends Birds{

    public Parrot(){
        this.setNumber(8);
        this.setName("앵무새");
        this.setHP(300);
        this.setEarnMoney(200);
    }

    public void Scratch(Parrot parrot, Human human){//스크래치공격
        System.out.println("할퀴기 공격을 하였습니다.");
        int damage = ((this.getClaw())*(this.getAnimalOffense()))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }

    public void Tackle(Parrot parrot, Human human){//태클공격
        System.out.println("몸통 박치기 공격을 하였습니다.");
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;    //1부터 3까지 랜덤 숫자 생성
        int damage = ((randomNumber)*(this.getAnimalOffense()))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
