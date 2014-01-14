package it.sevenpixel;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.valueOf;

public class StringCalculator {

    public int add(String numbers) {
        final Separator sep = getSeparator(numbers);
        numbers = getStringWithouthSeparators(numbers);
        final String[] nums = numbers.split(sep.getSeparatorString());
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

    private String getStringWithouthSeparators(String numbers) {
        String[] split = numbers.split("\n");
        if (numbers.startsWith("//")) {
            String[] purged = Arrays.copyOfRange(split, 1, split.length);
            return joinString(Arrays.asList(purged), "");
        } else return numbers;

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

    final Pattern pattern = Pattern.compile("[(.*)]|(.*)");


    private Separator getSeparator(String numbers) {
        if (!"".equals(numbers) && numbers.charAt(0) == '/') {
            final String[] sep = numbers.substring(2).split("\\n");
            final Matcher m = pattern.matcher(sep[0]);
            final List<String> separators = new ArrayList<String>();
            int count = 0;
            while (m.find()) {
                final String group = m.group(count++);
                if (!group.isEmpty()) {
                    separators.add(group);
                }
            }
            final Separator s = new Separator();
            s.getSeparators().addAll(separators);
            return s;
        } else {
            return new Separator();
        }
    }

    class Separator {
        private final List<String> separators = new ArrayList<String>();

        public List<String> getSeparators() {
            return separators;
        }

        public String getSeparatorString() {
            if (separators.isEmpty()) {
                return "(,|\\n)";
            } else {
                return String.format("(%s)", joinString(getSeparators(), "|"));
            }
        }

    }


    public static String joinString(Collection<String> strings, String separator) {
        Iterator<String> i = strings.iterator();
        StringBuilder sb = new StringBuilder();
        if (i.hasNext()) {
            sb.append(i.next());
            while (i.hasNext())
                sb.append(separator).append(i.next());
        }
        return sb.toString();
    }

}
