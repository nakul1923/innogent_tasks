package service;

import java.io.*;
import java.util.*;

public class FileService {

    public static <T> void writeToFile(String fileName, List<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static <T> List<T> readFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("⚠️ File not found: " + fileName + " — created empty list.");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error reading file: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

