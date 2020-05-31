package place;

import user.Human;

public class Sea extends Map {
    public Sea() {
        this.setName("바다");
    }
    public void comeToSea(Human human)  {

        System.out.println("현재 위치 : " + this.getName()+" 입니다.");

    }
}
