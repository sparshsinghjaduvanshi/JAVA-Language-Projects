import java.io.IOException;
import java.util.*;

public class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        System.out.println("Enter the file name to open : ");
        String fileName = sc.nextLine();

        try{
            lines = FileManager.readFile(fileName);
            System.out.println("File loaded successfully");
        }catch(IOException e){
            System.out.println("File not found : Creating new File.");
            e.printStackTrace();
        }

        boolean value = true;
        while(value){
            System.out.println("\n.............Menu..............");
            System.out.println("1. Display File");
            System.out.println("2. Append Line");
            System.out.println("3. Replace Line");
            System.out.println("4. Delete Line");
            System.out.println("5. Search Word");
            System.out.println("6. Replace Word");
            System.out.println("7. Save");
            System.out.println("8. Exit");

            System.out.println("Choose one of the above options:-");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1 -> Editor.display(lines);
                case 2 -> {
                    System.out.println("Enter the line to append :-");
                    Editor.appendLine(lines, sc.nextLine());
                }
                case 3 -> {
                    System.out.println("Enter the Line number to Replace:-");
                    int lineNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the new Content :- ");
                    Editor.replaceLine(lines, sc.nextLine(), lineNumber);
                }
                case 4 -> {
                    System.out.println("Enter the line number to delete :-");
                    Editor.deleteLine(lines, sc.nextInt());
                }
                case 5 -> {
                    System.out.println("Enter the word you want to search:-");
                    int index = Editor.searchWord(lines, sc.nextLine());
                    if(index == -1){
                        System.out.println("Word now found......");
                    }else{
                        System.out.println("Word is present in the text file.");
                    }
                }
                case 6 -> {
                    System.out.print("Word to replace: ");
                    String target = sc.nextLine();
                    System.out.print("Replace with: ");
                    String replacement = sc.nextLine();
                    Editor.replaceWord(lines, target, replacement);
                }
                case 7 ->{
                    try{
                        FileManager.writeFile(fileName, lines);
                        System.out.println("................File saved successfully............");
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                case 8 -> {
                    value = false;
                    System.out.println(".........EXITING............");
                }

                default -> System.out.println("....INVALID INPUT ...........");
            }
        }
    }
}
