package Logging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingThoseActions {

    private final ArrayList<String> lines = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new LoggingThoseActions().run();
    }

    private void run() {
        while (true) {
            showMenu();
        }
    }

    private void showMenu() {
        System.out.println("1. Add line");
        System.out.println("2. View lines");
        System.out.println("3. Delete line");
        System.out.println("4. Exit");
        System.out.println("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();  //scannerBugWorkaround

        switch (choice) {
            case 1 -> addLine();
            case 2 -> viewLines();
            case 3 -> deleteLine();
            case 4 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }

    private void addLine() {
        System.out.println("Enter a new line: ");
        String newLine = sc.nextLine();

        // Get the current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timestamp = currentTime.format(formatter);

        String logEntry = timestamp + ": Adding line: \"" + newLine + "\"";
        lines.add(logEntry);

        logAction(logEntry);
    }

    private void viewLines() {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private void deleteLine() {
        System.out.println("Enter the index of the line to delete: ");
        int indexToDelete = sc.nextInt();
        sc.nextLine();  //SBW

        if (indexToDelete >= 0 && indexToDelete < lines.size()) {
            String deletedLine = lines.remove(indexToDelete);
            String logEntry = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                    + ": Deleting line #" + (indexToDelete + 1) + ": \"" + deletedLine + "\"";
            logAction(logEntry);
        } else {
            System.out.println("Invalid index");
        }
    }

    private void logAction(String logEntry) {
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(("Logging//Logs.txt"), true));  // Append to the file
            ps.println(logEntry);
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
