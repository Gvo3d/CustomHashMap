import java.util.Iterator;

/**
 * Created by User on 14.01.2016.
 */
public class CustomHashMap {
    public static final int MAX_SIZE = 10;
    Entry[] table = new Entry[10];

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        String key = "name";
        Object value = "1254";
        String key2 = "nas";
        Object value2 = "12542cxzxc";
        map.put(key, value);
        map.put(key2, value2);

        System.out.println(map.get("name").toString());
    }

    public void put(Object key, Object value){
        Entry entry = new Entry(key, value);
        int index = key.hashCode() % MAX_SIZE;
        System.out.println(index);
        if (table[index]!=null) {
            for (Object en:table[index]){
                Entry en2 = (Entry) en;
                if (!en2.iterator().hasNext()) {
                    en2.next=entry;
                    break;
                }
            }
        } else table[index] = entry;
    }

    public Object get(Object key){
        int index = key.hashCode() % MAX_SIZE;
        Entry currentEntry = table[index];
        while (!currentEntry.key.equals(key)){
            currentEntry = currentEntry.next;
        }
        return currentEntry.value;
    }

    private class Entry implements Iterable{
        Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Iterator iterator() {
            return new EntryIterator(table[0]);
        }

        private class EntryIterator implements Iterator{
            private Entry current;

            public EntryIterator(Entry entry) {
                this.current=entry;
            }

            @Override
            public boolean hasNext() {
                return (current.next!=null);
            }

            @Override
            public Object next() {
                Object temp = current;
                current=current.next;
                return temp;
            }
        }
    }




}
