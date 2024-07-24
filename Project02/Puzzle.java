/*
Zhenhao Zhang zzh133@u.rochester.edu 32277234 Project 2
*/

import java.util.Scanner;
public class Puzzle {
    int[][] Puzzle_Array;
    int p, q;
    int Step;
    public Puzzle(int[][] board) {
        Step=0;
        Puzzle_Array = new int[4][4];
        p = q = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Puzzle_Array[i][j] = board[i][j];
                if (Puzzle_Array[i][j] == 0) {
                    p = i;
                    q = j;
                }
            }
        }
    }
    public boolean validMove(char m) {
        if (m == 'U' || m == 'u') {
            if (p == 0) {
                // upper border
                return false;
            }
        } else if (m == 'D' || m == 'd') {
            if (p + 1 > 3) {
                // lower border
                return false;
            }
        } else if (m == 'L' || m == 'l') {
            if (q == 0) {
                // left border
                return false;
            }
        } else if (m == 'R' || m == 'r') {
            if (q + 1 > 3) {
                // right border
                return false;
            }
        }
        return true;
    }
    public void doMove(char m) {
        if (validMove(m)) {
            int temp = 0;
            int original = 0;
            Step++;
            if (m == 'U' || m == 'u') {
                // move up
                // save the original value
                temp = Puzzle_Array[p][q];
                original = Puzzle_Array[p - 1][q];
                // exchange
                Puzzle_Array[p][q] = original;
                Puzzle_Array[p - 1][q] = temp;
                // update the blank tile position
                p = p - 1;
            } else if (m == 'D' || m == 'd') {
                // move down
                temp = Puzzle_Array[p][q];
                original = Puzzle_Array[p + 1][q];
                Puzzle_Array[p][q] = original;
                Puzzle_Array[p + 1][q] = temp;
                p = p + 1;
            } else if (m == 'L' || m == 'l') {
                // move left
                temp = Puzzle_Array[p][q];
                original = Puzzle_Array[p][q - 1];
                Puzzle_Array[p][q] = original;
                Puzzle_Array[p][q - 1] = temp;
                q = q - 1;
            } else if (m == 'R' || m == 'r') {
                // move right
                temp = Puzzle_Array[p][q];
                original = Puzzle_Array[p][q + 1];
                Puzzle_Array[p][q] = original;
                Puzzle_Array[p][q + 1] = temp;
                q = q + 1;
            }
        }
    }
    public boolean checkSolution(char[] moves) {
        Puzzle Puzzle_Backup = copy();
        for(int i = 0;i<moves.length;i++){
            if(Puzzle_Backup.validMove(moves[i])){
                Puzzle_Backup.doMove(moves[i]);
            }
            else{
                return false;
            }
        }
        return Puzzle_Backup.solved();
    }

    public boolean solved() {
        if (Puzzle_Array[0][0] == 0) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (Puzzle_Array[i][j] != count) {
                        return false;
                    }
                    count++;
                }
            }
            return true;
        }
        else if (Puzzle_Array[3][3] == 0) {
            int count = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == 3 && j == 3) {
                        return true;
                    } else if (Puzzle_Array[i][j] != count + 1) {
                        return false;
                    }
                    count++;
                }
            }
        }
        return false;
    }
    public Puzzle copy(){
        Puzzle temp = new Puzzle(Puzzle_Array);
        return temp;
    }
    public void display() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%4d", Puzzle_Array[i][j]);
            }
            System.out.println();
        }
    }
    public int getSteps(){
        return Step;
    }

    public static void main(String[]args) {
        int[][] board = new int[4][4];
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the board: ");
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = s.nextInt();
            }
        }
        System.out.println();
        Puzzle p = new Puzzle(board);
        System.out.println("Enter a move sequence: ");
        String move = s.next();
        char[] m = move.toCharArray();
        boolean solved = p.checkSolution(m);
        int NumberofSteps = p.getSteps();
        if (solved) {
            System.out.println("success");
            for (int i = 0; i < m.length; i++) {
                if (p.validMove(m[i])) {
                    p.doMove(m[i]);
                }
            }
            p.display();
        } else {
            for (int i = 0; i < m.length; i++) {
                if (p.validMove(m[i])) {
                    p.doMove(m[i]);
                } else {
                    break;

                }
            }
            NumberofSteps = p.getSteps();
            if(NumberofSteps==m.length){
                System.out.println("Failure");
            }
            else{
                System.out.println("Invalid");
                System.out.println("The number of steps: " + NumberofSteps);
                System.out.println();

            }
            p.display();
        }
    }
}
