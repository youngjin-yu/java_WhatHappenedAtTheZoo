package animal.Pisces;

import user.Human;

import java.util.Random;

//피라냐
public class Piranhas extends Pisces{

    public Piranhas() {
        this.setNumber(4);
        this.setName("피라냐");
        this.setHP(300);
        this.setEarnMoney(200);
    }

    public void SteelTeeth(Piranhas piranhas, Human human){//강철이빨
        System.out.println("강철 이빨 공격을 하였습니다.");
        int damage = (this.getAnimalOffense())*(this.getStrengthOfTeeth())-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
    public void WaterGun(Piranhas piranhas, Human human){//물대포공격
        System.out.println("물대포 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = (this.getAnimalOffense())*(randomNumber)-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
