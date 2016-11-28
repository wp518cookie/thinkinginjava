package chapter17_Container.exercise;

/**
 * Created by ping.wu on 2016/11/25.
 */
public class SList<T> {
    private Link<T> headNode;
    private Link<T> lastNode;
    private Link<T> currentNode;

    public String toString() {
        SListIterator iterator = iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
           sb.append(iterator.next()).append("  ");
        }
        return sb.toString();
    }

    public SListIterator iterator() {
        return new SListIteratorImpl();
    }

    class SListIteratorImpl implements SListIterator<T> {
        public void add(T element) {
            if (lastNode == null) {
                headNode = new Link(element);
                lastNode = headNode;
                currentNode = headNode;
            } else {
                lastNode.next = new Link(element);
                lastNode = lastNode.next;
            }
        }

        public void remove() {
            //新增个preNode;
        }

        public T next() {
            T result = currentNode.element;
            currentNode = currentNode.next;
            return result;
        }

        public boolean hasNext() {
            return currentNode != null;
        }
    }
}
