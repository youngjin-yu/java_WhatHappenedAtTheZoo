package user;

import animal.Animal;

import java.util.Random;

public class Viking extends Human {
    public Viking() {
        this.setMoney(2000);
        this.setPower(75);
        this.setHP(700);
        this.setMaxHP(700);
        this.setMaxPower(85);
        this.setHumanDefense(75);
        this.setCharacter(1);
    }

    public void slap(Human human, Animal animal){//덮쳐 찌르기 공격
        System.out.println(human.getName()+"이 덮쳐 찌르기 공격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getPower())*(randomNumber))-(animal.getAnimalDefense());
        if(damage<=0)
            damage=0;
        animal.animalHP_Reduced(damage);
    }

    public void focusedStrike(Human human, Animal animal){//집중의 일격
        System.out.println(human.getName()+"이 집중의 일격을 하였습니다.");
        Random generator = new Random();
        int randomNumber = generator.nextInt(3)+1;
        int damage = ((this.getPower())*(randomNumber))-(animal.getAnimalDefense());
        if(damage<=0)
            damage=0;
        animal.animalHP_Reduced(damage);
    }
}
