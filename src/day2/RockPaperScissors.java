package day2;

import utils.FileProcesser;

import java.util.HashMap;
import java.util.Map;

class RockPaperScissors {
    public static void main(String[] args) {
        // x -> 1, y -> 2, z -> 3
        // AX -> rock, BY -> paper, CZ -> scissors
        FileProcesser processer = new FileProcesser("day2/strategy.txt");
        Map<String, Integer> duelOutcomeMap = new HashMap<>();
        duelOutcomeMap.put("A X", 4);
        duelOutcomeMap.put("A Y", 8);
        duelOutcomeMap.put("A Z", 3);
        duelOutcomeMap.put("B X", 1);
        duelOutcomeMap.put("B Y", 5);
        duelOutcomeMap.put("B Z", 9);
        duelOutcomeMap.put("C X", 7);
        duelOutcomeMap.put("C Y", 2);
        duelOutcomeMap.put("C Z", 6);
        System.out.println("Final score: " + getFinalScore(processer, duelOutcomeMap));

        // part 2
        // x -> 1, y -> 2, z -> 3
        // A -> rock, B -> paper, C -> scissors
        // x -> lose, y -> draw, z -> win
        processer = new FileProcesser("day2/strategy.txt");
        duelOutcomeMap.put("A X", 3);
        duelOutcomeMap.put("A Y", 4);
        duelOutcomeMap.put("A Z", 8);
        duelOutcomeMap.put("B X", 1);
        duelOutcomeMap.put("B Y", 5);
        duelOutcomeMap.put("B Z", 9);
        duelOutcomeMap.put("C X", 2);
        duelOutcomeMap.put("C Y", 6);
        duelOutcomeMap.put("C Z", 7);
        System.out.println("Final score part 2: " + getFinalScore(processer, duelOutcomeMap));
    }

    private static int getFinalScore(FileProcesser processer, Map<String, Integer> duelOutcomeMap) {
        int finalScore = 0;
        String line = processer.getNextLine();
        while (line != null) {
            finalScore += duelOutcomeMap.get(line);
            line = processer.getNextLine();
        }
        processer.closeReader();
        return finalScore;
    }
}