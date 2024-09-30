package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Controller {
    public static void read(String idx) {
        try {
            // 명령어 설정
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "ssd", "R", idx);

            // 명령어 실행
            Process process = processBuilder.start();

            // result.txt 파일 확인
            File resultFile = new File("result.txt");
            if (resultFile.exists()) {
                System.out.println("Reading result.txt file...");

                // 파일 읽기
                try (BufferedReader br = new BufferedReader(new FileReader(resultFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("result.txt file not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void write(String idx, String value) {
        if (Integer.parseInt(idx) < 0 || Integer.parseInt(idx) > 99 ){
            System.out.println("유효하지 않은 값이다 닝겐");
            return;
        }


        try {
            // 명령어 설정
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "ssd", "W", idx, value);

            // 명령어 실행
            Process process = processBuilder.start();

            // 프로세스 종료 대기
            int exitCode = process.waitFor();
            System.out.println("Exited with error code : " + exitCode);

            // 출력
            System.out.println("저장완료!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fullRead() {
        for(int i = 0 ; i < 100 ; i++){
            read(String.valueOf(i));
        }
    }

    public static void fullWrite() {
        for(int i = 0 ; i < 100 ; i++){
            write(String.valueOf(i), "0xABCDFFFF");
        }
    }

}
