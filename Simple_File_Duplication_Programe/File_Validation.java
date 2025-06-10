import java.io.File;
import java.io.FileNotFoundException;

public class File_Validation {

    static boolean fileValidation(String fname){
            File file = new File(fname);

            if(file.exists()){
                return true;
            } else
                System.out.println("File name does not exists..........");
                return false;
    }
}
