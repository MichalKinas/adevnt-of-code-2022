package day6;

import utils.FileProcesser;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class PacketDetector {
    public static void main(String[] args) {
        FileProcesser processer = new FileProcesser("day6/datastreamBuffer.txt");
        processDatastream(processer, 4);
        processer.closeReader();

        //part 2
        processer = new FileProcesser("day6/datastreamBuffer.txt");
        processDatastream(processer, 14);
        processer.closeReader();
    }

    private static void processDatastream(FileProcesser processer, int sequenceLength) {
        int processedBeforePacketStart = 0;
        LinkedList<Character> uniqueCharacters = new LinkedList<>();
        String line = processer.getNextLine();
        while (line != null) {
            char[] characters = line.toCharArray();
            for (char character : characters) {
                processedBeforePacketStart++;
                uniqueCharacters.add(character);
                if (uniqueCharacters.size() == sequenceLength) {
                    if (!checkForDuplicates(uniqueCharacters)) {
                        System.out.println("Characters processed: " + processedBeforePacketStart + " for " + sequenceLength + " sequence length");
                        break;
                    } else {
                        uniqueCharacters.remove();
                    }
                }
            }
            line = processer.getNextLine();
        }
    }

    private static boolean checkForDuplicates(LinkedList<Character>  list) {
        Set<Character> uniques = new HashSet<>();
        for (Character character : list) {
            if (uniques.contains(character)) {
                return true;
            } else {
                uniques.add(character);
            }
        }
        uniques.clear();
        return false;
    }
}
