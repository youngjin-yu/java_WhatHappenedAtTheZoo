package place;

import user.Human;

//병원
public class Hospital extends Map {
    public Hospital() {
        this.setName("병원");
    }
    public void comeToHospital(Human human)  {

        System.out.println("현재 위치 : " + this.getName()+" 입니다.");

    }
}
