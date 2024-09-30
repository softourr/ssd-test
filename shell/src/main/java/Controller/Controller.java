package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Pattern;

public class Controller {
    private static final Pattern HEX_PATTERN = Pattern.compile("^0[xX][0-9a-fA-F]{1,8}$");

    public static String read(String idx) {
        try {
            if(!isNaN(idx)) {
                return "숫자가 아닙니다";
            }

            int idxTmp = Integer.parseInt(idx);
            if (idxTmp < 0 || idxTmp > 99) {
                return "0에서 99 사이의 값을 입력하시오";
            }

            // 명령어 설정
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "ssd.jar", "R", idx);

            // 명령어 실행
            Process process = processBuilder.start();


            // 프로세스 종료 대기
            int exitCode = process.waitFor();


            // result.txt 파일 확인
            File resultFile = new File("result.txt");
            if (resultFile.exists()) {
                // 파일 읽기
                try (BufferedReader br = new BufferedReader(new FileReader(resultFile))) {
                    String line;
                    if ((line = br.readLine()) != null) {
                        return line;
                    }
                }
            } else {
                System.out.println("result.txt file not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";
    }

    public static void write(String idx, String value) {
        if(!isNaN(idx)) {
            System.out.println("숫자가 아닙니다");
            return;
        }

        if (Integer.parseInt(idx) < 0 || Integer.parseInt(idx) > 99 ){
            System.out.println("유효하지 않은 값이다 닝겐");
            return;
        }


        try {
            // 명령어 설정
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "ssd.jar", "W", idx, value);

            // 명령어 실행
            Process process = processBuilder.start();

            // 프로세스 종료 대기
            int exitCode = process.waitFor();

            // 출력
            System.out.println("저장되었다!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fullRead() {
        for(int i = 0 ; i < 100 ; i++){
            String str = read(String.valueOf(i));
            System.out.println(str);
        }
    }

    public static void fullWrite() {
        for(int i = 0 ; i < 100 ; i++){
            write(String.valueOf(i), "0xABCDFFFF");
        }
    }

    private static boolean isNaN(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true; // null이나 빈 문자열은 NaN으로 간주
        }

        // 16진수 형식 검사
        if (HEX_PATTERN.matcher(str.trim()).matches()) {
            return false; // 올바른 16진수 형식이면 숫자로 간주
        }

        try {
            Double.parseDouble(str);
            return false; // 파싱 성공 시 숫자이므로 NaN이 아님
        } catch (NumberFormatException e) {
            return true; // 파싱 실패 시 NaN으로 간주
        }
    }

    public static void testApp1() {
        fullWrite();
        String str = read("0");

        for(int i = 0 ; i < 100 ; i++){
            String str2 = read(String.valueOf(i));
            if(!str.equals(str2)) {
                System.out.println("FAILED");
                return;
            }
        }
        System.out.println("SUCCESS");
    }

    public static void testApp2() {
        for(int i = 0 ; i < 30*6 ; i++){
            write(String.valueOf(i % 6), "0xAAAABBBB");
        }

        for(int i = 0 ; i < 6 ; i++){
            write(String.valueOf(i), "0x12345678");
        }

        for(int i = 0 ; i < 6 ; i++){
            String str = read(String.valueOf(i));
            if(!str.equals("0x12345678")) {
                System.out.println("FAILED");
            }
        }
        System.out.println("SUCCESS");
    }

    public static void testApp3() {
        write("999", "0x12341234");
        String str = read("999");
        System.out.println(str);
    }

    public static void testApp4() {
        write("-1", "0x12341234");
        String str = read("-1");
        System.out.println(str);
    }

    public static void testApp5() {
        write("asdf", "0x12341234");
        String str = read("asdf");
        System.out.println(str);
    }

    public static void testApp6() {
        write("0", "0xGGGGGGGG");
        String str = read("0");
        System.out.println(str);
    }

    public static void testApp7() {
        write("0", "0x1234");
        String str = read("0");
        System.out.println(str);
    }

    public static void testApp8() {
        write("0", "0x0123456789");
        String str = read("0");
        System.out.println(str);
    }

}
