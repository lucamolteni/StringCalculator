package it.sevenpixel;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.lang.String.valueOf;

public class StringCalculator {

    public int add(String numbers) {
        final Separator sep = getSeparator(numbers);
        numbers = numbers.substring(sep.getOffset());
        final String[] nums = numbers.split(sep.getSeparator());
        final ParseResult pr = new ParseResult();
        for (String s : nums) {
            if (!s.isEmpty()) {
                final Integer i = valueOf(s);
                if (i < 0) {
                    pr.addToNegative(i);
                }
                if (i < 1001) {
                    pr.addToTotal(i);
                }
            }
        }
        if (!pr.getNegativeNums().isEmpty()) {
            throw new NegativeNumberException(pr.getNegativeNums());
        }
        return pr.getTotal();
    }

    class ParseResult {
        private final List<Integer> negativeNums = new ArrayList<Integer>();
        private int total = 0;

        public List<Integer> getNegativeNums() {
            return negativeNums;
        }

        public int getTotal() {
            return total;
        }

        public void addToTotal(Integer i) {
            total += i;
        }

        public void addToNegative(Integer i) {
            negativeNums.add(i);
        }
    }

    private Separator getSeparator(String numbers) {
        if (!"".equals(numbers) && numbers.charAt(0) == '\\') {
            final String sep = numbers.substring(1).split("\\n")[0];
            return new Separator(sep, sep.length() + 2);
        } else {
            return new Separator("(,|\\n)", 0);
        }
    }

    class Separator {
        private final String separator;
        private final Integer offset;

        Separator(String separator, Integer offset) {
            this.separator = separator;
            this.offset = offset;
        }

        public String getSeparator() {
            return separator;
        }

        public Integer getOffset() {
            return offset;
        }
    }

}
