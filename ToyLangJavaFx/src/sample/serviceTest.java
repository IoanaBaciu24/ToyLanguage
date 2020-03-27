package sample;

import java.util.ArrayList;

public class serviceTest {

    ArrayList<String> list;

    public ArrayList<String> getList()
    {
        list = new ArrayList<String>();
        list.add("string1");
        list.add("string2");
        list.add("string3");
        list.add("string4");

        return list;
    }

}
