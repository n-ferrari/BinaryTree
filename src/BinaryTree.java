import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryTree {
    Node root = null;
    String graphBuilder = "";

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(int value) {
        if (this.root == null) {
            this.root = new Node(value);
            //System.out.println(value + " inserted as root");
        } else {
            this.insert(value, this.root);
        }
    }

    private void insert(int value, Node actualPosition) {
        if (value < actualPosition.getValue()) {
            if (actualPosition.getLeft() == null) {
                actualPosition.setLeft(new Node(value));
                //System.out.println(value + " inserted on the left of node " + actualPosition.getValue());
            } else {
                insert(value, actualPosition.getLeft());
            }
        } else if (value > actualPosition.getValue()) {
            if (actualPosition.getRight() == null) {
                actualPosition.setRight(new Node(value));
                //System.out.println(value + " inserted on the right of node " + actualPosition.getValue());
            } else {
                insert(value, actualPosition.getRight());
            }
        }
    }

    public Node search(int value) {
        return search(value, this.root);
    }

    private Node search(int value, Node actualPosition) {
        if (actualPosition == null) {
            return null;
        } else if (actualPosition.getValue() == value) {
            return actualPosition;
        } else if (value < actualPosition.getValue()) {
            return search(value, actualPosition.getLeft());
        } else {
            return search(value, actualPosition.getRight());
        }
    }

    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(Node actualPosition) {
        if (actualPosition != null) {
            System.out.print(actualPosition.getValue() + " ");
            if (actualPosition.getLeft() != null) {
                preOrder(actualPosition.getLeft());
                if (actualPosition.getRight() != null) {
                    preOrder(actualPosition.getRight());
                }
            } else if (actualPosition.getRight() != null) {
                preOrder(actualPosition.getRight());
                }
        }
    }

    public void inOrder(){
        inOrder(this.root);
    }

    private void inOrder(Node actualPosition){
        if(actualPosition.getLeft() != null){
            inOrder(actualPosition.getLeft());
        }
        System.out.println(actualPosition.getValue());
        if(actualPosition.getRight() != null){
            inOrder(actualPosition.getRight());
        }
    }

    public void postOrder(){
        postOrder(this.root);
    }

    private void postOrder(Node actualPosition){
        if(actualPosition.getLeft() != null){
            postOrder(actualPosition.getLeft());
        }
        if(actualPosition.getRight() != null){
            postOrder(actualPosition.getRight());
        }
        System.out.println(actualPosition.getValue());
    }

    public void remove(int value){
        if(search(value) == null ){
            System.out.println("Value not found in binary tree!");
        }else{
            remove(this.root, value);
        }
    }
    private void remove(Node parent, int value) {
        //treating ROOT
        if (parent.getValue() == value) { //root
            if(parent.getRight() != null){ //se houver filho direito
                int newRoot = parent.getRight().getValue();
                remove(parent, parent.getRight().getValue());
                this.root.setValue(newRoot);

                //if (temp.getLeft() == null) { //se nao houver filho esquerdo
                //    parent.setRight(temp.getRight());
            }else if(parent.getLeft() != null){
                int newRoot = parent.getLeft().getValue();
                remove(parent, parent.getLeft().getValue());
                this.root.setValue(newRoot);
            }else{
                this.root = null;
            }
        } else if (parent.getRight() != null && parent.getRight().getValue() == value) { //se o valor a excluir estiver no filho direito
            Node temp = parent.getRight();
            if (temp.getLeft() == null) { //se nao houver filho esquerdo
                parent.setRight(temp.getRight());
            } else if (temp.getLeft().getRight() != null) { //se houver filho direito no filho esquerda
                parent.getRight().setValue(temp.getLeft().getRight().getValue());
                remove(temp.getLeft(), temp.getLeft().getRight().getValue());
            } else {//se nao houver filho direito no filho esquerda
                parent.setRight(temp.getLeft());
                parent.getRight().setRight(temp.getRight());
            }
        } else if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
            Node temp = parent.getLeft();
            if (temp.getRight() == null) { //se nao houver filho direito
                parent.setLeft(temp.getLeft());
            } else if (temp.getRight().getLeft() != null) { //se houver filho esquerdo no filho direito
                parent.getLeft().setValue(temp.getRight().getLeft().getValue());
                remove(temp.getRight(), temp.getRight().getLeft().getValue());
            } else {//se nao houver filho esquerdo no filho direito
                parent.setLeft(temp.getRight());
                parent.getLeft().setLeft(temp.getLeft());
            }
        } else if(parent.getValue() > value){
            remove(parent.getLeft(), value);
        } else{
            remove(parent.getRight(), value);
        }
    }



    public void stringDot(){
        stringDot(this.root);
    }

    public void stringDot(Node actualPosition){
        if (actualPosition != null) {
            System.out.print(actualPosition.getValue() + "->");
            if (actualPosition.getLeft() != null) {
                System.out.print(actualPosition.getLeft().getValue());
                preOrder(actualPosition.getLeft());
                if (actualPosition.getRight() != null) {
                    preOrder(actualPosition.getRight());
                }
            } else if (actualPosition.getRight() != null) {
                preOrder(actualPosition.getRight());
            }
        }
    }
    public void treeToStringDot() throws IOException {
        this.graphBuilder = "";
        treeToStringDot(this.root);
        createDot();
    }

    private void treeToStringDot(Node node){
        if(node.getLeft() != null){
            graphBuilder = graphBuilder.concat(node.getValue() + " -> " + node.getLeft().getValue() + "\n");
            treeToStringDot(node.getLeft());
        }
        if(node.getRight() != null){
            graphBuilder = graphBuilder.concat(node.getValue() + " -> " + node.getRight().getValue() + "\n");
            treeToStringDot(node.getRight());
        }
    }


    private void createDot() throws IOException {
        String textDOT = "digraph G {\n";
        textDOT = textDOT.concat(graphBuilder);
        textDOT = textDOT.concat("}\n");

        writeTextToFile("output.dot", textDOT);
    }

    private void writeTextToFile(String fileName, String text) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(text.getBytes());
        outputStream.close();
    }


}
