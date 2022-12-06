package day5;

import utils.FileProcesser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Cargo {
    private static final Map<Integer, Stack<String>> stackMap = new HashMap<>();

    public static void main(String[] args) {
        FileProcesser processer = new FileProcesser("day5/cargoPlan.txt");
        for (int i = 1; i < 10; i++) {
            stackMap.put(i, new Stack<>());
        }
        String line = processer.getNextLine();
        while (line != null) {
            parseNextLine(line, 9000);
            line = processer.getNextLine();
        }
        System.out.println("Final arrangement for CrateMover9000: " + getFinalArrangement());
        processer.closeReader();

        //part 2
        // clear all stacks
        for (int i = 1; i < 10; i++) {
            stackMap.get(i).clear();
        }
        processer = new FileProcesser("day5/cargoPlan.txt");
        line = processer.getNextLine();
        while (line != null) {
            parseNextLine(line, 9001);
            line = processer.getNextLine();
        }
        System.out.println("Final arrangement for CrateMover9001: " + getFinalArrangement());
        processer.closeReader();
    }

    private static void parseNextLine(String line, int crateMoverVersion) {
        if (!line.isEmpty()) {
            //fill empty spaces with some default items to easier manage input
            line = line.replaceAll("    ", " [x]");
        }
        if (!line.startsWith(" ")) {
            if (line.startsWith("move")) {
                String[] stackInput = line.split(" ");
                int quantity = 0;
                int stackFrom = 0;
                int stackTo;
                for (int i = 0; i < stackInput.length; i++) {
                    switch (stackInput[i]) {
                        case "move" -> {
                            i++;
                            quantity = Integer.parseInt(stackInput[i]);
                        }
                        case "from" -> {
                            i++;
                            stackFrom = Integer.parseInt(stackInput[i]);
                        }
                        case "to" -> {
                            i++;
                            stackTo = Integer.parseInt(stackInput[i]);
                            switch (crateMoverVersion) {
                                case 9000 -> manipulateStacksV9000(stackMap.get(stackFrom), stackMap.get(stackTo), quantity);
                                case 9001 -> manipulateStacksV9001(stackMap.get(stackFrom), stackMap.get(stackTo), quantity);
                            }
                        }
                    }
                }
            } else {
                String[] stackInput = line.split(" ");
                for (int i = 0; i < stackInput.length; i++) {
                    if (!stackInput[i].isEmpty() && !stackInput[i].equals("[x]")) {
                        stackMap.get(i+1).push(stackInput[i]);
                    }
                }
            }
        } else if (line.startsWith(" 1")) {
            //when reading stack content is finished reverse them to get correct order
            for (int i = 1; i < 10; i++) {
                reverseStack(stackMap.get(i));
            }
        }
    }

    private static void reverseStack(Stack<String> stackToReverse) {
        Stack<String> helperStack = new Stack<>();
        while (!stackToReverse.isEmpty()) {
            helperStack.push(stackToReverse.pop());
        }
        stackToReverse.addAll(helperStack);
        helperStack.clear();
    }

    private static void manipulateStacksV9000(Stack<String> stackFrom, Stack<String> stackTo, int quantity) {
        while (quantity > 0) {
            stackTo.push(stackFrom.pop());
            quantity--;
        }
    }

    private static void manipulateStacksV9001(Stack<String> stackFrom, Stack<String> stackTo, int quantity) {
        Stack<String> tmpStack = new Stack<>();
        while (quantity > 0) {
            tmpStack.push(stackFrom.pop());
            quantity--;
        }
        while (!tmpStack.isEmpty()) {
            stackTo.push(tmpStack.pop());
        }
    }

    private static String getFinalArrangement() {
        String finalTopStacks = "";
        for (int i = 1; i < 10; i++) {
            finalTopStacks += stackMap.get(i).pop();
        }
        return finalTopStacks;
    }
}
