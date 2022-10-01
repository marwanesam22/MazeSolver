package com.company;

public class Queue {

    int lenth = 0;
    myNode front = null , rear = null;

    static class myNode{
        int i;
        int j;
        myNode parent;
        myNode next;
        myNode prev;
    }
    //---------------------------------------------------------------------------

    public void enqueue (int i , int j , myNode parent){

        myNode input = new myNode();
        input.i = i;
        input.j = j;
        input.parent = parent ;

        if (this.lenth == 0){
            this.front = this.rear = input;
            input.next = input.prev = null;
        }
        else {
            input.prev = this.rear;
            this.rear.next = input;
            this.rear = input;
            input.next = null;
        }
        this.lenth++;
    }

    //---------------------------------------------------------------------------

    public boolean isEmpty(){
        return this.lenth == 0 || this.front == null;
    }

    public int dequeue (){

        if(this.isEmpty()){
            System.out.println("Error");
            return 0;
        }

        else if(lenth == 1){
            myNode tbr = this.front;
            this.front = this.rear = null;
            return 0;
        }

        myNode f = this.front;
        front = front.next;
        front.prev = null;

        myNode cur = this.front;
        myNode del = this.front;
        this.lenth--;
        return f.i;

    }
    public void print(){
        myNode cur;
        cur = this.rear;

        while (cur != null){
            System.out.print("{" + cur.i + ", " + cur.j +"}");
            if(cur.prev != null)
                System.out.print(", ");
            cur = cur.prev ;
        }
    }
}
