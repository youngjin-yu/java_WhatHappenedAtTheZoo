package user;

import animal.Animal;

import java.util.Random;

public class Knight extends Human {
    public Knight() {
        this.setName("기사");
        this.setMoney(1000);
        this.setPower(70);
        this.setHP(500);
        this.setMaxHP(500);
        this.setMaxPower(80);
        this.setHumanDefense(70);
        this.setCharacter(0);
    }

    public void sprintAttack(Human human, Animal animal){//전력 질주 공격
        System.out.println(human.getName()+"이 전력질주 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getPower())*(randomNumber))-(animal.getAnimalDefense());
        if(damage<=0)
            damage=0;
        animal.animalHP_Reduced(damage);
    }

    public void hawkDash(Human human, Animal animal){//매의 돌진 공격
        System.out.println(human.getName()+"이 매의 돌진 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getPower())*(randomNumber))-(animal.getAnimalDefense());
        if(damage<=0)
            damage=0;
        animal.animalHP_Reduced(damage);
    }
}
