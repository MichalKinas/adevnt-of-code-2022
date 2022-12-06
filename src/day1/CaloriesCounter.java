package day1;

import utils.FileProcesser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaloriesCounter {
    public static void main(String[] args) {
        FileProcesser processer = new FileProcesser("day1/calories.txt");
        int maxCalories = 0;
        int currentElfCalories = 0;
        int elfIndex = 1;
        int elfWithMostCalories = 1;
        String line = processer.getNextLine();

        while (line != null) {
            if (line.isEmpty()) {
                if (currentElfCalories > maxCalories) {
                    maxCalories = currentElfCalories;
                    elfWithMostCalories = elfIndex;
                }
                elfIndex++;
                currentElfCalories = 0;
            } else {
                currentElfCalories += Integer.parseInt(line);
            }
            line = processer.getNextLine();
        }
        processer.closeReader();
        System.out.println("Max calories: " + maxCalories);
        System.out.println("Elf index: " + elfWithMostCalories);

        // part 2
        FileProcesser processer2 = new FileProcesser("day1/calories.txt");
        List<Integer> maxCaloriesList = new ArrayList<>(Collections.nCopies(3, 0));
        int smallestCaloriesIndex = 0;
        currentElfCalories = 0;
        line = processer2.getNextLine();

        while (line != null) {
            if (line.isEmpty()) {
                if (currentElfCalories > maxCaloriesList.get(smallestCaloriesIndex)) {
                    maxCaloriesList.set(smallestCaloriesIndex, currentElfCalories);
                    smallestCaloriesIndex = maxCaloriesList.indexOf(Collections.min(maxCaloriesList));
                }
                currentElfCalories = 0;
            } else {
                currentElfCalories += Integer.parseInt(line);
            }
            line = processer2.getNextLine();
        }
        processer2.closeReader();
        System.out.println("Max calories from top three elves: " + maxCaloriesList.stream().reduce(0, Integer::sum));
    }
}
