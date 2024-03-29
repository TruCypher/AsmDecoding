import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String,Integer> symbols = new HashMap<String, Integer>();

    public SymbolTable() {
        this.symbols.put("R0",0);
        this.symbols.put("R1",1);
        this.symbols.put("R2",2);
        this.symbols.put("R3",3);
        this.symbols.put("R4",4);
        this.symbols.put("R5",5);
        this.symbols.put("R6",6);
        this.symbols.put("R7",7);
        this.symbols.put("R8",8);
        this.symbols.put("R9",9);
        this.symbols.put("R10",10);
        this.symbols.put("R11",11);
        this.symbols.put("R12",12);
        this.symbols.put("R13",13);
        this.symbols.put("R14",14);
        this.symbols.put("R15",15);
        this.symbols.put("SCREEN", 16384);
        this.symbols.put("KBD", 24576);
        this.symbols.put("SP", 0);
        this.symbols.put("LCL", 1);
        this.symbols.put("ARG", 2);
        this.symbols.put("THIS", 3);
        this.symbols.put("THAT", 4);
        this.symbols.put("WRITE", 18);
        this.symbols.put("END", 22);
    }

    public void addEntry (String sym, int add) {
        this.symbols.put(sym,add);
    }

    public boolean contains(String sym) {
        if(this.symbols.containsKey(sym)) {
            return true;
        }
        return false;
    }

    public int getAddress(String sym) {
        return this.symbols.get(sym);
    }
}
