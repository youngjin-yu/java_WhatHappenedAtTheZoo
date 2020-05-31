package place;

import user.Human;

//조류들이 모여 있는 조류 공원
public class BirdPark extends Map {
    public BirdPark() {
        this.setName("조류 공원");
    }
    public void comeToBirdPark(Human human)  {

        System.out.println("현재 위치 : " + this.getName()+" 입니다.");

    }
}
