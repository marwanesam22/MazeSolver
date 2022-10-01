package com.company;

public class MyStack {

        int size = 0 ;
        node top = null ;
        class node {
            int i ;
            int j ;
            node next ;
        }
        //Start of methods implementation
        public void pop(){
            Object element ;
            if(size ==0){
                System.out.println("Error");
                System.exit(0);
            }
            this.top = this.top.next ;
            this.size-- ;
        }


        public void push(int i , int j){
            node added = new node();
            added.i = i ;
            added.j = j ;
            if(size==0){
                this.top = added;
                added.next = null ;
            }
            else {
                added.next = this.top;
                this.top = added;
            }
            this.size++ ;
        }
    public void print(){
        MyStack s = new MyStack();
        node pp = this.top ;
        while (pp != null){
            s.push(pp.i , pp.j);
            pp = pp.next;
        }
        if (this.size == 0){
            System.out.println("NOT FOUND");
        }
        else{
            node pr;
            pr = s.top ;
            while (pr != null){
                System.out.print("{" + pr.i + ", " + pr.j + "}");
                pr = pr.next ;
                if(pr != null){
                    System.out.print(", ");
                }
            }
        }
    }
}
