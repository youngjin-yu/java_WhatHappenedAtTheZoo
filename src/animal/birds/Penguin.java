package animal.birds;

import user.Human;

public class Penguin extends Birds{

    public Penguin() {
        this.setNumber(8);
        this.setName("펭귄");
        this.setHP(300);
        this.setEarnMoney(200);
    }

    public void Scratch(Penguin penguin, Human human) {//스크래치공격
        System.out.println("할퀴기 공격을 하였습니다.");
        int damage = ((this.getClaw())*(this.getAnimalOffense()))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }

    public void PeckingAttack(Penguin penguin,Human human){//쪼기공격
        System.out.println("부리로 쪼기 공격을 하였습니다.");
        int damage = ((this.getBeak())*(this.getAnimalOffense()))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
