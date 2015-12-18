import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Алексей on 17.12.2015.
 */
public class Autocomplete implements LocalFile, Comparator {
    public int count;
    public Map<String, Integer> map1 = new TreeMap<String, Integer>();
    public Map<String, Integer> map2 = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
    FileReader fileReader;

    public Autocomplete() {
        try {
            fileReader = new FileReader(LocalFile.locationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            count = Integer.parseInt(bufferedReader.readLine()); //читаю первую строку
        } catch (IOException e) {
            e.printStackTrace();
        }

        Autocomplete.readcomplete(count, map1, bufferedReader);

        try {
            count = Integer.parseInt(bufferedReader.readLine()); //количество запросов
        } catch (IOException e) {
            e.printStackTrace();
        }

        map2.putAll(map1); //Сортирую ключи compareToIgnoreCase

        map1 = new TreeMap<String, Integer>(this);

        int i = 0;
        while (i < count) {
            String string = null;
            try {
                string = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int h = 0;
            for (Map.Entry entry : map2.entrySet()) {
                if (entry.getKey().toString().length() >= string.length() && string.equals(entry.getKey().toString().substring(0, string.length()))) {
                    map1.put((String) entry.getKey(), (Integer) entry.getValue());
                    h++;
                }
                if (h >= 10)
                    break;
            }

            System.out.println(string);
            Autocomplete.printLists1(map1); //выводим в консоль
            i++;
            map1.clear();
        }
    }

    private static void readcomplete(int count, Map<String, Integer> map1, BufferedReader bufferedReader) {
        int i = 0;
        while (i < count) //Читаю дополнения
        {
            String string = null;
            try {
                string = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] z = string.split(" ");
            map1.put(z[0], Integer.parseInt(z[1]));
            i++;
        }
    }

    private static void printLists1(Map<String, Integer> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + "  " + (Integer) entry.getValue());
        }
    }

    @Override    public int compare(Object o1, Object o2) {
        return ((Integer) map2.get(o2)).compareTo((Integer) map2.get(o1));
    }
}
