import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;
public class ProductReader
{
    private static ArrayList<String> lines = new ArrayList<>();
    public static void main(String[] args)
    {
        JFileChooser fileChooser = new JFileChooser();
        File chosenFile;
        String readLine = "";
        String columnTitle1 = "ID#";
        String columnTitle2 = "ProductName";
        String columnTitle3 = "Description";
        String columnTitle4 = "Cost";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            fileChooser.setCurrentDirectory(workingDirectory);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                chosenFile = fileChooser.getSelectedFile();
                Path file = chosenFile.toPath();
                InputStream stream = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(stream));
                System.out.println(String.format("%s     %s %s %s",columnTitle1, columnTitle2, columnTitle3, columnTitle4));
                for(int i = 0; i < 40; i++ )
                {
                    System.out.print("=");
                }
                while(fileReader.ready())
                {
                    readLine = fileReader.readLine();
                    System.out.printf("\n%s", readLine);
                }
                fileReader.close();
                System.out.println("\n\nData file has been opened and read!");
            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}