import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BinaryTree binaryTree = new BinaryTree();

        int[] intArray = {2, 1, 3, 4, -1, 5, 7};
        for(int i : intArray){
            binaryTree.insert(i);
        }


        System.out.println(binaryTree.search(0));

        //binaryTree.preOrder();
        System.out.println();
        binaryTree.remove(2);
        //binaryTree.preOrder();

        binaryTree.inOrder();
        //binaryTree.postOrder();

        binaryTree.treeToStringDot();
    }
}
