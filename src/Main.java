public class Main {
    public static void main(String[] args) {
        //valuing the nodes
        Node f = new Node(3, null, null);
        Node g = new Node(12, null, null);
        Node h = new Node(6, null, null);
        Node i = new Node(1, null, null);
        Node d = new Node(9, i, null);
        Node e = new Node(3, g, h);
        Node c = new Node(7, null, d);
        Node b = new Node(14, f, e);
        Node a = new Node(2, b, c);

        BinarySearchTree tree = new BinarySearchTree(a);
        System.out.println("Number of nodes in the tree: " + tree.getNumOfNodes(a));
        System.out.println("Total number of nodes in the tree: " + tree.numOfNode());
        System.out.println();
        System.out.println("Height of the tree: " + tree.getDepth(a));
        System.out.println();
        System.out.println("Is the tree a binary search tree? " + tree.isBST(a, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println();
        System.out.println("Search for node with key 7: " + tree.searchByKey(a, 7));
        System.out.println();
        System.out.println("Maximum key in the tree: " + tree.getMax(a));
        System.out.println();
        System.out.println("Minimum key in the tree: " + tree.getMin(a));
        System.out.println();
        BinarySearchTree treeFromFile = new BinarySearchTree();
        treeFromFile.readFromFile("D:\\Java Projects\\BinarySearchTrees\\src\\values");
        System.out.println("Depth of the tree from file: " + treeFromFile.getDepth(treeFromFile.getRoot()));
        System.out.println();
        System.out.println("The next element after 4: " + tree.getNextNode(a, 4));
    }
}