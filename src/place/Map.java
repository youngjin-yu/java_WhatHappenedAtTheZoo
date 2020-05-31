package place;

import user.Human;

public class Map {

    //변수
    private String name; // 이름
    private boolean mapEntry; // 맵 입장 여부

    //생성자
    public Map() {
        this.name="공원";
    }

    //Getter,Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMapEntry() {
        return mapEntry;
    }

    public void setMapEntry(boolean mapEntry) {
        this.mapEntry = mapEntry;
    }

    //<메소드>
    //유저가 동물원으로 들어온다
    public void comeToMap(Human human)  {

        this.setMapEntry(true);
        System.out.println("현재 위치 : " + this.name+" 입니다.");

    }

    //동물원에서 나간다
    public void outOfMap() {
        // 동물원 입장 상태가 false가 된다
        this.mapEntry = false;
    }
}
