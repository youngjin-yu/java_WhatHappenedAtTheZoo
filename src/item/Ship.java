package item;

import user.Human;

//배
public class Ship extends Item{
    public Ship() {
        this.setName("배");
        this.setPrice(50);
    }
    public void shipIsPaid(Human human){
        human.buyItem(this.getPrice());
    }
}
