package View;
import Controller.Controller;
import Exceptions.PancakeException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allSteps(); }
        catch (PancakeException e) {
            System.out.println(e.toString());
        } //here you must treat the exceptions that can not be solved in the controller
    }
}

