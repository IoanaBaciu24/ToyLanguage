package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Containers.QueueInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.type;
import Model.Value.value;

public class PrintStmt implements IStmt {

    Expr exp;
    public PrintStmt(Expr e) {exp = e;}
    public ProgState execute(ProgState state) throws SomeException {
        //Stack<IStmt> stk = state.getExeStack();
        QueueInter<value> qu= state.getOut();
        MapInter<String,value> t = state.getSymTable();
        HeapInter heap = state.getHeap();
        qu.add(exp.eval(t, heap));
        return null;
    }
    public String toString(){ return "print " + exp.toString();}

    @Override
    public IStmt deepcopy() {
        PrintStmt evl = new PrintStmt(exp.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        exp.typecheck(typeEnv);
        return typeEnv;

    }
}
