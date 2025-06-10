import java.io.*;
import java.io.IOException;
import java.util.Scanner;


public class Note_Manager {
    static void addNote(Scanner sc){
        System.out.println("Enter your Note :-");
        String note = sc.nextLine();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Notes.txt",true))){
            writer.write(note);
            writer.close();
            System.out.println("..............Note added successfully..............");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void readNotes(){
        File file = new File("Notes.txt");
        if(!file.exists()){
            System.out.println("Notes are not present.");
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader("Notes.txt"))){
            String line;
            int count = 1;

            while((line = reader.readLine())!= null){
                System.out.println(count+". "+ line);
                count++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static void deleteNotes(){
        File file = new File("Notes.txt");

        if(!file.exists()){
            System.out.println("File does not exists@");
            return;
        }

        if(file.delete()) {
            System.out.println("..............All Notes have been deleted.............");
        }else{
            System.out.println("..............Deletion failed...................");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("..................WELCOME! to the Note Manager..............");
        System.out.println("1. Add a Note.");
        System.out.println("2. Read all Notes.");
        System.out.println("3. Delete all Notes.");
        System.out.println("4. Enter your choice");
        choice = sc.nextInt();
        sc.nextLine();
        do{
            switch(choice){
                case 1:
                    addNote(sc);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    deleteNotes();
                    break;
                case 4:
                    System.out.println(".............Exiting Note Manager..........");
                    break;
                default:
                    System.out.println("..............Invalid choice! Try again!...........");
            }
        }while(choice != 4);
    }
}
