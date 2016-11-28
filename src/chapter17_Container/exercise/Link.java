package chapter17_Container.exercise;

/**
 * Created by ping.wu on 2016/11/25.
 */
public class Link<T> {
    T element;
    Link<T> next;

    Link(T element) {
        this.element = element;
    }

    Link() {

    }
}
