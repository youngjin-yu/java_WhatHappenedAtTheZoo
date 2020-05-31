package item;

import user.Human;

//활
public class Bow extends Item{
    public Bow() {
        this.setName("활");
        this.setPrice(50);
    }
    public void bowIsPaid(Human human){
        human.buyItem(this.getPrice());
    }
}
