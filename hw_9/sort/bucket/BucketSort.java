package hw_9.sort.bucket;

import hw_9.sort.LinearSorter;

import java.util.ArrayList;
import java.util.LinkedList;

public class BucketSort implements LinearSorter {

    public void sort(int[] inputArray, int maxValue) {
        final var initArrayLength = inputArray.length;
        final var bucketArray = new ArrayList<LinkedList<Integer>>(initArrayLength);

        for (int i = 0; i < initArrayLength; i++) {
            bucketArray.add(new LinkedList<>());
        }

        for (var value : inputArray) {
            var bucketIndex = (int) (((long) value * initArrayLength) / (maxValue + 1));
            bucketArray.get(bucketIndex).add(value);
        }

        var counter = 0;
        for (var bucket : bucketArray) {
            if (bucketArray.size() > 0) {
                bucket.sort(Integer::compareTo);
                for (var val : bucket) {
                    inputArray[counter] = val;
                    counter++;
                }
            } else {
                inputArray[counter] = bucket.element();
                counter++;
            }
        }
    }
}