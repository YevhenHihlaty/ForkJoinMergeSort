import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        for (int i = 1_000; i >= 0; i --){
            list.add(i);
        }

        list.stream().forEach(n -> System.out.print(n + " "));


        System.out.println();
        var mergeSort = new MergeSort<>(list);
        mergeSort.compute();


        list.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
