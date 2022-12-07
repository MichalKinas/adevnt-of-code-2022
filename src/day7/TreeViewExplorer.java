package day7;

import utils.FileProcesser;
import utils.NodeType;
import utils.Node;

import java.util.Optional;

public class TreeViewExplorer {
    private static int sumOfAllMatchingDirs = 0;
    private static int freedSpaceAfterDeletingDir = Integer.MAX_VALUE;
    private static int dirToDeleteSize = 0;
    private static Node<String> root = new Node<>("/", NodeType.DIRECTORY);
    private static Node<String> activeNode = root;

    public static void main(String[] args) {
        FileProcesser processer = new FileProcesser("day7/terminalOutput.txt");
        String line = processer.getNextLine();
        while (line != null) {
            buildTree(line);
            line = processer.getNextLine();
        }
        traverseAndSetDirectorySize(root);
        System.out.println("Sum of sizes from all matching dirs: " + sumOfAllMatchingDirs);
        traverseAndFindSmallestDirToDelete(root, (30000000 - (70000000 - root.getNodeSize())));
        System.out.println("The best directory to delete size is: " + dirToDeleteSize);
        processer.closeReader();
    }

    private static void buildTree(String line) {
        line = line.replace("$ ", "");
        String[] commands = line.split(" ");
        switch (commands[0]) {
            case "cd":
                if (commands[1].equals("..")) {
                    activeNode = activeNode.getNodeParent();
                } else {
                    if (!commands[1].equals("/")) {
                        Optional<Node<String>> filteredNode = activeNode.getChildren().stream().filter(node -> node.getNodeData().equals(commands[1])).findFirst();
                        if (filteredNode.isPresent()) {
                            activeNode = filteredNode.get();
                        } else {
                            System.err.println("Incorrect folder name passed");
                        }
                    }
                }
                break;
            case "ls":
                break;
            case "dir":
                Node<String> newChild = new Node<>(activeNode, commands[1], NodeType.DIRECTORY);
                if (commands[1].equals("/")) {
                    root = newChild;
                } else {
                    activeNode.getChildren().add(newChild);
                }
                break;
            default:
                Node<String> newNode = new Node<>(activeNode, commands[1], NodeType.FILE);
                newNode.setNodeSize(Integer.parseInt(commands[0]));
                activeNode.getChildren().add(newNode);
                break;
        }
    }

    private static int traverseAndSetDirectorySize(Node<String> node) {
        int folderSize = 0;
        if (node.getType() == NodeType.FILE) {
            return node.getNodeSize();
        } else {
            for (Node<String> n : node.getChildren()) {
                folderSize += traverseAndSetDirectorySize(n);
            }
            node.setNodeSize(folderSize);
            if (node.getNodeSize() <= 100000) {
                sumOfAllMatchingDirs += node.getNodeSize();
            }
            return folderSize;
        }
    }

    private static void traverseAndFindSmallestDirToDelete(Node<String> node, int minSpaceRequired) {
        if (node.getType() == NodeType.DIRECTORY) {
            int curentFreedUpSpace = node.getNodeSize() - minSpaceRequired;
            if (curentFreedUpSpace >= 0 && curentFreedUpSpace < freedSpaceAfterDeletingDir) {
                freedSpaceAfterDeletingDir = curentFreedUpSpace;
                dirToDeleteSize = node.getNodeSize();
            }
            for (Node<String> n : node.getChildren()) {
                traverseAndFindSmallestDirToDelete(n, minSpaceRequired);
            }
        }
    }
}
