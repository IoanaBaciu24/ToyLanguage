package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Containers.StackInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.type;
import Model.Value.value;

public class AssignStmt implements IStmt {

    String id;
    Expr exp;

    public AssignStmt(String i, Expr e)
    {
        id = i;
        exp = e;
    }
    public String toString() {
        return id + "=" + exp.toString();
    }

    public ProgState execute(ProgState state) throws SomeException {
        StackInter<IStmt> stk = state.getExeStack();
        MapInter<String, value> symTbl = state.getSymTable();
        HeapInter heap = state.getHeap();
        value val = exp.eval(symTbl, heap);
        if (symTbl.containsKey(id)) {
            type typId = (symTbl.get(id)).getType();
            if (val.getType().equals(typId))
                symTbl.replace(id, val);
            else
                throw new SomeException("declared type of variable " + id + " and type of the assigned expression do not match");
        } else throw new SomeException("the used variable " + id + " was not declared before");


        return null;
    }

    @Override
    public IStmt deepcopy() {
        AssignStmt evl = new AssignStmt(id,exp.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        type typevar = typeEnv.get(id);
        type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new SomeException("Assignment: right hand side and left hand side have different types ");
    }
}