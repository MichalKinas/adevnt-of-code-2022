package utils;

import java.util.ArrayList;
import java.util.List;


public class Node<T> {
    private T nodeData;
    private final Node<T> nodeParent;
    private List<Node<T>> children;
    private int nodeSize;
    private NodeType type;

    public Node(Node<T> parent, T data, NodeType nodeType) {
        nodeData = data;
        nodeParent = parent;
        children = new ArrayList<>();
        type = nodeType;
    }

    public Node(T data, NodeType nodeType) {
        nodeData = data;
        nodeParent = null;
        children = new ArrayList<>();
        type = nodeType;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> getNodeParent() {
        return nodeParent;
    }

    public T getNodeData() {
        return nodeData;
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }

    public NodeType getType() {
        return type;
    }
}
