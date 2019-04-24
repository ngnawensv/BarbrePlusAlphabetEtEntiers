package barbreplusavecframe;




public class TestInt implements Executable<Integer>, java.io.Serializable {

    @Override
    public boolean execute(Integer arg1, Integer arg2) {
        return (arg1 < arg2);
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }
}