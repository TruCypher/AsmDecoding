package src.main.HackDecoder;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private File asm;
    private Scanner src;
    private String curr;
    private BinaryCode bc = new BinaryCode();

    private String commandType;
    private String _symbol;
    private String _dest;
    private String _comp;
    private String _jump;

    public void reset() {
        this._symbol = null;
        this._comp = null;
        this._dest = null;
        this._jump = null;
    }

    public Parser(String file) {
        try {
            this.asm = new File(file);
            this.src = new Scanner(this.asm);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean hasMoreCommands() {

        if (this.src.hasNextLine()) {
            return true;
        }
        return false;
    }

    public void advance() {
        while (this.hasMoreCommands()) {
            this.curr = this.src.nextLine();
            if (!curr.startsWith("//") && curr.length() > 0) {
                curr = curr.split("//")[0].trim();
                this.commandType();
            }
        }
    }

    public void commandType() {
        reset();
        if (this.curr.startsWith("@")) {
            this.commandType = "A";
            this.AType();
        } else if (this.curr.contains("=") || this.curr.contains(";")) {
            this.commandType = "C";
            this.CType();
        } else if (this.curr.startsWith("(")) {
            this.commandType = "L";
            this.LType();
        }
    }

    public void AType() {
        try {
            this.curr = curr.substring(1, curr.length());
            this._symbol = decToBin(curr);
            System.out.println(this._symbol);
        } catch (NumberFormatException e) {
            this.curr = curr.substring(1, curr.length());
            System.out.println(this._symbol);
        }
    }

    public void CType() {
        if (this.curr.contains("=")) {
            this._dest = curr.split("=")[0];
            this._comp = curr.split("=")[1];
            System.out.println(bc.CInstruction(this._comp,this._dest,true));
        } else if (this.curr.contains(";")) {
            this._jump = curr.split(";")[1];
            this._comp = curr.split(";")[0];
            System.out.println(bc.CInstruction(this._comp,this._jump));
        }


    }

    public void LType() {
        this._symbol = this.curr.substring(1, curr.indexOf(")"));
    }

    public String decToBin(String dec) throws NumberFormatException {
        return Integer.toBinaryString(0x10000 | Integer.parseInt(dec)).substring(1);
    }

    public static void main(String[] args) {

        Parser p = new Parser("C:/Users/Tru/Desktop/NandtoTetrix/Nand/src/Pong.asm");
        p.advance();
    }
}