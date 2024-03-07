package hw_4;

import hw_4.model.ArrayListWrapper;
import hw_4.model.FactorArray;
import hw_4.model.IArray;
import hw_4.model.MatrixArray;
import hw_4.model.SingleArray;
import hw_4.model.VectorArray;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        Stream.of(new SingleArray<Date>(), new VectorArray<Date>(), new FactorArray<Date>(), new MatrixArray<Date>())
                        .forEach(Program::testIfArrayWorks);
        Stream.of(new SingleArray<Date>(), new VectorArray<Date>(), new FactorArray<Date>(), new MatrixArray<Date>(), new ArrayListWrapper<Date>())
                .forEach(Program::testAddArray);
    }

    private static void testIfArrayWorks(IArray<Date> array) {
        var index = new Random().nextInt(1, 50);
        var elementToAdd = new Date();

        array.add(elementToAdd, index);
        var addedElement = array.get(index);
        if (addedElement != elementToAdd) {
            throw new RuntimeException("Wrong added element");
        }
        var removedElement = array.remove(index);
        if (removedElement != elementToAdd) {
            throw new RuntimeException("Wrong removed element");
        }
    }

    private static void testAddArray(IArray<Date> array) {
        final var amountOfElementsToAdd = 100_000;
        long start = System.currentTimeMillis();

        for (int j = 0; j < amountOfElementsToAdd; j ++)
            array.add(new Date());

        System.out.println(array.getClass().getSimpleName() + " added " + amountOfElementsToAdd + " elements in: " +
                (System.currentTimeMillis() - start) + " ms");
    }
}
