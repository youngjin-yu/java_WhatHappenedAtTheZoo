package item;

import user.Human;

//포획그물
public class CaptureNet extends Item{

    public CaptureNet() {
        this.setName("포획 그물망");
        this.setPrice(50);
    }

    public void captureNetIsPaid(Human human){
        human.buyItem(this.getPrice());
    }
}
