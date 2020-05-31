package item;

import user.Human;

public class Sword extends Item {
    public Sword() {
        this.setName("대검");
        this.setPrice(10000);
        this.setPowerToIncrease(100);
    }

    public void increaseUserPower(Human human){
            human.increasePower(this.getPowerToIncrease());
    }


    public void swordIsPaid(Human human){
        human.buyItem(this.getPrice());
    }
}
