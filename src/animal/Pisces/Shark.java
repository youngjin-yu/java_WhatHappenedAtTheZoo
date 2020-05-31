package animal.Pisces;

import user.Human;

public class Shark extends Pisces{

    private Boolean isSharkDead;    //상어가 죽었는지 살았는지 알수있는 변수

    //<생성자>
    public Shark() {
        this.setNumber(5);
        this.setName("상어");
        this.setHP(1000);
        this.setEarnMoney(600);
        this.setSharkDead(false);
    }

    public Boolean getSharkDead() {
        return isSharkDead;
    }

    public void setSharkDead(Boolean sharkDead) {
        isSharkDead = sharkDead;
    }

    public void Cramp(Shark shark, Human human){//몸통박치기
        System.out.println("강철 날개 공격을 하였습니다.");
        int damage = (this.getAnimalOffense())*(this.getScales())-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }

    public void WaterGun(Shark shark, Human human){//물대포공격
        System.out.println("물대포 공격을 하였습니다.");
        int damage = (this.getAnimalOffense())*(this.getStrengthOfTeeth())-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
