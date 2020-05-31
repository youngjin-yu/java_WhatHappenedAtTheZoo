package place;

import user.Human;

//가게
public class Store extends Map {
    public Store() {
        this.setName("상점");
    }
    public void comeToStore(Human human)  {

        System.out.println("현재 위치 : " + this.getName()+" 입니다.");

    }
}
