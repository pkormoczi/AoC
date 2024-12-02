package dev.ronin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

public class FileFromClassPath {

    private final File file;

    public FileFromClassPath(String fileName) {
        this.file = new File(requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid path!");
        }
        return lines;
    }
}
