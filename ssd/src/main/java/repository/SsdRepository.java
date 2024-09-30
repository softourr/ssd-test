package repository;

import model.Ssd;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SsdRepository {
    private static SsdRepository instance;
    private Ssd ssd;
    private static final String NAND_FILE = "nand.txt";
    private static final int SSD_SIZE = 100;

    public SsdRepository() {
        this.ssd = Ssd.getInstance();
        loadFromNand();
    }

    public String read(int index) {
        if (index < 0 || index >= SSD_SIZE) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int data = ssd.getData(index);
        return intToString(data);
    }

    public void write(int index, String value) {
        if (index < 0 || index >= SSD_SIZE) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int valueData = stringToInt(value);
        ssd.setData(index, valueData);
        saveToNand(index, valueData);
    }

    private int stringToInt(String str) {
        if (str == null || !str.startsWith("0x")) {
            throw new NumberFormatException("Invalid hex string format. Must start with '0x'.");
        }

        String numberPart = str.substring(2);
        return Integer.parseUnsignedInt(numberPart, 16);
    }

    private String intToString(int number) {
        return String.format("0x%08X", number);
    }

    private void saveToNand(int index, int value) {
        try {
            RandomAccessFile file = new RandomAccessFile(NAND_FILE, "rw");
            file.seek(index * 4L); // 각 int는 4바이트
            file.writeInt(value);
            file.close();
        } catch (IOException e) {
            System.err.println("Error writing to NAND file: " + e.getMessage());
        }
    }

    private void loadFromNand() {
        Path file = Paths.get(NAND_FILE);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
                initializeNandFile();
                System.out.println("New NAND file created and initialized.");
            } catch (IOException e) {
                System.err.println("Failed to create NAND file: " + e.getMessage());
                return;
            }
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(NAND_FILE))) {
            for (int i = 0; i < SSD_SIZE; i++) {
                int value = dis.readInt();
                ssd.setData(i, value);
            }
        } catch (EOFException e) {
            // 파일의 끝에 도달했을 때
            System.out.println("Reached end of file. SSD partially loaded.");
        } catch (IOException e) {
            System.err.println("Error reading NAND file: " + e.getMessage());
        }
    }

    private void initializeNandFile() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(NAND_FILE))) {
            for (int i = 0; i < SSD_SIZE; i++) {
                dos.writeInt(0);
            }
        } catch (IOException e) {
            System.err.println("Error initializing NAND file: " + e.getMessage());
        }
    }
}