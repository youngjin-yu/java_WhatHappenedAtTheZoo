package main;

import java.util.Scanner;

public class Control {

    private int dayOrNight; // 날씨 변수 0 : 낮, 1 : 밤

    public int getDayOrNight() {
        return dayOrNight;
    }

    public void setDayOrNight(int dayOrNight) {
        this.dayOrNight = dayOrNight;
    }

    public Control() {
        this.setDayOrNight(0);
    }


    public void menu() {
        System.out.println();
        System.out.println("────────────────────────");
        System.out.println("		메뉴를 선택하여주세요");
        System.out.println("	0 : 현재 상태 확인");
        System.out.println("	1 : 북쪽 공원으로 간다.");
        System.out.println("	2 : 서쪽 공원으로 간다.");
        System.out.println("	3 : 동쪽 공원으로 간다.");
        System.out.println("	4 : 남쪽 공원으로 간다.");
        System.out.println("	5 : 상점으로 간다.");
        System.out.println("	6 : 취침한다.");
        System.out.println("	7 : 산으로 간다. (독수리 출몰지역)");
        System.out.println("	8 : 바다로 간다. (상어 출몰지역)");
        System.out.println("────────────────────────");

    }



    public void pressEnter() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
