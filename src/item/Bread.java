package item;

import user.Human;

public class Bread extends Item{
    public Bread(){
        this.setName("빵");
        this.setPrice(1500);
        this.setHealthToRecover(70);
    }

    public void healUser(Human human){
        human.recover(this.getHealthToRecover());
    }
    public void breadIsPaid(Human human){
        human.buyItem(this.getPrice());
    }
}
