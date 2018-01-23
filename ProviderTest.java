import org.awaitility.Awaitility;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class ProviderTest {

    private Provider provider = new Provider();
    private ArrayList<Integer> value = new ArrayList<>();

    @Test
    public void testProvider() throws InterruptedException {
        Awaitility.setDefaultPollInterval(500, MILLISECONDS);
        value.add(0);
        getValue();
        for (int i = 0; i < 500; ++i) {
            try {
                await().atMost(5, SECONDS).until(valueIsFive(value, 5));
                System.out.println("OK");
            } catch (Exception ex) {
                System.out.println("!----!");
            }
        }
    }

    private Callable<Boolean> valueIsFive(List<Integer> v, int num) {
        return () -> v.get(0).equals(num);
    }

    private void getValue() {
        Thread tt = new Thread(() -> calculateValue());
        tt.start();
    }

    private void calculateValue() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value.set(0, provider.getValue());
        }
    }
}

class Provider {

    private static final int MAX_VALUE = 10;
    private final Random random;

    public Provider() {
        random = new Random(System.currentTimeMillis());
    }

    public Integer getValue() {
        int value = random.nextInt(MAX_VALUE);

        System.out.println(System.currentTimeMillis() + ": " + value);
        return value;
    }
}
