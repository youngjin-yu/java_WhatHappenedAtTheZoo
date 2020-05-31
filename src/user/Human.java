package user;

import java.util.Random;

public class Human {
    //변수
    private String name;                    //유저 이름
    private int character;                  //캐릭터 종류 0: 기사 1: 바이킹 2: 사무라이
    private int HP;                         //유저 체력
    private int money;                      //유저가 지니고 있는 돈
    private int power;                      //유저의 능력치
    private int maxHP;                      //유저의 최고 HP
    private int maxPower;                   //유저의 최고 Power
    private int humanDefense;               //유저의 방어력
    private int humanAvoid;                 //유저가 공격을 피하는 확률
    private Boolean isEquippedWithSword;    //대검을 장착하고 있는지 여부
    private Boolean isWhetherHaveArrow;     //화살을 가지고 있는지 여부
    private Boolean isWhetherHaveBow;       //활을 가지고 있는지 여부
    private Boolean isWhetherHaveCaptureNet;//그물망을 가지고 있는지 여부
    private Boolean isWhetherHaveShip;      //배를 가지고 있는지 여부

    //생성자
    public Human(){
        this.setHumanAvoid(humanAvoid);
        this.isEquippedWithSword=false;
        this.isWhetherHaveArrow=false;
        this.isWhetherHaveBow=false;
        this.isWhetherHaveCaptureNet=false;
        this.isWhetherHaveShip=false;
    }

    //getter, setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public Boolean getEquippedWithSword() {
        return isEquippedWithSword;
    }

    public void setEquippedWithSword(Boolean equippedWithSword) {
        isEquippedWithSword = equippedWithSword;
    }

    public synchronized int getHumanDefense() {
        return humanDefense;
    }

    public synchronized void setHumanDefense(int humanDefense) {
        this.humanDefense = humanDefense;
    }

    public int getHumanAvoid() {
        return humanAvoid;
    }

    public void setHumanAvoid(int humanAvoid) {
        Random generator = new Random();
        int randomNumber = generator.nextInt(10)+1;
        humanAvoid = randomNumber;
        this.humanAvoid = humanAvoid;
    }


    public Boolean getWhetherHaveArrow() {
        return isWhetherHaveArrow;
    }

    public void setWhetherHaveArrow(Boolean whetherHaveArrow) {
        isWhetherHaveArrow = whetherHaveArrow;
    }

    public Boolean getWhetherHaveBow() {
        return isWhetherHaveBow;
    }

    public void setWhetherHaveBow(Boolean whetherHaveBow) {
        isWhetherHaveBow = whetherHaveBow;
    }

    public Boolean getWhetherHaveCaptureNet() {
        return isWhetherHaveCaptureNet;
    }

    public void setWhetherHaveCaptureNet(Boolean whetherHaveCaptureNet) {
        isWhetherHaveCaptureNet = whetherHaveCaptureNet;
    }

    public Boolean getWhetherHaveShip() {
        return isWhetherHaveShip;
    }

    public void setWhetherHaveShip(Boolean whetherHaveShip) {
        isWhetherHaveShip = whetherHaveShip;
    }

    //사람의 체력이 감소한다.
    public void humanHP_Reduced(int damage){
        System.out.println(this.name + "의 체력이 " + damage + " 감소했습니다!");

        this.HP -= damage;

        //체력이 마이너스가 되는 경우는 0으로 바꾼다
        if(HP <= 0) {
            this.HP = 0;
        }
    }

    //사람의 체력이 회복된다
    public void recover(int i) {
        this.HP = this.getHP()+(i);
        //체력을 회복하였는데 HP가 maxHP와 같거나 크다면 HP에 maxHP 값을 넣는다.
        if(this.HP>=this.maxHP){
            this.HP=this.maxHP;
        }
    }

    //사람의 위력이 증가하다.
    public void increasePower(int i){
        this.power= this.getPower()+i;
        //위력을 증가시켰는데 위력이 최대위력과 같거나 크다면 power에 maxPower 값을 넣는다.
        if(this.power>=this.maxPower){
            this.power=this.maxPower;
        }
    }
    //사람이 아이템 값을 지불하다.
    public void buyItem(int i){
        this.money= this.getMoney()-i;
    }

    //사람이 검을 장착하다
    public void equipSword(){
        this.setEquippedWithSword(true);
    }

    //화살을 지니다.
    public void ownArrow(){
        this.setWhetherHaveArrow(true);
    }

    //활을 지니다.
    public void ownBow(){
        this.setWhetherHaveBow(true);
    }

    //그물망을 지니다.
    public void ownCaptureNet(){
        this.setWhetherHaveCaptureNet(true);
    }

    //배를 지니다.
    public void ownShip(){
        this.setWhetherHaveShip(true);
    }
}
