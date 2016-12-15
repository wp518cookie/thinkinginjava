package chapter17_Container;

/**
 * Created by ping.wu on 2016/12/14.
 */
public abstract class Test<C> {
    String name;

    public Test(String name) {
        this.name = name;
    }

    abstract int test(C container, TestParam tp);
}
