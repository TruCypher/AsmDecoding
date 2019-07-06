package src.main.VMTranslator;

import com.sun.org.apache.bcel.internal.classfile.Code;

public class Main {

    public static void main(String[] args) {

        Parser a = new Parser("C:/Users/Tru/Desktop/NandtoTetrix/Nand/src/StackTest.vm");
        CodeWriter cw = new CodeWriter();
        while(a.hasMoreCommands()) {
            a.advance();
            if (!a.curr.startsWith("//") && a.curr.length() > 0) {
                a.curr = a.curr.split("//")[0].trim();
                String type = a.commandType();
                if(type.equals("arithmetic")) {
                    cw.writeArithmetic(a.arg1());
                } else if (type.equals("pop") || type.equals("push")) {
                    cw.writePushPop(type,a.arg1(),a.arg2());
                }
            }
        }

    }
}
