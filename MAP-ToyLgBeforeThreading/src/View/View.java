//package View;
//import Container.*;
//import Controller.Controller;
//import Exceptions.PancakeException;
//import Module.Statement.*;
//import Module.*;
//import Module.Type.*;
//import Module.Value.*;
//import Repo.*;
//import Module.Expression.*;
//
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Scanner;
//import java.util.Stack;
//
//public class View {
//    private static Scanner input = new Scanner(System.in);
//
//    public static void main(String args[])
//    {
//        Repository repo = new CompilerRepo();
//        try{
//            // int v; v=2;Print(v)
//            IStmt ex1 = new CompStmt(new VarDeclarationStmt("v", new IntType()),
//                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
//                            new PrintStmt(new VarExp("v"))));
//            //int a;int b; a=2+3*5;b=a+1;Print(b)
//            IStmt ex2 = new CompStmt( new VarDeclarationStmt("a",new IntType()),
//                    new CompStmt(new VarDeclarationStmt("b",new IntType()),
//                            new CompStmt(new AssignStmt("a", new ArithExp(1,new ValueExp(new IntValue(2)),new
//                                    ArithExp(3,new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
//                                    new CompStmt(new AssignStmt("b",new ArithExp(1,new VarExp("a"), new
//                                            ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
//            //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
//            IStmt ex3 = new CompStmt(new VarDeclarationStmt("a",new BoolType()),
//                    new CompStmt(new VarDeclarationStmt("v", new IntType()),
//                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                                    new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
//                                            IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
//                                            VarExp("v"))))));
//            while(true)
//            {
//                int age = Integer.parseInt(input.nextLine().replaceAll("[\\n\\t ]", ""));
//                PrgState program;
//                switch (age)
//                {
//                    case(1):
//                    {
//                        program = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex1);
//                        repo.add(program);
//                        break;
//                    }
//                    case(2):
//                    {
//                        program = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex2);
//                        repo.add(program);
//                        break;
//                    }
//                    case(3):
//                    {
//                        program = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex3);
//                        repo.add(program);
//                        break;
//                    }
//                }
//
//                Controller ctrl = new Controller(repo);
//                ctrl.allSteps();
//            }
//
//        }
//        catch (PancakeException e) {
//            System.out.println();
//        }
//    }
//}
