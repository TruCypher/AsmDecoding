package src.main.VMTranslator;

import java.util.HashMap;
import java.util.Map;

public class CommandType {

    private Map<String,String> CType = new HashMap<String,String>();

    public CommandType() {
        //arithmetics
        this.CType.put("add","arithmetic");
        this.CType.put("sub","arithmetic");
        this.CType.put("neg","arithmetic");
        this.CType.put("eq","arithmetic");
        this.CType.put("gt","arithmetic");
        this.CType.put("lt","arithmetic");
        this.CType.put("and","arithmetic");
        this.CType.put("or","arithmetic");
        this.CType.put("not","arithmetic");

        //memory-access
        this.CType.put("push","push");
        this.CType.put("pop","pop");

    }

    public String getCType(String key) {
        return this.CType.get(key);
    }
}
