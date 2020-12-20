package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.RAM;

public class Subtract implements Operation {
    public static final int OPCODE = 2;
    public static final Operation INSTANCE = new Subtract();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int rd, int ra, int rb) {
        RAM.write(rd, RAM.read(ra) - RAM.read(rb));
    }
}