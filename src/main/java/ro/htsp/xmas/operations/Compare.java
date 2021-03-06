package ro.htsp.xmas.operations;

import ro.htsp.xmas.machine.CPU;
import ro.htsp.xmas.machine.RAM;

public class Compare implements Operation {
    public static final int OPCODE = 3;
    public static final Operation INSTANCE = new Compare();

    @Override
    public int getOpCode() {
        return OPCODE;
    }

    @Override
    public void execute(int cm, int b, int c) {
        if ((cm & 24) == 24) {
            compare(cm - 24, b, RAM.read(c));
        } else if ((cm & 16) == 16) {
            compare(cm - 16, RAM.read(b), c);
        } else if ((cm & 8) == 8) {
            compare(cm - 8, RAM.read(c), RAM.read(b));
        } else {
            compare(cm, RAM.read(b), RAM.read(c));
        }
    }

    @Override
    public String decode(Boolean condition, int cm, int oa, int ob) {
        return InstructionDecoder.decodeCompare(condition, cm, oa, ob);
    }

    private void compare(int comparison, int a, int b) {
        switch (comparison) {
            case 0:
                CPU.setConditionFlag(true);
                break;
            case 1:
                CPU.setConditionFlag(false);
                break;
            case 2:
                CPU.setConditionFlag(a == b);
                break;
            case 3:
                CPU.setConditionFlag(a != b);
                break;
            case 4:
                CPU.setConditionFlag(Utils.sixBitSigned(a) < Utils.sixBitSigned(b));
                break;
            case 5:
                CPU.setConditionFlag(Utils.sixBitSigned(a) > Utils.sixBitSigned(b));
                break;
            case 6:
                CPU.setConditionFlag(a < b);
                break;
            case 7:
                CPU.setConditionFlag(a > b);
                break;
        }
    }

}
