import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MergeSort <T extends Comparable<? super T>> extends RecursiveTask <List<T>> {
    private final List<T> list;

    public MergeSort(List<T> list) {
        this.list = list;
    }

    @Override
    protected List<T> compute() {
        if (list.size() > 1) {
            var leftList = new LinkedList<>(list.subList(0, list.size() / 2));
            var rightList = new LinkedList<>(list.subList(list.size() / 2, list.size()));

            var leftMergeSort = new MergeSort<>(leftList);
            var rightMergeSort = new MergeSort<>(rightList);

            leftMergeSort.fork();

             merge(leftMergeSort.join(), rightMergeSort.compute());
        }
        return list;
    }

    private void merge(List<T> leftList, List<T> rightList) {
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) < 0) {
                list.set(leftIndex + rightIndex, leftList.get(leftIndex++));
            } else {
                list.set(rightIndex + leftIndex, rightList.get(rightIndex++));
            }
        }

        while (leftIndex < leftList.size()) {
            list.set(leftIndex + rightIndex, leftList.get(leftIndex++));
        }

        while (rightIndex < rightList.size()) {
            list.set(leftIndex + rightIndex, rightList.get(rightIndex++));
        }

    }
}
