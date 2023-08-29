import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;
public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");
        String ID = "";
        String productName = "";
        String productDesc = "";
        double cost = 0;
        boolean done = false;
        String productRec = "";

        do {
            ID = SafeInput.getNonZeroLenString(in,"Enter the ID [000000 - 999999]");
            productName = SafeInput.getNonZeroLenString(in,"Enter product name");
            productDesc = SafeInput.getNonZeroLenString(in,"Enter product description");
            cost = SafeInput.getDouble(in,"Enter product cost");
            productRec = ID + ", " + productName + ", " + productDesc + ", " + cost;
            products.add(productRec);
            done = SafeInput.getYNConfirm(in, "Are you finished?");
        } while (!done);

        for(String i: products)
            System.out.println(i);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : products)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}