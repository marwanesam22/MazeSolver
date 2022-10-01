package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
interface IMazeSolver {
    /**
     * Read the maze file, and solve it using Breadth First Search
     * @param maze maze file
     * @return the coordinates of the found path from point ’S’
     *
    to point ’E’ inclusive, or null if no path is found.
     */
    public int[][] solveBFS(java.io.File maze) throws FileNotFoundException ;
    /**
     * Read the maze file, and solve it using Depth First Search
     */
    public void solveDFS (char [][] map , int i , int j);
}

public class Main implements IMazeSolver {

    static Queue Q = new Queue() ;
    static MyStack stack = new MyStack() ;
    static int Ei = -1, Ej= -1 , N , M , Si , Sj;

    static char[][] readFile(File maze) throws FileNotFoundException {
        Scanner sc = new Scanner(maze);
        char[] r = new char[3];
        char[] c = new char[3];
        int r_i = 0, c_i = 0;
        String dim = sc.nextLine();
        char[] d = dim.toCharArray();
        boolean swap = false;
        for(char s: d){
            if(s == ' '){
                swap = !swap;
            }else if(!swap){
                r[r_i++] = s;
            }else{
                c[c_i++] = s;
            }
        }
        N = Integer.parseInt(String.valueOf(r).trim());
        M = Integer.parseInt(String.valueOf(c).trim());

        String[] m = new String[N];
        for(int i = 0; sc.hasNext(); i++)
            m[i] = sc.nextLine();


        char[][] map = new char[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = m[i].toCharArray();
        }
        return map;
    }

    public int[][] solveBFS(java.io.File maze) throws FileNotFoundException {
        int i = Si , j= Sj ;
        char [][] map = readFile(maze);
        if(Ei == -1 && Ej == -1){
            System.out.println("NOT FOUND");
            System.exit(0);
        }
        Q.enqueue(i , j , null);
        map[i][j] = '*' ;
        Queue.myNode temp = new Queue.myNode();
        temp = Q.front;

        while (!Q.isEmpty()){
            if(temp.i == Ei && temp.j == Ej){
                Queue PQ = new Queue();
                while (temp != null){
                    PQ.enqueue(temp.i, temp.j, null);
                    temp = temp.parent ;
                }
                PQ.print();
                break;
            }
            //check right
            if(j+1 < map[0].length){
                if(map[i][j+1] == '.' || map[i][j+1] == 'E'){
                    map[i][j+1] = '*';
                    Q.enqueue(i, j+1 , temp);
                }
            }
            //check left
            if(j-1 >= 0){
                if(map[i][j-1] == '.' || map[i][j-1] == 'E'){
                    map[i][j-1] = '*';
                    Q.enqueue(i, j-1 , temp);
                }
            }
            //check up
            if(i-1 >= 0){
                if(map[i-1][j] == '.' || map[i-1][j] == 'E'){
                    map[i-1][j] = '*';
                    Q.enqueue(i-1, j ,temp);
                }
            }
            //check down
            if(i+1 < map.length){
                if(map[i+1][j] == '.' || map[i+1][j] == 'E'){
                    map[i+1][j] = '*';
                    Q.enqueue(i+1, j, temp);
                }
            }
            Q.dequeue();
            temp = Q.front ;
            i= temp.i; j= temp.j ;
        }
        return null ;
    }

    public void solveDFS (char [][] map , int i , int j){
        if(i < 0 || j > map[0].length-1 || i > map.length-1 || j < 0){ // check size
            return;
        }
        else if(map[i][j] == '#' || map[i][j] == '*'){ // visited
            return;
        }
        map[i][j] = '*' ; //make it visited
        stack.push(i , j);
        if (i == Ei && j == Ej){
            stack.print();
            System.exit(0);
        }

        solveDFS(map ,i-1 ,j); //up
        solveDFS(map ,i+1 ,j); //down
        solveDFS(map ,i ,j+1); //right
        solveDFS(map ,i ,j-1); //left
        stack.pop(); //pop when it was a dead end
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        filePath = filePath.replaceAll("\"", "");
        File file = new File(filePath);
        char [][] map = readFile(file) ;
        Main obj = new Main();



        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j<M ; j++){
                if(map[i][j] == 'E'){
                    Ei = i;
                    Ej = j ;
                }
            }
        }
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j<M ; j++){
                if(map[i][j] == 'S'){
                    Si= i; Sj=j;
                    System.out.println("BFS:");
                    obj.solveBFS(file);

                    System.out.println("\nDFS:");
                    obj.solveDFS(map , i , j);
                }
            }
        }
    }
}
