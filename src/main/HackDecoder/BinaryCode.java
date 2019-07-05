package src.main.HackDecoder;

import java.util.HashMap;
import java.util.Map;

public class BinaryCode {

    private Map<String,String> dest = new HashMap<String, String>();
    private Map<String,String> comp = new HashMap<String, String>();
    private Map<String,String> jump = new HashMap<String, String>();

    public BinaryCode() {
        //init dest
        this.dest.put("null","000") ;
        this.dest.put("M","001");
        this.dest.put("D","010");
        this.dest.put("MD","011");
        this.dest.put("A","100");
        this.dest.put("AM","101");
        this.dest.put("AD","110");
        this.dest.put("AMD","11");

        //init comp
        this.comp.put("0","0101010");
        this.comp.put("1","0111111");
        this.comp.put("-1","0111010");
        this.comp.put("D","0001100");
        this.comp.put("A","0110000");
        this.comp.put("M","1110000");
        this.comp.put("!D","0001101");
        this.comp.put("!A","0110001");
        this.comp.put("!M","1110001");
        this.comp.put("-D","0001111");
        this.comp.put("-A","0110011");
        this.comp.put("-M","1110011");
        this.comp.put("D+1","0011111");
        this.comp.put("A+1","0110111");
        this.comp.put("M+1","1110111");
        this.comp.put("D-1","0001110");
        this.comp.put("A-1","0110010");
        this.comp.put("M-1","1110010");
        this.comp.put("D+A","0110010");
        this.comp.put("D+M","10110010");
        this.comp.put("D-A","0010011");
        this.comp.put("D-M","1010011");
        this.comp.put("A-D","0000111");
        this.comp.put("M-D","1000111");
        this.comp.put("D&A","0000000");
        this.comp.put("D&M","1000000");
        this.comp.put("D|A","0010101");
        this.comp.put("D|M","1010101");

        //init jump
        this.jump.put("null","000");
        this.jump.put("JGT","001");
        this.jump.put("JEQ","010");
        this.jump.put("JGE","011");
        this.jump.put("JLT","100");
        this.jump.put("JNE","101");
        this.jump.put("JLE","110");
        this.jump.put("JMP","111");
    }

    public String getDest(String key) {
        return this.dest.get(key);
    }

    public String getComp(String key) {
        return this.comp.get(key);
    }

    public String getJump(String key) {
        return this.jump.get(key);
    }

    public String CInstruction(String comp, String dest, boolean destEmpty) {
        String temp ="111"+this.getComp(comp)+""+this.getDest(dest);
        return bitFormat(temp,temp.length());
    }
    public String CInstruction(String comp, String jump) {
        String temp ="111"+this.getComp(comp)+"000"+this.getJump(jump);
        return bitFormat(temp,temp.length());
    }

    public String bitFormat(String bits, int n) {
        int missingBits = 16-n;
        for(int i=0 ;i<missingBits;i++) {
            bits += "0";
        }
        return bits;
    }
}
