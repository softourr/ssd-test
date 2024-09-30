package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SsdView {
    private static final String RESULT_FILE = "result.txt";

    public void setResult(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Input value cannot be null or empty");
        }

        Path filePath = Paths.get(RESULT_FILE);
        boolean fileExisted = Files.exists(filePath);

        try {
            // 입력값이 '0x'로 시작하지 않으면 추가
            if (!value.startsWith("0x")) {
                value = "0x" + value;
            }

            // 16진수 문자열을 정수로 변환
            int intValue = Integer.parseUnsignedInt(value.substring(2), 16);

            // 정수를 8자리 16진수 문자열로 변환
            String formattedValue = String.format("0x%08X", intValue);

            // 파일에 쓰기 (기존 파일 덮어쓰기 또는 새 파일 생성)
            Files.write(filePath, formattedValue.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

            if (!fileExisted) {
                System.out.println("New file created: " + RESULT_FILE);
            }
            System.out.println("Result successfully written to " + RESULT_FILE);
        } catch (NumberFormatException e) {
            System.err.println("Invalid hexadecimal value: " + value);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
