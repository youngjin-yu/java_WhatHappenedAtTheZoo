package item;

import user.Human;

//화살
public class Arrow extends Item{
    public Arrow() {
        this.setName("화살");
        this.setPrice(50);
    }
    public void arrowIsPaid(Human human){
        human.buyItem(this.getPrice());
    }


}
