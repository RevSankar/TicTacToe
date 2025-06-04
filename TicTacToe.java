import java.util.Scanner;

public class TicTacToe {
    private static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int move;
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
            move = sc.nextInt() - 1;
            if (move < 0 || move > 8 || board[move] != ' ') {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board[move] = currentPlayer;
            if (checkWinner()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (isDraw()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    private static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("|" + board[i] + "|" + board[i+1] + "|" + board[i+2] + "|");
        }
        System.out.println("---------");
    }

    private static boolean checkWinner() {
        int[][] winPatterns = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == currentPlayer &&
                board[pattern[1]] == currentPlayer &&
                board[pattern[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDraw() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }
}
