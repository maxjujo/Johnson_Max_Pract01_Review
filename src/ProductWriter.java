import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {




    public static void main(String[] args)
    {
        String ID ="";
        String Name = "";
        String Desc= "";
        double Cost = 0;
        String proRec = "";
        boolean done = false;

        Scanner in = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductData.txt");
        ArrayList<String> recs = new ArrayList<>();

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID");
            Name = SafeInput.getNonZeroLenString(in, "Enter the name");
            Desc = SafeInput.getNonZeroLenString(in, "Enter the description");
            Cost = SafeInput.getRangedInt(in, "Enter the cost", 0, 9999);

            proRec = ID + ", "+ Name +", " +Desc +", "+Cost+", ";

            done = SafeInput.getYNConfirm(in,"Are you done?");
            recs.add(proRec);

        }while(!done);


        System.out.println(recs);


        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }





    }
}

