package it.sevenpixel;


import java.util.ArrayList;
import java.util.List;

public class NegativeNumberException extends RuntimeException {
    private List<Integer> numbers = new ArrayList<Integer>();

    public NegativeNumberException(List<Integer> numbers) {
        this.numbers.addAll(numbers);
    }

    @Override
    public String getMessage() {
        return String.format("No negative numbers allowed: %s", numbers);
    }
}
