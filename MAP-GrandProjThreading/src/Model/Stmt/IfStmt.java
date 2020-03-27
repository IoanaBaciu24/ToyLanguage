package Model.Stmt;

import ExceptionThing.SomeException;
import Model.*;
import Model.Containers.HeapInter;
import Model.Containers.MapInter;
import Model.Containers.StackInter;
import Model.Exp.Expr;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.value;

public class IfStmt implements IStmt {

    Expr exp;
    IStmt thenS;
    IStmt elseS;

    //vezi aci cum sa il faci sa mearga
    public IfStmt(Expr e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}
    public ProgState execute(ProgState state) throws SomeException
    {
        MapInter<String, value> tbl = state.getSymTable();
        StackInter<IStmt> stk = state.getExeStack();
        HeapInter heap = state.getHeap();
        value v;
        v = exp.eval(tbl, heap);
        if(v.getType().equals(new BoolType()))
        {
                BoolValue val = (BoolValue)v;
                if(val.getVal() == true)
                {
                    stk.push(thenS);
                }
                else stk.push(elseS);
        }
        else throw new SomeException("give me the bool expression");

        return null;
    }
    public  String toString(){ return "IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+")";}

    @Override
    public IStmt deepcopy() {
        IfStmt evl = new IfStmt(exp.deepcopy(), thenS.deepcopy(), elseS.deepcopy());
        return evl;
    }
}
