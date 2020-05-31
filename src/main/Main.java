// 동물(앵무새, 펭귄, 피라냐, 가오리)를 사냥하여 돈을 모아 활과 화살을 사서 독수리를 이기고, 그물망과 배를 사서 상어를 이기는 게임
// 게임 시작시 유저의 이름을 입력받고, 캐릭터의 종류(기사, 바이킹, 사무라이)를 보여주고 선택하게끔 합니다.
// 게임 시작시 캐릭터의 종류에 따라 공격력, 방어력, 체력 및 소지하고 있는 돈이 달라지며 공격할 수 있는 스킬도 다릅니다. (회피능력은 랜덤으로 결정됨)
// 조류의 방어력에 영향을 주는 속성은 털이 많은지 여부, 피부의 단단함 정도, 날개의 크기로 정하였고
// 조류의 공격력에 영향을 주는 속성은 부리의 크기, 발톱 크기로 정하였고
// 조류의 회피능력에 영향을 주는 속성은 날개의 크기로 정하였습니다.
// 어류의 방어력에 영향을 주는 속성은 피부의 단단함 정도, 비늘의 개수, 지느러미의 크기로 정하였고
// 어류의 공격력에 영향을 주는 속성은 치아의 단단함 정도, 비늘의 개수로 정하였고
// 어류의 회피력에 영향을 주는 속성은 지느러미의 크기로 정하였습니다.
// 유저가 동물을 사냥 시작하면 50% 확률로 유저가 먼저 공격할수도 있고 동물이 먼저 공격할 수 있습니다.
// 사용한 스레드는 낮밤스레드, 날씨스레드, 로딩스레드, 음악재생스레드. 체력회복스레드. 산사태스레드, 폭풍우스레드,
// 상어와 싸우는 스레드, 독수리와 싸우는 스레드가 있습니다.
// 유저가 잠을 자게되면 캐릭터의 종류에 맞는 최대 체력(HP)까지 체력을 올릴 수 있습니다.
package main;

import animal.Pisces.Piranhas;
import animal.Pisces.Shark;
import animal.Pisces.Stingray;
import animal.birds.Eagle;
import animal.birds.Parrot;
import animal.birds.Penguin;
import item.*;
import place.*;
import thread.*;
import user.Human;
import user.Knight;
import user.Samurai;
import user.Viking;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Map map = new Map();
        BirdPark birdPark = new BirdPark();
        PiscesPark piscesPark = new PiscesPark();
        Sea sea = new Sea();
        Mountain mountain = new Mountain();
        Store store = new Store();
        Hospital hospital = new Hospital();
        Eagle eagle = new Eagle();
        Shark shark = new Shark();
        Arrow arrow = new Arrow();
        Bow bow = new Bow();
        Bread bread = new Bread();
        CaptureNet captureNet = new CaptureNet();
        Ship ship = new Ship();
        Sword sword = new Sword();
        String username = null;
        Control control = new Control();
        Random generator = new Random();//Random() 객체생성
        int userage = -1;//사용자 나이

        int listIndex = -1;
        Boolean flag = true;
        //Music introMusic = new Music("오프닝.mp3", true);
        Runnable musicRunnable = new MusicThread("forest", false);
        Thread Musicthread = new Thread(musicRunnable);
        Musicthread.start();
        long startTime = System.currentTimeMillis();
        //startMusicthread.join();
        Scanner scanner = new Scanner(System.in);
        System.out.println("동물을 사냥하며 돈을 모아 아이템을 살 수 있습니다. 독수리와 상어를 물리쳐야 이기는 게임입니다.");
        System.out.println("이름은 어떻게 되십니까?");
        String input = scanner.next();
        username = input;
        System.out.println("종족을 선택해 주세요. 0: 기사 1: 바이킹 2: 사무라이 (0부터 2까지 숫자만 입력해주세요.)");
        input = scanner.next();
        String regEx = "^[0-2]{1}$";
        while (!input.matches(regEx)) {
            System.out.println("종족을 선택해 주세요. 0: 기사 1: 바이킹 2: 사무라이 (0부터 2까지 숫자만 입력해주세요.)");
            input = scanner.next();
        }
        Knight knight = new Knight();
        Viking viking = new Viking();
        Samurai samurai = new Samurai();
        listIndex = Integer.parseInt(input);
        ArrayList<Human> kindOfCharacter = new ArrayList<Human>();
        kindOfCharacter.add(knight);
        kindOfCharacter.add(viking);
        kindOfCharacter.add(samurai);
        (kindOfCharacter.get(listIndex)).setName(username);
        map.comeToMap((kindOfCharacter.get(listIndex)));
        Runnable loadingRunnable = new LoadingThread();
        Thread loadingthread = new Thread(loadingRunnable);
        loadingthread.start();
        loadingthread.join();
        //메인스레드 0.5초간 sleep 건다.
        Thread.sleep(500);
        System.out.println();
        showCurrentStatus(eagle, shark, listIndex, kindOfCharacter);
        //메인스레드 0.5초간 sleep 건다.
        Thread.sleep(500);
        //주기적으로 날씨가 바뀌는 스레드 적용
        ChangeWeatherJob job = new ChangeWeatherJob(knight, viking, samurai, eagle, shark, listIndex, kindOfCharacter);
        Timer jobScheduler = new Timer();
        jobScheduler.scheduleAtFixedRate(job, 0, 30000);
        //데몬스레드로 만든다. 날씨는 계속 바뀌므로.
        //jobScheduler.cancel();
        //메인스레드 0.5초간 sleep 건다.
        Thread.sleep(500);
        //낮, 밤이 바뀌는 스레드 적용
        DayAndNightJob dayAndNightJob = new DayAndNightJob(knight, viking, samurai, eagle, shark, listIndex, kindOfCharacter, control);
        Timer dayAndNightScheduler = new Timer();
        dayAndNightScheduler.scheduleAtFixedRate(dayAndNightJob, 0, 30000);
        //메인스레드 0.5초간 sleep 건다.
        Thread.sleep(500);
        while (flag) {
            //음악 재생 시작 이후 30초를 넘어가면 음악을 끈다.
            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) / 1000 > 30)
                ((MusicThread) musicRunnable).close();
            //메뉴를 보여주기 전에 메인스레드를 0.5초간 sleep 건다.
            Thread.sleep(500);
            control.menu();
            input = scanner.next();
            // 유저가 0을 입력하면 현재상태를 보여준다.
            if (input.equals("0")) {
                Thread.sleep(500);
                showCurrentStatus(eagle, shark, listIndex, kindOfCharacter);
                continue;
            } else if (input.equals("1") || input.equals("2")) {
                Runnable loadingRunnable2 = new LoadingThread();
                Thread loadingthread2 = new Thread(loadingRunnable2);
                loadingthread2.start();
                loadingthread2.join();
                int randomBirds = generator.nextInt(2);
                switch ((randomBirds)) {
                    case 0:
                        fightWithParrot(birdPark, hospital, control, generator, listIndex, flag, knight, viking, samurai, kindOfCharacter);
                        break;
                    case 1:
                        fightWithPenguin(birdPark, hospital, control, generator, listIndex, flag, knight, viking, samurai, kindOfCharacter);
                        break;
                }
            }else if (input.equals("3")||input.equals("4")) {
                Runnable loadingRunnable3 = new LoadingThread();
                Thread loadingthread3 = new Thread(loadingRunnable3);
                loadingthread3.start();
                loadingthread3.join();
                int randomPisces = generator.nextInt(2);
                switch ((randomPisces)) {
                    case 0:
                        fightWithPiranhas(piscesPark, hospital, control, generator, listIndex, flag, knight, viking, samurai, kindOfCharacter);
                        break;
                    case 1:
                        fightWithStingray(piscesPark, hospital, control, generator, listIndex, flag, knight, viking, samurai, kindOfCharacter);
                        break;
                }
            }
            else if (input.equals("5")) {
                while (flag) {
                    //상점에 오다
                    store.comeToStore((kindOfCharacter.get(listIndex)));
                    System.out.println("상점에 왔습니다.");
                    System.out.println("어떤 물건을 구매하시겠습니까? 1. 빵  2. 검  3. 화살  4. 활  5. 그물망  6. 배  ");
                    input = scanner.next();
                    if (input.equals("1")) {
                        buyBread(bread, listIndex, kindOfCharacter);
                        break;
                    } else if (input.equals("2")) {
                        buySword(sword, listIndex, kindOfCharacter);
                        break;
                    } else if (input.equals("3")) {
                        buyArrow(arrow, listIndex, kindOfCharacter);
                        break;
                    } else if (input.equals("4")) {
                        buyBow(bow, listIndex, kindOfCharacter);
                        break;
                    } else if (input.equals("5")) {
                        buyCaptureNet(captureNet, listIndex, kindOfCharacter);
                        break;
                    } else if (input.equals("6")) {
                        buyShip(ship, listIndex, kindOfCharacter);
                        break;
                    } else {
                        System.out.println("다시 입력해 주세요. 숫자 1부터 6까지 입력 가능합니다.");
                    }
                }
            } else if (input.equals("6")) {
                Runnable recoverRunnable = new RecoverThread(listIndex, kindOfCharacter);
                Thread recoverThread = new Thread(recoverRunnable);
                recoverThread.start();
                recoverThread.join();
            } else if (input.equals("7")) {
                if(eagle.getHP()<=0){
                    System.out.println("독수리를 이미 사냥하였습니다.");
                    continue;
                }
                if (((kindOfCharacter.get(listIndex)).getWhetherHaveArrow().equals(true)) && ((kindOfCharacter.get(listIndex)).getWhetherHaveBow().equals(true))) {
                    Runnable fightEagleRunnable = new FightEagleThread(eagle, mountain, listIndex, flag, generator, control, knight, viking, samurai, hospital, kindOfCharacter);
                    Thread fightEagleThread = new Thread(fightEagleRunnable);
                    fightEagleThread.start();
                    fightEagleThread.join();
                } else
                    System.out.println("독수리를 사냥하려면 활과 화살이 필요합니다.");
            } else if (input.equals("8")) {
                if(shark.getHP()<=0){
                    System.out.println("상어를 이미 사냥하였습니다.");
                    continue;
                }
                if (((kindOfCharacter.get(listIndex)).getWhetherHaveCaptureNet().equals(true)) && ((kindOfCharacter.get(listIndex)).getWhetherHaveShip().equals(true))) {
                    Runnable fightSharkRunnable = new FightSharkThread(shark, sea, listIndex, flag, generator, control, knight, viking, samurai, hospital, kindOfCharacter);
                    Thread fightSharkThread = new Thread(fightSharkRunnable);
                    fightSharkThread.start();
                    fightSharkThread.join();
                } else
                    System.out.println("상어를 사냥하려면 배와 포획 그물망이 필요합니다.");
            } else
                continue;
            //게임 종료 조건
            if ((eagle.getEagleDead()).equals(true) && ((shark.getSharkDead()).equals(true))) {
                flag = false;
            }
        }
        System.out.println("승리하였습니다.");
        System.exit(0);
    }

    private static void buyShip(Ship ship, int listIndex, ArrayList<Human> kindOfCharacter) {
        if ((kindOfCharacter.get(listIndex)).getMoney() >= (ship.getPrice())) {
            ship.shipIsPaid((kindOfCharacter.get(listIndex)));
            (kindOfCharacter.get(listIndex)).ownShip();//배를 소유하다.
            System.out.println("배를 구매하였습니다.");
            System.out.println("현재 남아있는 돈은 " + (kindOfCharacter.get(listIndex)).getMoney() + "원 입니다.");
        } else {
            System.out.println("현재 배를 살 돈이 없습니다.");
        }
    }

    private static void buyCaptureNet(CaptureNet captureNet, int listIndex, ArrayList<Human> kindOfCharacter) {
        if ((kindOfCharacter.get(listIndex)).getMoney() >= (captureNet.getPrice())) {
            captureNet.captureNetIsPaid((kindOfCharacter.get(listIndex)));
            (kindOfCharacter.get(listIndex)).ownCaptureNet();//포획 그물망을 소유하다.
            System.out.println("포획 그물망을 구매하였습니다.");
            System.out.println("현재 남아있는 돈은 " + (kindOfCharacter.get(listIndex)).getMoney() + "원 입니다.");
        } else {
            System.out.println("현재 그물망을 살 돈이 없습니다.");
        }
    }

    private static void buyBow(Bow bow, int listIndex, ArrayList<Human> kindOfCharacter) {
        if ((kindOfCharacter.get(listIndex)).getMoney() >= (bow.getPrice())) {
            bow.bowIsPaid((kindOfCharacter.get(listIndex)));
            (kindOfCharacter.get(listIndex)).ownBow();//활을 소유하다.
            System.out.println("활을 구매하였습니다.");
            System.out.println("현재 남아있는 돈은 " + (kindOfCharacter.get(listIndex)).getMoney() + "원 입니다.");
        } else {
            System.out.println("현재 활을 살 돈이 없습니다.");
        }
    }

    private static void buyArrow(Arrow arrow, int listIndex, ArrayList<Human> kindOfCharacter) {
        if ((kindOfCharacter.get(listIndex)).getMoney() >= (arrow.getPrice())) {
            arrow.arrowIsPaid((kindOfCharacter.get(listIndex)));
            (kindOfCharacter.get(listIndex)).ownArrow();//화살을 소유하다.
            System.out.println("화살을 구매하였습니다.");
            System.out.println("현재 남아있는 돈은 " + (kindOfCharacter.get(listIndex)).getMoney() + "원 입니다.");
        } else {
            System.out.println("현재 화살을 살 돈이 없습니다.");
        }
    }

    private static void buySword(Sword sword, int listIndex, ArrayList<Human> kindOfCharacter) {
        if (((kindOfCharacter.get(listIndex)).getMoney() >= (sword.getPrice())) && ((kindOfCharacter.get(listIndex)).getPower() < (kindOfCharacter.get(listIndex)).getMaxPower())) {
            sword.increaseUserPower((kindOfCharacter.get(listIndex)));
            sword.swordIsPaid((kindOfCharacter.get(listIndex)));
            (kindOfCharacter.get(listIndex)).equipSword();//대검을 장착하다.
            System.out.println("검을 구매하였습니다.");
            System.out.println("현재 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + "이고, 공격력은 " + (kindOfCharacter.get(listIndex)).getPower() + "이고, 남아있는 돈은 " + (kindOfCharacter.get(listIndex)).getMoney() + "원 입니다.");
        } else {
            System.out.println("현재 검을 살 돈이 없거나 공격력이 최대공격력과 같기 때문에 검을 구매할 수 없습니다.");
        }
    }

    private static void buyBread(Bread bread, int listIndex, ArrayList<Human> kindOfCharacter) {
        if (((kindOfCharacter.get(listIndex)).getMoney() >= (bread.getPrice())) && ((kindOfCharacter.get(listIndex)).getHP() < (kindOfCharacter.get(listIndex)).getMaxHP())) {
            bread.healUser((kindOfCharacter.get(listIndex)));
            bread.breadIsPaid((kindOfCharacter.get(listIndex)));
            System.out.println("빵을 구매하였습니다!");
            System.out.println("현재 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + "이고, 위력은 " + (kindOfCharacter.get(listIndex)).getPower() + "이고, 남아있는 돈은 " + (kindOfCharacter.get(listIndex)).getMoney() + "원 입니다.");
        } else {
            System.out.println("현재 빵을 살 돈이 없거나 체력이 최대체력과 같기 때문에 빵을 구매할 수 없습니다.");
        }
    }

    private static void fightWithStingray(PiscesPark piscesPark, Hospital hospital, Control control, Random generator, int listIndex, Boolean flag, Knight knight, Viking viking, Samurai samurai, ArrayList<Human> kindOfCharacter) throws InterruptedException {
        piscesPark.comeToPiscesPark((kindOfCharacter.get(listIndex)));
        Stingray stingray = new Stingray();
        System.out.println(stingray.getName() + " " + stingray.getNumber() + " 마리가 습격 하였습니다!");
        while (flag) {
            int randomNumber = generator.nextInt(2);        //0부터 1까지 랜덤 숫자 생성  //사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            int randomAvoid = generator.nextInt(40) + 1;    //1부터 40까지 랜덤 숫자 생성 //공격을 피할 확률
            int randomHumanSkill = generator.nextInt(2) + 1;  //1부터 2까지 랜덤 숫자 생성    // 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
            int randomAnimalSkill = generator.nextInt(3) + 1; //1부터 3까지 랜덤 숫자 생성   // 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
            // randomNumber : 사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            if (randomNumber == 0) { //  50% 확률로 사람이 동물을 공격
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < stingray.getAnimalAvoid()) {
                    System.out.println(stingray.getName() + " 이(가) 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println(stingray.getName() + " 이(가) 공격을 피하지 못하였습니다.");
                // randomHumanSkill : 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {// 기사인 경우
                    if (randomHumanSkill == 1) {
                        knight.sprintAttack(knight, stingray);
                    } else {
                        knight.hawkDash(knight, stingray);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) { //바이킹인 경우
                    if (randomHumanSkill == 1) {
                        viking.slap(viking, stingray);
                    } else {
                        viking.focusedStrike(viking, stingray);
                    }
                } else {
                    if (randomHumanSkill == 1) {
                        samurai.assaultSweep(samurai, stingray);
                    } else {
                        samurai.swiftStrike(samurai, stingray);
                    }
                }
            }
            //50% 확률로 동물이 사람을 공격
            else {
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < ((kindOfCharacter.get(listIndex)).getHumanAvoid())) {
                    System.out.println("사람이 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println("사람이 공격을 피하지 못하였습니다.");
                // randomAnimalSkill : 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {//기사인 경우
                    if (randomAnimalSkill == 1) {
                        stingray.Bubble(stingray, knight);
                    } else if (randomAnimalSkill == 2) {
                        stingray.PosionSting(stingray, knight);
                    } else {
                        stingray.Cramp(stingray, knight);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) {//바이킹인 경우
                    if (randomAnimalSkill == 1) {
                        stingray.Bubble(stingray, viking);
                    } else if (randomAnimalSkill == 2) {
                        stingray.PosionSting(stingray, viking);
                    } else {
                        stingray.Cramp(stingray, viking);
                    }
                } else {
                    if (randomAnimalSkill == 1) {
                        stingray.Bubble(stingray, samurai);
                    } else if (randomAnimalSkill == 2) {
                        stingray.PosionSting(stingray, samurai);
                    } else {
                        stingray.Cramp(stingray, samurai);
                    }
                }
            }
            System.out.println("현재 사람의 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + ", 동물의 체력은 " + stingray.getHP() + "입니다.");
            if ((kindOfCharacter.get(listIndex)).getHP() <= 0) {
                System.out.println("동물과 싸워서 사람이 졌습니다.");
                hospital.comeToHospital((kindOfCharacter.get(listIndex)));
                Runnable recoverRunnable = new RecoverThread(listIndex, kindOfCharacter);
                Thread recoverThread = new Thread(recoverRunnable);
                recoverThread.start();
                recoverThread.join();
                break;
            }
            if (stingray.getHP() <= 0) {
                System.out.println((stingray.getName())+" 이(가) 죽었습니다.");
                System.out.println("사람이 " + stingray.getEarnMoney() + "원의 돈을 얻었습니다!");
                (kindOfCharacter.get(listIndex)).setMoney((kindOfCharacter.get(listIndex)).getMoney() + stingray.getEarnMoney());
                break;
            }
            control.pressEnter();
        }
    }

    private static void fightWithPiranhas(PiscesPark piscesPark, Hospital hospital, Control control, Random generator, int listIndex, Boolean flag, Knight knight, Viking viking, Samurai samurai, ArrayList<Human> kindOfCharacter) throws InterruptedException {
        piscesPark.comeToPiscesPark((kindOfCharacter.get(listIndex)));
        Piranhas piranhas = new Piranhas();
        System.out.println(piranhas.getName() + " " + piranhas.getNumber() + " 마리가 습격 하였습니다!");
        while (flag) {
            int randomNumber = generator.nextInt(2);        //0부터 1까지 랜덤 숫자 생성  //사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            int randomAvoid = generator.nextInt(40) + 1;    //1부터 40까지 랜덤 숫자 생성 //공격을 피할 확률
            int randomHumanSkill = generator.nextInt(2) + 1;  //1부터 2까지 랜덤 숫자 생성    // 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
            int randomAnimalSkill = generator.nextInt(3) + 1; //1부터 3까지 랜덤 숫자 생성   // 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
            // randomNumber : 사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            if (randomNumber == 0) { //  50% 확률로 사람이 동물을 공격
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < piranhas.getAnimalAvoid()) {
                    System.out.println(piranhas.getName() + " 이(가) 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println(piranhas.getName() + " 이(가) 공격을 피하지 못하였습니다.");
                // randomHumanSkill : 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {// 기사인 경우
                    if (randomHumanSkill == 1) {
                        knight.sprintAttack(knight, piranhas);
                    } else {
                        knight.hawkDash(knight, piranhas);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) { //바이킹인 경우
                    if (randomHumanSkill == 1) {
                        viking.slap(viking, piranhas);
                    } else {
                        viking.focusedStrike(viking, piranhas);
                    }
                } else {
                    if (randomHumanSkill == 1) {
                        samurai.assaultSweep(samurai, piranhas);
                    } else {
                        samurai.swiftStrike(samurai, piranhas);
                    }
                }
            }
            //50% 확률로 동물이 사람을 공격
            else {
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < ((kindOfCharacter.get(listIndex)).getHumanAvoid())) {
                    System.out.println("사람이 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println("사람이 공격을 피하지 못하였습니다.");
                // randomAnimalSkill : 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {//기사인 경우
                    if (randomAnimalSkill == 1) {
                        piranhas.Bubble(piranhas, knight);
                    } else if (randomAnimalSkill == 2) {
                        piranhas.SteelTeeth(piranhas, knight);
                    } else {
                        piranhas.WaterGun(piranhas, knight);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) {//바이킹인 경우
                    if (randomAnimalSkill == 1) {
                        piranhas.Bubble(piranhas, viking);
                    } else if (randomAnimalSkill == 2) {
                        piranhas.SteelTeeth(piranhas, viking);
                    } else {
                        piranhas.WaterGun(piranhas, viking);
                    }
                } else {
                    if (randomAnimalSkill == 1) {
                        piranhas.Bubble(piranhas, samurai);
                    } else if (randomAnimalSkill == 2) {
                        piranhas.SteelTeeth(piranhas, samurai);
                    } else {
                        piranhas.WaterGun(piranhas, samurai);
                    }
                }
            }
            System.out.println("현재 사람의 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + ", 동물의 체력은 " + piranhas.getHP() + "입니다.");
            if ((kindOfCharacter.get(listIndex)).getHP() <= 0) {
                System.out.println("동물과 싸워서 사람이 졌습니다.");
                hospital.comeToHospital((kindOfCharacter.get(listIndex)));
                Runnable recoverRunnable = new RecoverThread(listIndex, kindOfCharacter);
                Thread recoverThread = new Thread(recoverRunnable);
                recoverThread.start();
                recoverThread.join();
                break;
            }
            if (piranhas.getHP() <= 0) {
                System.out.println((piranhas.getName())+" 이(가) 죽었습니다.");
                System.out.println("사람이 " + piranhas.getEarnMoney() + "원의 돈을 얻었습니다!");
                (kindOfCharacter.get(listIndex)).setMoney((kindOfCharacter.get(listIndex)).getMoney() + piranhas.getEarnMoney());
                break;
            }
            control.pressEnter();
        }
    }

    private static void fightWithPenguin(BirdPark birdPark, Hospital hospital, Control control, Random generator, int listIndex, Boolean flag, Knight knight, Viking viking, Samurai samurai, ArrayList<Human> kindOfCharacter) throws InterruptedException {
        birdPark.comeToBirdPark((kindOfCharacter.get(listIndex)));
        Penguin penguin = new Penguin();
        System.out.println(penguin.getName() + " " + penguin.getNumber() + " 마리가 습격 하였습니다!");
        while (flag) {
            int randomNumber = generator.nextInt(2);        //0부터 1까지 랜덤 숫자 생성  //사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            int randomAvoid = generator.nextInt(40) + 1;    //1부터 40까지 랜덤 숫자 생성 //공격을 피할 확률
            int randomHumanSkill = generator.nextInt(2) + 1;  //1부터 2까지 랜덤 숫자 생성    // 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
            int randomAnimalSkill = generator.nextInt(3) + 1; //1부터 3까지 랜덤 숫자 생성   // 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
            // randomNumber : 사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            if (randomNumber == 0) { //  50% 확률로 사람이 동물을 공격
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < penguin.getAnimalAvoid()) {
                    System.out.println(penguin.getName() + " 이(가) 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println(penguin.getName() + " 이(가) 공격을 피하지 못하였습니다.");
                // randomHumanSkill : 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {// 기사인 경우
                    if (randomHumanSkill == 1) {
                        knight.sprintAttack(knight, penguin);
                    } else {
                        knight.hawkDash(knight, penguin);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) { //바이킹인 경우
                    if (randomHumanSkill == 1) {
                        viking.slap(viking, penguin);
                    } else {
                        viking.focusedStrike(viking, penguin);
                    }
                } else {
                    if (randomHumanSkill == 1) {
                        samurai.assaultSweep(samurai, penguin);
                    } else {
                        samurai.swiftStrike(samurai, penguin);
                    }
                }
            }
            //50% 확률로 동물이 사람을 공격
            else {
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < ((kindOfCharacter.get(listIndex)).getHumanAvoid())) {
                    System.out.println("사람이 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println("사람이 공격을 피하지 못하였습니다.");
                // randomAnimalSkill : 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {//기사인 경우
                    if (randomAnimalSkill == 1) {
                        penguin.Growl(penguin, knight);
                    } else if (randomAnimalSkill == 2) {
                        penguin.Scratch(penguin, knight);
                    } else {
                        penguin.PeckingAttack(penguin, knight);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) {//바이킹인 경우
                    if (randomAnimalSkill == 1) {
                        penguin.Growl(penguin, viking);
                    } else if (randomAnimalSkill == 2) {
                        penguin.Scratch(penguin, viking);
                    } else {
                        penguin.PeckingAttack(penguin, viking);
                    }
                } else {
                    if (randomAnimalSkill == 1) {
                        penguin.Growl(penguin, samurai);
                    } else if (randomAnimalSkill == 2) {
                        penguin.Scratch(penguin, samurai);
                    } else {
                        penguin.PeckingAttack(penguin, samurai);
                    }
                }
            }
            System.out.println("현재 사람의 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + ", 동물의 체력은 " + penguin.getHP() + "입니다.");
            if ((kindOfCharacter.get(listIndex)).getHP() <= 0) {
                System.out.println("동물과 싸워서 사람이 졌습니다.");
                hospital.comeToHospital((kindOfCharacter.get(listIndex)));
                Runnable recoverRunnable = new RecoverThread(listIndex, kindOfCharacter);
                Thread recoverThread = new Thread(recoverRunnable);
                recoverThread.start();
                recoverThread.join();
                break;
            }
            if (penguin.getHP() <= 0) {
                System.out.println((penguin.getName())+" 이(가) 죽었습니다.");
                System.out.println("사람이 " + penguin.getEarnMoney() + "원의 돈을 얻었습니다!");
                (kindOfCharacter.get(listIndex)).setMoney((kindOfCharacter.get(listIndex)).getMoney() + penguin.getEarnMoney());
                break;
            }
            control.pressEnter();
        }
    }

    private static void fightWithParrot(BirdPark birdPark, Hospital hospital, Control control, Random generator, int listIndex, Boolean flag, Knight knight, Viking viking, Samurai samurai, ArrayList<Human> kindOfCharacter) throws InterruptedException {
        birdPark.comeToBirdPark((kindOfCharacter.get(listIndex)));
        Parrot parrot = new Parrot();
        System.out.println(parrot.getName() + " " + parrot.getNumber() + " 마리가 습격 하였습니다!");
        while (flag) {
            int randomNumber = generator.nextInt(2);        //0부터 1까지 랜덤 숫자 생성  //사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            int randomAvoid = generator.nextInt(40) + 1;    //1부터 40까지 랜덤 숫자 생성 //공격을 피할 확률
            int randomHumanSkill = generator.nextInt(2) + 1;  //1부터 2까지 랜덤 숫자 생성    // 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
            int randomAnimalSkill = generator.nextInt(3) + 1; //1부터 3까지 랜덤 숫자 생성   // 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
            // randomNumber : 사람이 먼저 공격할지, 동물이 먼저 공격할지를 결정
            if (randomNumber == 0) { //  50% 확률로 사람이 동물을 공격
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < parrot.getAnimalAvoid()) {
                    System.out.println(parrot.getName() + " 이(가) 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println(parrot.getName() + " 이(가) 공격을 피하지 못하였습니다.");
                // randomHumanSkill : 각각의 연령대에 따른 인간의 보유 공격 능력 2개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {// 기사인 경우
                    if (randomHumanSkill == 1) {
                        knight.sprintAttack(knight, parrot);
                    } else {
                        knight.hawkDash(knight, parrot);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) { //바이킹인 경우
                    if (randomHumanSkill == 1) {
                        viking.slap(viking, parrot);
                    } else {
                        viking.focusedStrike(viking, parrot);
                    }
                } else {
                    if (randomHumanSkill == 1) {
                        samurai.assaultSweep(samurai, parrot);
                    } else {
                        samurai.swiftStrike(samurai, parrot);
                    }
                }
            }
            //50% 확률로 동물이 사람을 공격
            else {
                //randomAvoid : 공격을 피할 확률
                if (randomAvoid < ((kindOfCharacter.get(listIndex)).getHumanAvoid())) {
                    System.out.println("사람이 공격을 피하였습니다.");
                    control.pressEnter();
                    continue;
                }
                System.out.println("사람이 공격을 피하지 못하였습니다.");
                // randomAnimalSkill : 각각의 동물에 따른 동물의 보유 공격 능력 3개 중 1개를 선택
                if (((kindOfCharacter.get(listIndex)).getCharacter()) == 0) {//기사인 경우
                    if (randomAnimalSkill == 1) {
                        parrot.Growl(parrot, knight);
                    } else if (randomAnimalSkill == 2) {
                        parrot.Scratch(parrot, knight);
                    } else {
                        parrot.Tackle(parrot, knight);
                    }
                } else if (((kindOfCharacter.get(listIndex)).getCharacter()) == 1) {//바이킹인 경우
                    if (randomAnimalSkill == 1) {
                        parrot.Growl(parrot, viking);
                    } else if (randomAnimalSkill == 2) {
                        parrot.Scratch(parrot, viking);
                    } else {
                        parrot.Tackle(parrot, viking);
                    }
                } else {
                    if (randomAnimalSkill == 1) {
                        parrot.Growl(parrot, samurai);
                    } else if (randomAnimalSkill == 2) {
                        parrot.Scratch(parrot, samurai);
                    } else {
                        parrot.Tackle(parrot, samurai);
                    }
                }
            }
            System.out.println("현재 사람의 체력은 " + (kindOfCharacter.get(listIndex)).getHP() + ", 동물의 체력은 " + parrot.getHP() + "입니다.");
            if ((kindOfCharacter.get(listIndex)).getHP() <= 0) {
                System.out.println("동물과 싸워서 사람이 졌습니다.");
                hospital.comeToHospital((kindOfCharacter.get(listIndex)));
                Runnable recoverRunnable = new RecoverThread(listIndex, kindOfCharacter);
                Thread recoverThread = new Thread(recoverRunnable);
                recoverThread.start();
                recoverThread.join();
                break;
            }
            if (parrot.getHP() <= 0) {
                System.out.println((parrot.getName())+" 이(가) 죽었습니다.");
                System.out.println("사람이 " + parrot.getEarnMoney() + "원의 돈을 얻었습니다!");
                (kindOfCharacter.get(listIndex)).setMoney((kindOfCharacter.get(listIndex)).getMoney() + parrot.getEarnMoney());
                break;
            }
            control.pressEnter();
        }
    }

    private static void showCurrentStatus(Eagle eagle, Shark shark, int listIndex, ArrayList<Human> kindOfCharacter) {
        System.out.println();
        System.out.println("  유저의 현재 상태:  1.유저의 방어력: " + (kindOfCharacter.get(listIndex)).getHumanDefense() + ", 유저의 현재 공격력: " + (kindOfCharacter.get(listIndex)).getPower() + ", 유저의 현재 체력: " + (kindOfCharacter.get(listIndex)).getHP());
        System.out.println("독수리의 현재 상태: 1.독수리의 방어력: " + eagle.getAnimalDefense() + ", 독수리의 현재 공격력: " + eagle.getAnimalOffense() + ",  독수리의 현재 체력: " + eagle.getHP());
        System.out.println("  상어의 현재 상태:  1.상어의 방어력: " + shark.getAnimalDefense() + ",   상어의 현재 공격력: " + shark.getAnimalOffense() + ",    상어의 현재 체력: " + shark.getHP());
        System.out.println();
    }


}
