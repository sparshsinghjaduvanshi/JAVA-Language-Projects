import java.util.Scanner;
import java.io.File;

class Menu{
    private int a;
    public void menu(Scanner sc){

        System.out.println("What do you wish to do :- ");
        System.out.println("enter 1 for copying into a new file");
        System.out.println("enter 2 for copying into already existed file.");
        a = sc.nextInt();
        sc.nextLine();
        check_value(a,sc);
    }

    public void check_value(int a,Scanner sc){
        if(a == 1 || a == 2){
            filesValidation(sc);
        } else{
            System.out.println("undefined request please try again.");
            menu(sc);
        }
    }

    public void filesValidation(Scanner sc){

        System.out.println("Enter the source File name :- ");
        String source = sc.nextLine();

        if(File_Validation.fileValidation(source)){
            action_determiner(source,sc);
        }else{
            filesValidation(sc);
        }
    }

    public void action_determiner(String source,Scanner sc){
        File_Creating_Writing fcw = new File_Creating_Writing(source);

        if(a == 1){
            fcw.creatingNewFile(sc);
        }else if(a == 2){
            while(true){
                System.out.println("Enter the Destination file name : ");
                fcw.destination = sc.nextLine();
                if(File_Validation.fileValidation(fcw.destination)){
                    fcw.writeFile(sc);
                    break;
                }
            }
        }
    }
}