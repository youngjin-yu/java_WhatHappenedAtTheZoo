package place;

import user.Human;


public class PiscesPark extends Map {
    //생성자
    public PiscesPark() {
        this.setName("어류 공원");

    }
    public void comeToPiscesPark(Human human)  {

        System.out.println("현재 위치 : " + this.getName()+" 입니다.");

    }


}
