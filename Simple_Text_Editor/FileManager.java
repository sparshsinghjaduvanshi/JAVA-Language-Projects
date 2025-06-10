import java.io.*;
import java.util.*;

public class FileManager {
    public static List<String> readFile(String fileName) throws IOException{
        List<String> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;
        while((line = reader.readLine()) != null){
            lines.add(line);
        }

        reader.close();
        return lines;
    }

    public static void writeFile(String fileName, List<String> lines) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (String line : lines){
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
