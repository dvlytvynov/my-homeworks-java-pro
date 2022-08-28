package test.fork.join;

import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class Accumulator extends RecursiveTask<Double> {
    private final List<Double> list;
    private final Set<String> set;

    public Accumulator(List<Double> list, Set<String> set) {
        this.list = list;
        this.set = set;
    }

    @Override
    protected Double compute() {
        set.add(Thread.currentThread().getName());
        double result = 0;
        if (list.isEmpty()) {
            return result;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        int subListSize = list.size() / 2;
        final List<Double> subList1 = list.subList(0, subListSize);
        final List<Double> subList2 = list.subList(subListSize, list.size());
        Accumulator task1 = new Accumulator(subList1, set);
        Accumulator task2 = new Accumulator(subList2, set);
        task1.fork();
        task2.fork();
        result = result + task1.join();
        result = result + task2.join();
        return result;
    }
}
