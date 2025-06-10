import java.io.*;
import  java.util.Scanner;

public class File_Creating_Writing {
    private String fname,line;
    protected String source,destination;

    public File_Creating_Writing(String file){
        source = file;
    }
    public void creatingNewFile(Scanner sc){

        while(true){
            System.out.println("Enter the new File Name : -");
            fname = sc.nextLine();

            File file = new File(fname);
            if(file.exists()){
                System.out.println("File name already exists choode another name ..............");
            }else{
                try{
                    if(file.createNewFile()){
                        System.out.println("New file created successfully.........");
                        destination = fname;
                        writeFile(sc);
                        break;
                    }else{
                        System.out.println("File creation failed............");
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public void writeFile(Scanner sc){

        try {

                BufferedReader reader = new BufferedReader(new FileReader(source));
                BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
                System.out.println("Copying The File ..............");

                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }

                writer.close();
                reader.close();
                System.out.println("Copying The File Complete....................");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
