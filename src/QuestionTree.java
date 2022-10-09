import java.io.*;
import java.util.*;

public class QuestionTree {
    public QuestionNode overallRoot;
    public Scanner console = new Scanner(System.in) ;

    QuestionTree() {
        overallRoot = new QuestionNode("computer");
    }

    public void read(Scanner input) {
        overallRoot = read(input, overallRoot);
    }
    private QuestionNode read(Scanner input, QuestionNode root) {
        String QOrA = input.nextLine();
        String data = input.nextLine();
        if (QOrA.equals("Q:")) {
            root = new QuestionNode(data);
            root.left = read(input, root.left);
            root.right = read(input, root.right);
        }else{
            root = new QuestionNode(data);
        }
        return root;
    }
    public void write(PrintStream output){
        overallRoot = write(output, overallRoot);
    }
    private QuestionNode write(PrintStream output, QuestionNode root){
        if(root.data != null){
            if (root.left == null && root.right == null) {
                output.println("A: ");
                output.println(root.data);
            }
            else {
                output.println("Q: ");
                output.println(root.data);
                root.left = write(output, root.left);
                root.right = write(output, root.right);
            }
        }
        return root;
    }
    public void askQuestions(){
        overallRoot = askQuestions(overallRoot);
    }
    private QuestionNode askQuestions(QuestionNode root){
        if (root.data.contains("?")) {
            if (yesTo(root.data)) {
                root.left = askQuestions(root.left);
            } else {
                root.right = askQuestions(root.right);
            }
        } else {
            if (yesTo("Would your object happen to be " + root.data + "?")){
                System.out.println("Great, I got it right!");
            } else{
                System.out.print("What is the name of your object? ");
                String ans = console.nextLine();
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguish between your object");
                System.out.print("and mine-->");
                String question = console.nextLine();
                if(yesTo("And what is the answer for your object?")){
                    root.left = new QuestionNode(ans);
                    root.right = new QuestionNode(root.data);
                    root.data = question;
                }
                else {
                    root.right = new QuestionNode(ans);
                    root.left = new QuestionNode(root.data);
                    root.data = question;
                }
            }
        }
        return root;
    }
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

}
