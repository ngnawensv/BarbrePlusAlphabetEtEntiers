package barbreplusavecframe;




public class TestCharacter implements Executable<Character>, java.io.Serializable {

    @Override
    public boolean execute(Character arg1, Character arg2) {
        int int1 = (int) arg1;
        int int2 = (int) arg2;
        return (int1 < int2);
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }
}