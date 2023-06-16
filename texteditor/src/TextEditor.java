import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // declare properties of TextEditor
    JFrame frame;

    // create menu bar
    JMenuBar menuBar;

    // create text area
    JTextArea textArea;

    // intialize menus in menu bar
    JMenu file, edit;

    // file menu items
    JMenuItem newFile, openFile, saveFile;

    // edit menu item
    JMenuItem cut, copy, paste, selectAll, close;

    TextEditor() {
        // intialize frame
        frame = new JFrame();

        // initialize menubar
        menuBar = new JMenuBar();

        // initialize text area
        textArea = new JTextArea();

        // Initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize file menu item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("save File");

        // add action Listioner to newFile
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // add menu item to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initialize edit menu itmes
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // add actionListener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);


        // set Menubar in frame
        frame.setJMenuBar(menuBar);

        // create content plane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to pannel
        panel.add(textArea, BorderLayout.CENTER);
        // create scroll now
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scoll pane to pannel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        // set Dimension of frame
        frame.setBounds(100, 100, 400, 400);
        frame.setTitle("Text Editor (by Ankush Shekhawat)");
        frame.setVisible(true);
        frame.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            // performd cut operation
            textArea.cut();
        }

        if (actionEvent.getSource() == copy) {
            // perform copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            // perform paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // perform close editor operation
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // getting selected file
                File file = fileChooser.getSelectedFile();
                // get path of selected file
                String filePath = file.getPath();
                try {
                    // intialize file reader
                    FileReader fileReader = new FileReader(filePath);

                    // intialize buffer reader
                    BufferedReader bufferReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // Read content line by line
                    while ((intermediate = bufferReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    // set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile)
        {
                // initalize file picker
                JFileChooser fileChooser = new JFileChooser("C:");
                // get chooser option from file chooser
                int chooseOption = fileChooser.showSaveDialog(null);
                // check if we cicked save button
                if(chooseOption ==  JFileChooser.APPROVE_OPTION )
                {
                    // createa new file choose dir path and save file
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                        FileWriter fileWriter = new FileWriter(file);
                        // intialize buffered writer
                        BufferedWriter bufferWriter =  new BufferedWriter(fileWriter);
                        // write content of text area to file
                        textArea.write(bufferWriter);
                        bufferWriter.close();
                    }
                    catch(IOException ioException){
                        ioException.printStackTrace();
                    }
                }
        }

        if(actionEvent.getSource() == newFile)
        {
           TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {

       TextEditor textEditor = new TextEditor();

    }

}



