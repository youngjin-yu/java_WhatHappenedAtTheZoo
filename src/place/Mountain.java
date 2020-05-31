package place;

import user.Human;

public class Mountain extends Map{
    public Mountain() {
        this.setName("산");

    }
    public void comeToMountain(Human human)  {

        System.out.println("현재 위치 : " + this.getName()+" 입니다.");

    }

}
