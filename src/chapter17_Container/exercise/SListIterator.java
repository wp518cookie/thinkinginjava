package chapter17_Container.exercise;

/**
 * Created by ping.wu on 2016/11/25.
 */
public interface SListIterator<T> {
    public void add(T element);
    public void remove();
    public boolean hasNext();
    public T next();
}
