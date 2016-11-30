package chapter17_Container.exercise;

/**
 * Created by ping.wu on 2016/11/30.
 */

import net.mindview.util.TextFile;

import java.util.List;

import static net.mindview.util.Print.*;

class AssociateArray<K, V> {
    private Object[][] pairs;
    public int index;

    public AssociateArray(int length) {
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                pairs[i] = new Object[]{key, value};
                return;
            }
            if (index >= pairs.length) {
                throw new IndexOutOfBoundsException();
            }
        }
        pairs[index++] = new Object[]{key, value};
    }

    public V get(K key) {
        for(int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                return (V)pairs[i][1];
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0] + " : " + pairs[i][1]);
            result.append("\n");
        }
        return result.toString();
    }
}

public class E13_WordCounter {
    public static void main(String[] args) {
        List<String> words = new TextFile("src/chapter17_Container/exercise/E12_MapDemo.java", "\\W+");
        AssociateArray<String, Integer> map = new AssociateArray<>(words.size());
        for (String word : words) {
            Integer freq = map.get(word);
            map.put(word, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
    }
}
