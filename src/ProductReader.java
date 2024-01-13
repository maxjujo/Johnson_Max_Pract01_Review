import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                try (InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    System.out.printf("%-10s%-15s%-15s%-8s%n", "ID#", "Name", "Description", "Cost");
                    System.out.println("=====================================");
                    int line = 0;
                    while (reader.ready()) {
                        rec = reader.readLine();
                        line++;
                        System.out.printf("%-60s%n", rec);
                    }
                    reader.close(); // must close the file to seal it and flush buffer
                    System.out.println("\n\nData file read!");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
