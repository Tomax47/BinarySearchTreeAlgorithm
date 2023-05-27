import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public int getNumOfNodes(Node node) {
        if (node == null) return 0;
        return 1 + getNumOfNodes(node.getLeft()) + getNumOfNodes(node.getRight());
    }

    public int numOfNode() {
        return getNumOfNodes(root);
    }

    public int getDepth(Node node) {
        if (node == null) return 0;
        int l = getDepth(node.getLeft());
        int r = getDepth(node.getRight());
        return Math.max(l, r) + 1;
    }

    public boolean isBST(Node node, int low, int high) {
        if (node == null) return true;
        if (node.getKey() <= low || node.getKey() >= high) {
            return false;
        }
        return isBST(node.getLeft(), low, node.getKey()) && isBST(node.getRight(), node.getKey(), high);
    }

    public Node searchByKey(Node node, int key) {
        if (node == null || node.getKey() == key) {
            return node;
        }
        if (key < node.getKey()) {
            return searchByKey(node.getLeft(), key);
        }
        return searchByKey(node.getRight(), key);
    }

    public Node getMax(Node node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }

    public Node getMin(Node node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    public void readFromFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            int n = scanner.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node();
            }
            for (int i = 0; i < n; i++) {
                nodes[i].setKey(scanner.nextInt());
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                if (left != 0) {
                    nodes[i].setLeft(nodes[left - 1]);
                    nodes[left - 1].setParent(nodes[i]);
                }
                if (right != 0) {
                    nodes[i].setRight(nodes[right - 1]);
                    nodes[right - 1].setParent(nodes[i]);
                }
            }
            root = nodes[0];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getNextNode(Node root, int target) {
        Node prev = null;
        while (root != null) {
            if (root.getKey() == target) {
                if (root.getRight() != null) {
                    Node node = root.getRight();
                    while (node.getLeft() != null) {
                        node = node.getLeft();
                    }
                    return node;
                } else {
                    return prev;
                }
            } else if (root.getKey() > target) {
                prev = root;
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }
        return null;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.getKey()) {
            root.setLeft(deleteRec(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(deleteRec(root.getRight(), key));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
            Node successor = findMinRecursive(root.getRight());
            root.setKey(successor.getKey());
            root.setRight(deleteRec(root.getRight(), successor.getKey()));
        }
        return root;
    }

    private Node findMinRecursive(Node root) {
        if (root.getLeft() == null) {
            return root;
        }
        return findMinRecursive(root.getLeft());
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key, null, null);
        }
        if (key < root.getKey()) {
            root.setLeft(insertRec(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(insertRec(root.getRight(), key));
        }
        return root;
    }

    public void deleteVertices(int[] keys) {
        for (int key : keys) {
            root = deleteVertices(root, key);
        }
    }

    private Node deleteVertices(Node root, int key) {
        return root;
    }

    private int countVer(Node node) {
        return countVerticesRecursive(root);
    }

    private int countVerticesRecursive(Node node) {
        if (node == null) {
            return 0;
        }

        int leftCount = countVerticesRecursive(node.getLeft());
        int rightCount = countVerticesRecursive(node.getRight());

        return leftCount + rightCount + 1;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}