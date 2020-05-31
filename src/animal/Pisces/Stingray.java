package animal.Pisces;

import user.Human;

import java.util.Random;

//가오리
public class Stingray extends Pisces{

    public Stingray() {
        this.setNumber(6);
        this.setName("가오리");
        this.setHP(400);
        this.setEarnMoney(450);
    }

    public void PosionSting(Stingray stingray, Human human){//독 공격
        System.out.println("가오리가 독 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = (this.getAnimalOffense())*(randomNumber)-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }

    public void Cramp(Stingray stingray, Human human){//몸통박치기
        System.out.println("몸통 박치기 공격을 하였습니다.");
        int damage = (this.getAnimalOffense())*(this.getScales())-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
