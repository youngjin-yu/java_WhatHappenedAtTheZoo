package user;

import animal.Animal;

import java.util.Random;

public class Samurai extends Human {
    public Samurai() {
        this.setMoney(3000);
        this.setPower(80);
        this.setHP(900);
        this.setMaxHP(900);
        this.setMaxPower(90);
        this.setHumanDefense(80);
        this.setCharacter(2);
    }

    public void assaultSweep(Human human, Animal animal){//돌격 휩쓸기
        System.out.println(human.getName()+"이 돌격 휩쓸기 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getPower())*(randomNumber))-animal.getAnimalDefense();
        if(damage<=0)
            damage=0;
        animal.animalHP_Reduced(damage);
    }

    public void swiftStrike(Human human, Animal animal){//신속한 일격
        System.out.println(human.getName()+"이 신속한 일격을 가하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getPower())*(randomNumber))-animal.getAnimalDefense();
        if(damage<=0)
            damage=0;
        animal.animalHP_Reduced(damage);
    }
}
