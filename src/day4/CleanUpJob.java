package day4;

import utils.FileProcesser;

public class CleanUpJob {
    public static void main(String[] args) {
        FileProcesser processer = new FileProcesser("day4/cleanupPairs.txt");
        int overlappingPairsCount = 0;
        String line = processer.getNextLine();
        while (line != null) {
            String[] pair = line.split(",");
            String[] firstElfRange = pair[0].split("-");
            String[] secondElfRange = pair[1].split("-");
            if (isPairFullyOverlapping(rangeToNumber(firstElfRange), rangeToNumber(secondElfRange))) {
                overlappingPairsCount++;
            }
            line = processer.getNextLine();
        }
        System.out.println("Fully overlapping pairs count: " + overlappingPairsCount);
        processer.closeReader();

        //part 2
        processer = new FileProcesser("day4/cleanupPairs.txt");
        overlappingPairsCount = 0;
        line = processer.getNextLine();
        while (line != null) {
            String[] pair = line.split(",");
            String[] firstElfRange = pair[0].split("-");
            String[] secondElfRange = pair[1].split("-");
            if (isPairPartlyOverlapping(rangeToNumber(firstElfRange), rangeToNumber(secondElfRange))) {
                overlappingPairsCount++;
            }
            line = processer.getNextLine();
        }
        System.out.println("Partly overlapping pairs count: " + overlappingPairsCount);
        processer.closeReader();
    }

    private static int[] rangeToNumber(String[] range) {
        int[] rangeNum = new int[range.length];
        for (int i = 0; i < range.length; i++) {
            rangeNum[i] = Integer.parseInt(range[i]);
        }
        return rangeNum;
    }

    private static boolean isPairFullyOverlapping(int[] elfOneRange, int[] elfTwoRange) {
        return (elfOneRange[0] <= elfTwoRange[0] && elfOneRange[1] >= elfTwoRange[1] ||
                elfTwoRange[0] <= elfOneRange[0] && elfTwoRange[1] >= elfOneRange[1]);
    }

    private static boolean isPairPartlyOverlapping(int[] elfOneRange, int[] elfTwoRange) {
        return (elfTwoRange[1] - elfOneRange[0]) * (elfOneRange[1] - elfTwoRange[0]) >= 0;
    }
}
