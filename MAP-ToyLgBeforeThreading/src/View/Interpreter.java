package View;
import Container.*;
import Controller.Controller;
import Exceptions.PancakeException;
import Module.Statement.*;
import Module.*;
import Module.Type.*;
import Module.Value.*;
import Repo.*;
import Module.Expression.*;
import Module.Statement.IStmt;

class Interpreter {
    public static void main(String[] args) {
        IStmt ex1 = new CompStmt(new VarDeclarationStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex1);
        Repository repo1 = new CompilerRepo(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclarationStmt("a", new IntType()),
                new CompStmt(new VarDeclarationStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new
                                ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex2);
        Repository repo2 = new CompilerRepo(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);
        IStmt ex3 = new CompStmt(new VarDeclarationStmt("a", new BoolType()),
                new CompStmt(new VarDeclarationStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        IStmt ex4 = new CompStmt(
                new VarDeclarationStmt("varf", new StringType()),
                new CompStmt(
                        new AssignStmt("varf", new ValueExp(new StringValue("test.txt"))),
                        new CompStmt(new OpenStmt(new VarExp("varf")),
                                new CompStmt(new VarDeclarationStmt("varc", new IntType()),
                                        new CompStmt(new ReadStmt("varc", new VarExp("varf")),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadStmt( "varc", new VarExp("varf")),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new CloseStmt(new VarExp("varf"))))))))));
        PrgState prg3 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex3);
        Repository repo3 = new CompilerRepo(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);
        PrgState prg4 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex4);
        Repository repo4 = new CompilerRepo(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);
        IStmt ex5= new CompStmt(
                new VarDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclarationStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))
                                )
                        )
                )
        );
        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex5);
        Repository repo5 = new CompilerRepo(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);
        IStmt ex6 = new CompStmt(
                new VarDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(
                                new VarDeclarationStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(
                                        new NewStmt("a", new VarExp("v")),
                                        new CompStmt(
                                                new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                new PrintStmt(new HeapReadingExp(new VarExp("a"))))
                                                )
                                        )
                                )
                );
        PrgState prg6 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex6);
        Repository repo6 = new CompilerRepo(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(
                new VarDeclarationStmt("v",  new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(new RelExp(5, new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v"))
                        )
                )

        );
        PrgState prg7 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex7);
        Repository repo7 = new CompilerRepo(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 = new CompStmt(
                new VarDeclarationStmt("v",  new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(new ArithExp(1, new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v"))
                        )
                )

        );
        PrgState prg8 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex8);
        Repository repo8 = new CompilerRepo(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        IStmt ex9 = new CompStmt(
                new VarDeclarationStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new NewStmt("v", new ValueExp(new IntValue(20))),
                        new NewStmt("v", new ValueExp(new IntValue(30)))
                )
        );
        PrgState prg9 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<String>(), ex9);
        Repository repo9 = new CompilerRepo(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.show();
    }
}
