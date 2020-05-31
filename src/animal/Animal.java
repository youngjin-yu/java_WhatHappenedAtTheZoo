package animal;

import java.util.ArrayList;
import java.util.Random;

public class Animal {
    //<변수>
    private int number;// 몇 마리인지
    private String name;// 동물 이름
    private int HP;// 동물 체력
    private String type;// 동물 타입 ex) 포유류, 조류
    private int earnMoney; //동물을 만나서 싸워서 이겼을 경우 얻을 수 있는 돈
    protected int animalAvoid; // 동물이 공격을 피할 확률
    private int skinFirmness;          //피부의 단단함
    private int strengthOfTeeth;        //치아의 단단함
    protected int animalOffense;      //동물의 공격력
    protected int animalDefense;      //동물의 방어력
    public ArrayList<Animal> animalArrayList;


    //<생성자>
    public Animal() {
        this.setSkinFirmness();
        this.setStrengthOfTeeth();
    }

    //<getter, setter>
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEarnMoney() {
        return earnMoney;
    }

    public void setEarnMoney(int earnMoney) {
        this.earnMoney = earnMoney;
    }

    public int getAnimalAvoid() {
        return animalAvoid;
    }

    public void setAnimalAvoid(int animalAvoid) {
        this.animalAvoid = animalAvoid;
    }

    public synchronized int getAnimalOffense() {
        return animalOffense;
    }

    public synchronized void setAnimalOffense(int animalOffense) {
        this.animalOffense = animalOffense;
    }

    public synchronized int getAnimalDefense() {
        return animalDefense;
    }

    public synchronized void setAnimalDefense(int animalDefense) {
        this.animalDefense = animalDefense;
    }

    public int getSkinFirmness() {
        return skinFirmness;
    }

    public void setSkinFirmness() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;
        this.skinFirmness = randomNumber;
    }

    public int getStrengthOfTeeth() {
        return strengthOfTeeth;
    }

    public void setStrengthOfTeeth() {
        Random generator = new Random();//Random() 객체생성
        int randomNumber = generator.nextInt(3)+1;
        this.strengthOfTeeth = randomNumber;
    }

    public ArrayList<Animal> getAnimalArrayList() {
        return animalArrayList;
    }

    public void setAnimalArrayList(ArrayList<Animal> animalArrayList) {
        this.animalArrayList = animalArrayList;
    }

    //동물의 체력이 감소한다.
    public void animalHP_Reduced(int damage) {

        System.out.println(this.name + "의 체력이 " + damage + " 감소했습니다!");
        this.HP -= damage;
        //체력이 마이너스가 되는 경우는 0으로 바꾼다
        if(this.HP <= 0) {
            this.HP = 0;
        }
    }
}
