package src.main.VMTranslator;

public class CodeWriter {
    private int flag = 0;


    public CodeWriter() {

    }

    public void writeArithmetic(String command) {
        String trans = "";
        if (command.equals("add")) {
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "M=D+M\n";
            trans += "@SP\n";
            trans += "M=M+1\n";
        } else if (command.equals("sub")) {
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "M=M-D\n";
            trans += "@SP\n";
            trans += "M=M+1\n";
        } else if (command.equals("neg")) {
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=-M\n";
        } else if (command.equals("not")) {
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=!M\n";
        } else if (command.equals("or")) {
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=D|M\n";
        } else if (command.equals("and")) {
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=D&M\n";
        } else if (command.equals("eq")) {
            String flag = String.valueOf(this.flag);
            this.flag++;
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "D=M-D\n";
            trans += "M=-1\n";
            trans += "@eqTrue" + flag + "\n";
            trans += "D;JEQ\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=0\n";
            trans += "(eqTrue" + flag + ")\n";
        } else if (command.equals("gt") ) {
            String flag = String.valueOf(this.flag);
            this.flag++;
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "D=M-D\n";
            trans += "M=-1\n";
            trans += "@gtTrue" + flag + "\n";
            trans += "D;JGT\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=0\n";
            trans += "(gtTrue" + flag + ")\n";
        } else if (command.equals("lt")) {
            String flag = String.valueOf(this.flag);
            this.flag++;
            trans += "@SP\n";
            trans += "AM=M-1\n";
            trans += "D=M\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "D=M-D\n";
            trans += "M=-1\n";
            trans += "@ltTrue";
            trans += "D;JLT\n";
            trans += "@SP\n";
            trans += "A=M-1\n";
            trans += "M=0\n";
            trans += "(ltTrue" + flag + ")\n";
        }

        System.out.println(trans);
    }

    public void writePushPop(String command, String segment, int index) {
        String trans = "";
        if(command.equals("push")) {
            trans += "// push " + segment + index + "\n";
            if(segment.equals("constant")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if (segment.equals("static")) {
                trans += "@"  + index + "\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if(segment.equals("this")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@THIS\n";
                trans += "A=M+D\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if(segment.equals("that")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@THAT\n";
                trans += "A=M+D\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if(segment.equals("argument")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@ARG\n";
                trans += "A=M+D\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if (segment.equals("local")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@LCL\n";
                trans += "A=M+D\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if (segment.equals("temp")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@5\n";
                trans += "A=A+D\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            } else if(segment.equals("pointer")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@3\n";
                trans += "A=A+D\n";
                trans += "D=M\n";
                trans += "@SP\n";
                trans += "A=M\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "M=M+1\n";
            }
        }
        else if(command.equals("pop")) {
            trans += "// pop " + segment + index + "\n";
            if(segment.equals("constant")) {
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@" + index + "\n";
                trans += "M=D\n";
            } else if(segment.equals("this")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@THIS\n";
                trans += "D=M+D\n";
                trans += "@R13\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@R13\n";
                trans += "A=M\n";
                trans += "M=D\n";
            } else if(segment.equals("that")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@THAT\n";
                trans += "D=M+D\n";
                trans += "@R13\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@R13\n";
                trans += "A=M\n";
                trans += "M=D\n";
            } else if(segment.equals("argument")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@ARG\n";
                trans += "D=M+D\n";
                trans += "@R13\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@R13\n";
                trans += "A=M\n";
                trans += "M=D\n";
            } else if (segment.equals("local")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@LCL\n";
                trans += "D=M+D\n";
                trans += "@R13\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@R13\n";
                trans += "A=M\n";
                trans += "M=D\n";
            } else if (segment.equals("temp")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@5\n";
                trans += "D=A+D\n";
                trans += "@R13\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@R13\n";
                trans += "A=M\n";
                trans += "M=D\n";
            } else if(segment.equals("pointer")) {
                trans += "@" + index + "\n";
                trans += "D=A\n";
                trans += "@3\n";
                trans += "D=A+D\n";
                trans += "@R13\n";
                trans += "M=D\n";
                trans += "@SP\n";
                trans += "AM=M-1\n";
                trans += "D=M\n";
                trans += "@R13\n";
                trans += "A=M\n";
                trans += "M=D\n";
            }
        }

        System.out.println(trans);
    }
}
