package View;

import Service.Service;
import ExceptionThing.SomeException;
import Model.*;
import Model.Containers.MonMap;
import Model.Containers.MonQ;
import Model.Containers.MonStaque;
import Model.Exp.*;
import Model.Stmt.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.value;
import RepoStuff.Repo;

import java.io.IOException;

public class View {

    public static void main(String[]args)
    {
            try {

                IStmt ex1= new CompSrmt(new VarDeclStmt("v",new IntType()),
                        new CompSrmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
                                VarExp("v"))));
                //how is that supposed to work??

                IStmt ex2 = new CompSrmt( new VarDeclStmt("a",new IntType()),
                        new CompSrmt(new VarDeclStmt("b",new IntType()),
                                new CompSrmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                        ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                        new CompSrmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
                                                ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));



                IStmt ex3 = new CompSrmt(new VarDeclStmt("a",new BoolType()),
                        new CompSrmt(new VarDeclStmt("v", new IntType()),
                                new CompSrmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                        new CompSrmt(new IfStmt(new RelExp(">",new VarExp("a"), new VarExp("v")),new AssignStmt("v",new ValueExp(new
                                                IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                                VarExp("v"))))));


                IStmt ex4 = new CompSrmt(
                        new VarDeclStmt("varf", new StringType()),
                        new CompSrmt(
                                new AssignStmt("varf", new ValueExp(new StringValue("leTest.txt"))),
                                new CompSrmt(new OpenRFile(new VarExp("varf")),
                                        new CompSrmt(new VarDeclStmt("varc", new IntType()),
                                                new CompSrmt(new readFile(new VarExp("varf"), "varc"),
                                                        new CompSrmt(new PrintStmt(new VarExp("varc")),
                                                                new CompSrmt(new readFile(new VarExp("varf"), "varc"),
                                                                        new CompSrmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))
                                                                )
                                                )))));


                IStmt ex5= new CompSrmt(
                        new VarDeclStmt("v", new RefType(new IntType())),
                        new CompSrmt(
                                new NewStmt("v", new ValueExp(new IntValue(20))),
                                new CompSrmt(
                                        new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                        new CompSrmt(
                                                new NewStmt("a", new VarExp("v")),
                                                new CompSrmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))
                                        )
                                )
                        )
                );

                IStmt ex6 = new CompSrmt(
                        new VarDeclStmt("v", new RefType(new IntType())),
                        new CompSrmt(
                                new NewStmt("v", new ValueExp(new IntValue(20))),
                                new CompSrmt(
                                        new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompSrmt(
                                                new NewStmt("a", new VarExp("v")),
                                               // new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                new CompSrmt(
                                                //new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))),
                                                        new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                                        new PrintStmt(new ArithExp('+', new HeapReadingExp(new HeapReadingExp(new VarExp("a"))), new ValueExp(new IntValue(5)))
                                                        )
                                        )
                                )
                        )
                ));

                IStmt ex7 = new CompSrmt(
                        new VarDeclStmt("v", new RefType(new IntType())),
                        new CompSrmt(
                                new NewStmt("v", new ValueExp(new IntValue(20))),
                                new CompSrmt(
                                        new PrintStmt(new HeapReadingExp(new VarExp("v"))),
                                        new CompSrmt(
                                                new HWriteStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ArithExp('+',new HeapReadingExp(new VarExp("v")), new ValueExp(new IntValue(5))))
                                        )
                                )
                        )
                );

                IStmt ex8 = new CompSrmt(
                        new VarDeclStmt("v",  new IntType()),
                        new CompSrmt(
                                new AssignStmt("v", new ValueExp(new IntValue(4))),
                                new CompSrmt(
                                        new WhileStmt(new RelExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                                new CompSrmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                        new PrintStmt(new VarExp("v"))
                                )
                                )

                );
                IStmt ex9 = new CompSrmt(
                        new VarDeclStmt("v", new RefType(new IntType())),
                        new CompSrmt(
                                new NewStmt("v", new ValueExp(new IntValue(20))),
                                new CompSrmt(
                                        new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompSrmt(
                                                new NewStmt("a", new VarExp("v")),
                                                new CompSrmt(
                                                        new NewStmt("v", new ValueExp(new IntValue(30))),
                                                        new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))))
                                                )
                                        )
                                )
                        )

                );



                IStmt forFork = new CompSrmt(new HWriteStmt("a", new ValueExp(new IntValue(30))),
                        new CompSrmt( new AssignStmt("v", new ValueExp(new IntValue(32))),
                                new CompSrmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))
                );

                IStmt ex10 = new CompSrmt(
                        new VarDeclStmt("v", new IntType()), new CompSrmt(
                        new VarDeclStmt("a", new RefType(new IntType())), new CompSrmt(
                        new AssignStmt("v", new ValueExp(new IntValue(10))), new CompSrmt(
                        new NewStmt("a", new ValueExp(new IntValue(22))), new CompSrmt(
                        new ForkStmt(forFork), new CompSrmt(
                        new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));


                IStmt ex11 = new CompSrmt(
                        new VarDeclStmt("v", new IntType()), new CompSrmt(
                        new VarDeclStmt("a", new RefType(new IntType())), new CompSrmt(
                        new AssignStmt("v", new ValueExp(new IntValue(10))), new CompSrmt(
                        new PrintStmt(new VarExp("v")), new CompSrmt(
                        new ForkStmt(new CompSrmt(new NewStmt("a", new ValueExp(new IntValue(22))), new PrintStmt(new HeapReadingExp(new VarExp("a"))))), new ForkStmt(new CompSrmt(new AssignStmt("v", new ValueExp(new IntValue(20))), new PrintStmt(new VarExp("v"))))
                )
                )
                )
                )
                );


                IStmt ex12 = new CompSrmt(new VarDeclStmt("v",new IntType()),new CompSrmt(new VarDeclStmt("a",new RefType(new IntType())),
                        new CompSrmt(new AssignStmt("v",new ValueExp(new IntValue(10))),new CompSrmt(new NewStmt("a",new ValueExp(new IntValue(22))),
                                new CompSrmt(new ForkStmt(new CompSrmt(new HWriteStmt("a",new ValueExp(new IntValue(30))),
                                        new CompSrmt(new AssignStmt("v",new ValueExp(new IntValue(32))), new CompSrmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadingExp(new VarExp("a"))))))),
                                        new CompSrmt(new PrintStmt(new ValueExp(new StringValue("I love FORKS :D"))),
                                                new CompSrmt(new ForkStmt(new CompSrmt(new HWriteStmt("a",new ValueExp(new IntValue(30))),
                                                        new CompSrmt(new AssignStmt("v",new ValueExp(new IntValue(32))), new CompSrmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadingExp(new VarExp("a"))))))),
                                                        new CompSrmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadingExp(new VarExp("a")))))))))));



                Repo rep1 = new Repo("log1.in");
                //ProgState prg = new ProgState(new Stack<IStmt>(), new HashMap<String, value>(), new LinkedList<>(), ex3);
                ProgState prg = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex1);
                rep1.add(prg);
                Service ctrl1 = new Service(rep1);
                Repo rep2 = new Repo("log2.in");
                //ProgState prg = new ProgState(new Stack<IStmt>(), new HashMap<String, value>(), new LinkedList<>(), ex3);
                ProgState prg2 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex2);
                rep2.add(prg2);
                Service ctrl2 = new Service(rep2);

                Repo rep3 = new Repo("log3.in");
                //ProgState prg = new ProgState(new Stack<IStmt>(), new HashMap<String, value>(), new LinkedList<>(), ex3);
                ProgState prg3 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex3);
                rep3.add(prg3);
                Service ctrl3 = new Service(rep3);

                Repo rep4 = new Repo("log4.in");
                ProgState prg4 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex4);
                rep4.add(prg4);
                Service ctrl4 = new Service(rep4);

                Repo rep5 = new Repo("log5.in");
                ProgState prg5 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex5);
                rep5.add(prg5);
                Service ctrl5 = new Service(rep5);

                Repo rep6 = new Repo("log6.in");
                ProgState prg6 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex6);
                rep6.add(prg6);
                Service ctrl6 = new Service(rep6);
                Repo rep7 = new Repo("log7.in");
                ProgState prg7 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex7);
                rep7.add(prg7);
                Service ctrl7 = new Service(rep7);

                Repo rep8 = new Repo("log8.in");
                ProgState prg8 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex8);
                rep8.add(prg8);
                Service ctrl8 = new Service(rep8);

                Repo rep9 = new Repo("log9.in");
                ProgState prg9 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex9);
                rep9.add(prg9);
                Service ctrl9 = new Service(rep9);

                Repo rep10 = new Repo("log10.in");
                ProgState prg10 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex10);
                rep10.add(prg10);
                Service ctrl10 = new Service(rep10);

                Repo rep11 = new Repo("log11.in");
                ProgState prg11 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex11);
                rep11.add(prg11);
                Service ctrl11 = new Service(rep11);

                Repo rep12 = new Repo("log12.in");
                ProgState prg12 = new ProgState(new MonStaque<IStmt>(),  new MonMap<String, value>(), new MonQ<value>(), ex12);
                rep12.add(prg12);
                Service ctrl12 = new Service(rep12);

                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "exit"));
                menu.addCommand(new RunExample("1",ex1.toString(),ctrl1));
                menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));
                menu.addCommand(new RunExample("3",ex3.toString(),ctrl3));
                menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
                menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
                menu.addCommand(new RunExample("6", ex6.toString(), ctrl6));
                menu.addCommand(new RunExample("7", ex7.toString(), ctrl7));
                menu.addCommand(new RunExample("8", ex8.toString(), ctrl8));
                menu.addCommand(new RunExample("9", ex9.toString(), ctrl9));
                menu.addCommand(new RunExample("10", ex10.toString(), ctrl10));
                menu.addCommand(new RunExample("11", ex11.toString(), ctrl11));
                menu.addCommand(new RunExample("12", ex12.toString(), ctrl12));

                menu.show();
               // System.exit(0);

                ctrl4.allSteps();
                //QueueInter<value> qu = prg.getOut();
                //System.out.println(qu);






            }
            catch (SomeException e)
            {
                System.out.println(e);
            }
            catch (IOException e)
            {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
