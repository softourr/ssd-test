import Controller.Controller;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String command, tmp;
        while(true) {

            System.out.print("SHELL > ");
            command = sc.nextLine().trim();
            String[] tokens = command.split(" ");

            if (tokens[0].equals("exit")){
                System.out.println("BYE");
                break;
            }

            switch (tokens[0]) {
                case "help":
                    printHelp();
                    break;  // continue는 switch-case에서 break로 대체

                case "read":
                    tmp = Controller.read(tokens[1]);
                    System.out.println(tmp);
                    break;

                case "write":
                    Controller.write(tokens[1], tokens[2]);
                    break;

                case "fullread":
                    Controller.fullRead();
                    break;

                case "fullwrite":
                    Controller.fullWrite();
                    break;

                case "testapp1":
                    Controller.testApp1();
                    break;

                case "testapp2":
                    Controller.testApp2();
                    break;

                case "testapp3":
                    Controller.testApp3();
                    break;

                case "testapp4":
                    Controller.testApp4();
                    break;

                case "testapp5":
                    Controller.testApp5();
                    break;

                case "testapp6":
                    Controller.testApp6();
                    break;

                case "testapp7":
                    Controller.testApp7();
                    break;


                default:
                    System.out.println("알 수 없는 명령어 입니다: " + tokens[0]);
                    break;
            }
        }
    }

    public static void printHelp() {
        System.out.println("사용 가능한 명령어 목록:");
        System.out.println("  write [LBA] [값]  : 특정 LBA에 값을 기록합니다.");
        System.out.println("                     값은 0x로 시작하는 16진수 8자리여야 합니다. (예: 0xABCD1234)");
        System.out.println("  read [LBA]        : 특정 LBA의 값을 읽습니다.");
        System.out.println("  fullwrite         : 모든 LBA에 임의의 값을 기록합니다.");
        System.out.println("  fullread          : 모든 LBA의 값을 읽습니다.");
        System.out.println("  exit              : 프로그램을 종료합니다.");
        System.out.println("  help              : 사용 가능한 명령어를 출력합니다.");
    }

}
