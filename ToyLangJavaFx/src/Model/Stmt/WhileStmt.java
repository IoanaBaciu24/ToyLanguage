package Model.Stmt;

import ExceptionThing.SomeException;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Containers.StackInter;
import Model.Exp.Expr;
import Model.ProgState;
import Model.Type.BoolType;
import Model.Type.type;
import Model.Value.BoolValue;
import Model.Value.value;

import java.util.Objects;

public class WhileStmt implements IStmt {

    Expr expr;
    IStmt statement;

    public WhileStmt(Expr e, IStmt s)
    {
        expr = e;
        statement = s;
    }

    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl= state.getSymTable();
        HeapInter heap= state.getHeap();
        StackInter<IStmt> stack = state.getExeStack();
        value val = expr.eval(tbl,heap);
        if(val.getType().equals(new BoolType()))
        {
            boolean evaluated = ((BoolValue)val).getVal();
            if(Objects.equals(evaluated, true))
            {
                stack.push(this);
                statement.execute(state);
            }
        }
        else throw new SomeException("the expression must be boolean to fit the while statement");
        return null;
    }
    public String toString()
    {
        return "while (" + expr.toString() + ") execute {" + statement.toString() + "}";
    }
    public IStmt deepcopy()
    {
        WhileStmt evl = new WhileStmt(expr.deepcopy(), statement.deepcopy());
        return evl;
    }

    @Override
    public MapInter<String, type> typecheck(MapInter<String, type> typeEnv) throws SomeException {
        type typexp=expr.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typecheck(typeEnv.clone());

           // elseS.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new SomeException("The condition of while has not the type bool");
    }
}
