package animal.birds;

import user.Human;

//독수리
public class Eagle extends Birds {

    private Boolean isEagleDead;    //독수리가 죽었는지 살았는지 알수있는 변수

    public Eagle(){
        this.setNumber(2);
        this.setName("독수리");
        this.setHP(1000);
        this.setEarnMoney(600);
        this.setEagleDead(false);
    }

    public Boolean getEagleDead() {
        return isEagleDead;
    }

    public void setEagleDead(Boolean eagleDead) {
        isEagleDead = eagleDead;
    }

    public void MetalClaw(Eagle eagle, Human human){//강철날개
        System.out.println("강철 날개 공격을 하였습니다.");
        int damage = ((this.getWing())*(this.getAnimalOffense()))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }

    public void PeckingAttack(Eagle eagle,Human human){//쪼기공격
        System.out.println("부리로 쪼기 공격을 하였습니다.");
        int damage = ((this.getBeak())*(this.getAnimalOffense()))-(human.getHumanDefense());
        if(damage<=0)
            damage=0;
        human.humanHP_Reduced(damage);
    }
}
