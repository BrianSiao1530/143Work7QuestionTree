public class QuestionNode {
    public String data;
    public QuestionNode right;
    public QuestionNode left;
    public QuestionNode(String data, QuestionNode right, QuestionNode left){
        this.data = data;
        this.right = right;
        this.left = left;
    }
    public QuestionNode(String data){
        this(data, null, null);
    }
}
