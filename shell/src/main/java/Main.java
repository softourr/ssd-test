import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String command;
        while(true) {

            System.out.print("SHELL > ");
            command = sc.nextLine().trim();
            String[] tokens = command.split(" ");

            if (tokens[0].equals("exit")) {
                System.out.println("BYE");
                break;
            }

            if (tokens[0].equals("help")) {
                printHelp();
                continue;

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
