import java.util.Date;

/**
 * Created by Алексей on 17.12.2015.
 */
public class Main {
    public static void main(String[] args)
    {
        long millis = new Date().getTime();
        new Autocomplete();
        long millis2 = new Date().getTime();
        System.out.println(millis2-millis);
    }
}
