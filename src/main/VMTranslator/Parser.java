package src.main.VMTranslator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private File asm;
    private Scanner src;
    public String curr;

    CommandType CType = new CommandType();

    public Parser(String url) {

        try {
            this.asm = new File(url);
            this.src = new Scanner(this.asm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasMoreCommands() {
        if(this.src.hasNextLine()) {
            return true;
        }
        return false;
    }

    public void advance() {
        if(hasMoreCommands()) {
            this.curr = this.src.nextLine();
        } else {
            this.curr = null;
        }
    }

    public String commandType() {
        String[] splitCommand = this.curr.split(" ");
        if(CType.getCType(splitCommand[0]) == "arithmetic") {
            return "arithmetic";
        } else if(CType.getCType(splitCommand[0]) == "push") {
            return "push";
        } else if(CType.getCType(splitCommand[0]) == "pop") {
            return "pop";
        }

        return null;
    }

    public String arg1 () {
        String[] splitCommand = this.curr.split(" ");
        String type = this.commandType();
        if(type == "arithmetic") {
            return splitCommand[0];
        } else if (type != "arithmetic") {
            return splitCommand[1];
        }

        return null;
    }

    public int arg2 () {
        try {
            String[] splitCommand = this.curr.split(" ");
            String type = this.commandType();
            if(type != "arithmetic") {
                return Integer.parseInt(splitCommand[2]);
            }
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }

        return -1;
    }


}
