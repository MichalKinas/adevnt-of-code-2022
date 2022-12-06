package day3;

import utils.FileProcesser;

import java.util.HashSet;
import java.util.Set;

class RucksackPackaging {
    public static void main(String[] args) {
        FileProcesser processer = new FileProcesser("day3/rucksack.txt");
        int sumOfPriorities = 0;
        String line = processer.getNextLine();
        while (line != null) {
            int length = line.length();
            char[] itemsArray = line.toCharArray();
            Set<Character> uniqueItems = new HashSet<>();
            // first half
            for (int i = 0; i < length / 2; i++) {
                uniqueItems.add(itemsArray[i]);
            }
            //second half
            for (int j = length / 2; j < length; j++) {
                if (uniqueItems.contains(itemsArray[j])) {
                    sumOfPriorities += getPriority(itemsArray[j]);
                    break;
                }
            }
            line = processer.getNextLine();
        }
        System.out.println("Final sum of priorities: " + sumOfPriorities);
        processer.closeReader();

        //part 2
        processer = new FileProcesser("day3/rucksack.txt");
        sumOfPriorities = 0;
        int groupMembersCount = 1;
        Set<Character> uniqueItemsG1 = new HashSet<>();
        Set<Character> uniqueItemsG12 = new HashSet<>();
        line = processer.getNextLine();
        while (line != null) {
            char[] itemsArray = line.toCharArray();
            switch (groupMembersCount % 3) {
                case 0:
                    for (char item : itemsArray) {
                        if (uniqueItemsG12.contains(item)) {
                            sumOfPriorities += getPriority(item);
                            uniqueItemsG12.clear();
                            uniqueItemsG1.clear();
                            break;
                        }
                    }
                    break;
                case 1:
                    for (char item : itemsArray) {
                        uniqueItemsG1.add(item);
                    }
                    break;
                case 2:
                    for (char item : itemsArray) {
                        if (uniqueItemsG1.contains(item)) {
                            uniqueItemsG12.add(item);
                        }
                    }
                    break;
            }
            line = processer.getNextLine();
            groupMembersCount++;
        }
        System.out.println("Final sum of priorities for groups: " + sumOfPriorities);
        processer.closeReader();
    }

    private static int getPriority(char item) {
        if (Character.isUpperCase(item)) {
            return (int) item - 38;
        } else {
            return (int) item - 96;
        }
    }
}
