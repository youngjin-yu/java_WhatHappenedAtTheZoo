package item;

public class Item {
    //<변수>
    private String name; //이름
    private int price; //가격
    private int healthToRecover;//회복할 체력
    private int powerToIncrease; //증가시킬 체력

    //<생성자>
    public Item() {

    }

    //<Getter,Setter>
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHealthToRecover() {
        return healthToRecover;
    }

    public void setHealthToRecover(int healthToRecover) {
        this.healthToRecover = healthToRecover;
    }

    public int getPowerToIncrease() {
        return powerToIncrease;
    }

    public void setPowerToIncrease(int powerToIncrease) {
        this.powerToIncrease = powerToIncrease;
    }

    //사용하다
    public void useItem() {
        System.out.println(this.name + "을 사용합니다.");
    }
}
