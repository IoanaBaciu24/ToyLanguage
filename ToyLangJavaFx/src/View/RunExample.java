package View;

import Service.Service;
import ExceptionThing.SomeException;

import java.io.IOException;

public class RunExample extends Command{

    private Service ctr;
    public RunExample(String key, String desc, Service ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allSteps(); }
        catch (SomeException e) { System.out.println(e);}
        catch (IOException e){System.out.println(e);}//here you must treat the exceptions that can not be solved in the controller
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
