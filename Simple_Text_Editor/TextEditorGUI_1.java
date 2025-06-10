import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class TextEditorGUI extends JFrame implements ActionListener {

    List<String> lines = new ArrayList<>();
    //Components
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu fileMenu,editMenu;
    JMenuItem openItem, saveItem, exitItem;
    JMenuItem appendLineItem,replaceLineItem,deleteLineItem,searchItem,replaceWordItem;

    public TextEditorGUI(){
        setTitle("Sparsh NotePad !");

        //Text Area
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        //Menu Bar
        menuBar = new JMenuBar();

        //Creating Menu in Menu bar
        //Adding the Menu of  File.
        fileMenu = new JMenu("File");

        openItem = new JMenuItem("open");
        openItem.addActionListener(this);

        saveItem = new JMenuItem("save");
        saveItem.addActionListener(this);

        exitItem = new JMenuItem("exit");
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        //adding the Menu 'fileMenu' to the menubar
        menuBar.add(fileMenu);

        //Adding the menu of editMenu
        editMenu = new JMenu("Edit");

        appendLineItem = new JMenuItem("append line");
        appendLineItem.addActionListener(this);

        replaceLineItem = new JMenuItem("replace line");
        replaceLineItem.addActionListener(this);

        deleteLineItem = new JMenuItem("delete line");
        deleteLineItem.addActionListener(this);

        searchItem = new JMenuItem("search");
        searchItem.addActionListener(this);

        replaceWordItem = new JMenuItem("replace word");
        replaceWordItem.addActionListener(this);

        editMenu.add(appendLineItem);
        editMenu.add(replaceLineItem);
        editMenu.add(deleteLineItem);
        editMenu.add(searchItem);
        editMenu.add(replaceWordItem);

        //adding the Menu 'editMenu' to the menubar
        menuBar.add(editMenu);

        //Setting the MenuBar
        setJMenuBar(menuBar);

        //Window setup
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Add action event listner(open, save, Exit)
    public void actionPerformed(ActionEvent e){
        //1. Action Listner for opening or loading the file.
        if(e.getSource() == openItem){ // for the loading of the file
            JFileChooser fileChooser = new JFileChooser();  //This allows the user to select a file from their file system using a graphical interface.
            int result = fileChooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){ //JFileChooser.APPROVE_OPTION means the user selected a file and clicked "Open".
                File file = fileChooser.getSelectedFile();//  Gets the File object that the user selected.
               try{
                   lines = FileManager.readFile(file.getAbsolutePath());

                   //adding it to the JTextArea
                   textArea.setText(String.join("\n",lines));
                   JOptionPane.showMessageDialog(this,"The file loaded successfully!");

               }catch(IOException ex){
                    JOptionPane.showMessageDialog(this, "Error opening file.");
                }
            }
        }

        //2. Action Listner for saving the notes
        if(e.getSource() == saveItem){// to save the file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                try{
                    lines = Arrays.asList(textArea.getText().split("\n"));
                    FileManager.writeFile(file.getAbsolutePath(),lines);
                    JOptionPane.showMessageDialog(this,"File saved successfully");
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(this, "Error saving file ");
                }
            }
        }

        //3. Action Listener for the saving the appended text
        if(e.getSource() == appendLineItem){
            String newLine = JOptionPane.showInputDialog(this, "Enter you Line");
            if(newLine != null && !newLine.isEmpty()){
                //update the list in the backend
                Editor.appendLine(lines,newLine);

                //updating the JTextArea now
                textArea.setText(String.join("\n",lines));
            }
        }

        //4. Action listener for replacing the line in the text file
        if(e.getSource() == replaceLineItem){
            String lineNumber = JOptionPane.showInputDialog(this,"Enter the line number you want to replace : -");
            if(lineNumber != null && !lineNumber.isEmpty()) {
                try {
                    int lineNum = Integer.parseInt(lineNumber);

                    //check if the number is valid
                    if(lineNum < 1 || lineNum > lines.size()){
                        JOptionPane.showMessageDialog(this,"Invalid Line Number.");
                        return;
                    }

                    //Asking for the new content
                    String newContent = JOptionPane.showInputDialog(this,"Enter the new Content.");
                    if(newContent == null) return;

                    //replacing the content
                    Editor.replaceLine(lines,newContent,lineNum);

                    //updating the JtextArea
                    textArea.setText(String.join("\n",lines));

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                }
            }
        }

        //5. Action Listener after deleting the line
        if(e.getSource() == deleteLineItem){
            String lineNumber = JOptionPane.showInputDialog(this,"Enter the line number you want to delete :");

            if(lineNumber != null && !lineNumber.isEmpty()){
                try{
                    int lineNum = Integer.parseInt(lineNumber);

                    //check if the number is correct
                    if(lineNum < 1 || lineNum > lines.size()){
                        JOptionPane.showMessageDialog(this,"Enter Line Number is not valid.");
                        return;
                    }

                    Editor.deleteLine(lines, lineNum);

                    //updating the JtextArea
                    textArea.setText(String.join("\n",lines));

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                }
            }
        }

        //6. Action listener for searching the item
        if(e.getSource() == searchItem){
            String word = JOptionPane.showInputDialog(this, "Enter the word you want to search:-");
            if(word != null && !word.isEmpty()){

                int lineIndex =  Editor.searchWord(lines, word);

                if(lineIndex == -1){
                    JOptionPane.showMessageDialog(this,"'"+word+"' not found");
                }else{
                    //Calculating selection indices to highlight in JTextArea
                    int startIndex = 0;
                    for(int i = 0 ; i < lineIndex ; i++) {
                        startIndex += lines.get(i).length() + 1; //adding  1 for the newLine
                    }

                    int wordPosition = lines.get(lineIndex).indexOf(word);
                    int selectStart = startIndex + wordPosition;
                    int selectEnd = selectStart + word.length();

                    //Select found text
                    textArea.requestFocus(); //It brings the cursor (caret) to the text area.
                    textArea.select(selectStart, selectEnd); //This highlights (selects) the portion of the text starting from index up to index + searchTerm.length().
                    textArea.setCaretPosition(selectEnd); //This moves the cursor (caret) just after the selected word.
                }
            }
        }

        //7.Action listener for replacing the word Item
        if(e.getSource() == replaceWordItem){
            String word = JOptionPane.showInputDialog(this,"Enter the word you want to replace:-");
            if(word == null || word.isEmpty()) return ;
            String replace = JOptionPane.showInputDialog(this,"Enter the word you want to replace with :- ");
            if(replace == null ) return ;

            String text = textArea.getText();
            if(!text.contains(word)){
                JOptionPane.showMessageDialog(this, "'" + word + "' not found in the text.");
                return;
            }

            lines = new ArrayList<>(Arrays.asList(textArea.getText().split("\n")));

            // Use your existing Editor logic to replace words in lines
            Editor.replaceWord(lines, word, replace);

            // Update textArea with updated lines
            textArea.setText(String.join("\n", lines));

            JOptionPane.showMessageDialog(this, "All occurrences of '" + word + "' replaced with '" + replace + "'.");
        }

        //8. Action listener for exiting the notepad
        if(e.getSource() == exitItem){
            dispose(); // close the window
        }
    }

    public static void main(String[] args){
        new TextEditorGUI();
    }

}
