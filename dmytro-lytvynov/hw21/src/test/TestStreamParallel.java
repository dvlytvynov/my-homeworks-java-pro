package test;

import java.util.ArrayList;
import java.util.List;

public class TestStreamParallel {
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i * 11 + 125);
        }
        List<Integer> listAfterStream = list
                .stream()
                .parallel()
                .map(integer -> integer / 17)
                .map(integer -> integer + 42)
                .map(String::valueOf)
                .map(str -> {
                    System.out.println(Thread.currentThread());
                    return str.concat("-String");
                })
                .map(str -> str.split("-")[0])
                .map(Integer::parseInt)
                .toList();
        System.out.println(listAfterStream);
    }
}
