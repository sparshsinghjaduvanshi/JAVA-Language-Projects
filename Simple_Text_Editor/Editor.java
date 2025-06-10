import java.util.*;

public class Editor {
    public static void display(List<String> lines){
        for(int i = 0 ; i < lines.size() ; i++){
            System.out.println((i+1)+ "; "+ lines.get(i));
        }
    }

    public static void appendLine(List<String> lines,String newLine){
        lines.add(newLine);
    }

    public static void replaceLine(List<String> lines, String newLine, int lineNumber){
        if(lineNumber > 0 && lineNumber <= lines.size()){
            lines.set(lineNumber-1, newLine);
        }else{
            System.out.println("...............Invalid Line Number..............");
        }
    }

    public static void deleteLine(List<String> lines, int lineNumber){
        if(lineNumber > 0 && lineNumber <= lines.size()){
            lines.remove(lineNumber-1);
        }else{
            System.out.println("........Invalid line number ...........");
        }
    }

    public static int searchWord(List<String> lines, String word){

        for(int i = 0 ; i < lines.size() ; i++){
            if(lines.get(i).contains(word)){
               return i;
            }
        }
       return -1;
    }

    public static void replaceWord(List<String> lines, String target, String replace){

        for(int i = 0 ; i < lines.size() ; i++){
            String rep = lines.get(i).replaceAll(target,replace);
            lines.set(i, rep);
        }
    }
}
