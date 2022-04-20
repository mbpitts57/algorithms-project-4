import java.io.IOException;
import java.util.Scanner;

public class guess {
    public static char userAnswer;
    public static int numChips, rangeOfNums, option, numQuestions, targetNum, qNum, chips, range;
    public static int c;
    public static int i, j, k, l, m;
    public static int[][] costs, guesses;
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        // evaluates the user-provided arguments.
        if (args.length == 3) {
            numChips = Integer.parseInt(args[0]); // k, or m
            rangeOfNums = Integer.parseInt(args[1]); // n
            option = Integer.parseInt(args[2]);
        } else {
            System.out.println("Three arguments expected. " + args.length
                    + " received.\nUsage: java Guess m n option\n m: the number of chips\n n: the range of target number\n option:\n    0 - show minimum number of questions\n    1 - show the table of minimum number of questions\n    2 - show the table of minimum number of questions and the optimal guesses\n    3 - play the game with user as the referee\n");
            return;
        }

        // adding 1 because range is inclusive.
        costs = new int[numChips + 1][rangeOfNums + 1];
        guesses = new int[numChips + 1][rangeOfNums + 1];
        for (i = 1; i <= rangeOfNums; i++) {
            costs[1][i] = i;
            guesses[1][i] = 1;
        }
        for (i = 1; i <= numChips; i++) {
            costs[i][1] = 1;
            guesses[i][1] = 1;
        }
        for (j = 2; j <= numChips; j++) {
            for (k = 2; k <= rangeOfNums; k++) {
                costs[j][k] = rangeOfNums + 1;
                for (l = 1; l <= k; l++) {
                    if (costs[j - 1][l - 1] > costs[j][k - l]) {
                        m = (costs[j - 1][l - 1] + 1);
                    } else {
                        m = (costs[j][k - l] + 1);
                    }
                    if (m < costs[j][k]) {
                        costs[j][k] = m;
                        guesses[j][k] = l;
                    }
                }
            }
        }

        numQuestions = costs[numChips][rangeOfNums];
        // if-else statements for each option
        if (option == 0) {
            System.out.printf(
                    "Molly Pitts\nFor a target number between %d and 0, with %d chips, it takes at most %d questions to identify the target number in the worst case.\n",
                    rangeOfNums, numChips, numQuestions);
        } else if (option == 1) {
            System.out.println("Molly Pitts");
            for (i = 1; i <= numChips; i++) {
                for (j = 1; j <= rangeOfNums; j++) {
                    System.out.printf("costs[%d][%d] = %d, ", i, j, costs[i][j]);
                }
                System.out.println();
            }
        } else if (option == 2) {
            System.out.println("Molly Pitts");
            for (i = 1; i <= numChips; i++) {
                for (j = 1; j <= rangeOfNums; j++) {
                    System.out.printf("costs[%d][%d] = %d, ", i, j, costs[i][j]);
                }
                System.out.println();
            }
            for (i = 1; i <= numChips; i++) {
                for (j = 1; j <= rangeOfNums; j++) {
                    System.out.printf("guesses[%d][%d] = %d, ", i, j, guesses[i][j]);
                }
                System.out.println();
            }
        } else if (option == 3) {
            System.out.printf(
                    "Molly Pitts\nFor a target number between %d and 0, with %d chips, it takes at most %d questions to identify the target number in the worst case.\n",
                    rangeOfNums, numChips, numQuestions);
            System.out.printf("Please pick a number between 0 and %d in your mind, and let's play the game. :-|\n",
                    rangeOfNums);
                    
                    qNum = 1;
                    targetNum = 0;
                    chips = numChips;
                    range = rangeOfNums;
                    c = guesses[chips][range];
                    while (true) {
                      System.out.printf("Number of Chips Remaining = %d. Question %d: Is the target integer less than %d? (Y/N) ", chips, qNum++, targetNum + c);
                      userAnswer = scan.next().toLowerCase().charAt(0);
                      if (userAnswer == 'y') {
                        chips--;
                        range = c - 1;
                      } else {
                        range -= c;
                        targetNum += c;
                      } 
                      c = guesses[chips][range];
                      if (c == 0) {
                        System.out.printf("I nailed it! The target number is %d!! ;-)\n", targetNum);
                        break;
                      } 
            }
        }
    }
}