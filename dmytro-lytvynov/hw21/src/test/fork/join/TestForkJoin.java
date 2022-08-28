package test.fork.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class TestForkJoin {
    public void test() {
        System.out.println("\n- - Test Fork Join - -");
        final List<Double> financeList = Collections.synchronizedList(new ArrayList<>());
        final Set<String> set = Collections.synchronizedSet(new HashSet<>());
        double personSalary;
        for (int i = 0; i < 153288; i++) {
            personSalary = 5000 + (i % 10) * 1000 + 0.01*(i % 100);
            financeList.add(personSalary);
        }
        final ForkJoinPool pool = new ForkJoinPool();
        final Accumulator task = new Accumulator(financeList, set);
        double salaryFund = pool.invoke(task);
        System.out.printf("Our organization has Salary Fund = %,.2f UAH\n", salaryFund);
        System.out.println("Salary Fund calculation used fallow Threads:");
        System.out.println(set);
    }
}
