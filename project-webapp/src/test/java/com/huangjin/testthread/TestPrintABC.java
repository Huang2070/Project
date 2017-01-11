package com.huangjin.testthread;

/**
 * Created by huang on 2016-12-29.
 */

public class TestPrintABC {
    public static void main(String[] args) {
        PrintABC printABC = new PrintABC("A");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 100;i++) {
                        printABC.printA();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 100;i++) {
                        printABC.printB();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i < 100;i++) {
                        printABC.printC();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}



class PrintABC {
    private String flag;

    public PrintABC(String flag) {
        this.flag = flag;
    }

    public synchronized void printA() throws Exception {
        if(flag == "A") {
            System.out.print("A");
            flag = "B";
            notifyAll();
        } else {
            wait();
        }
    }

    public synchronized void printB() throws Exception {
        if(flag == "B") {
            System.out.print("B");
            flag = "C";
            notifyAll();
        } else {
            wait();
        }
    }

    public synchronized void printC() throws Exception {
        if(flag == "C") {
            System.out.print("C");
            flag = "A";
            notifyAll();
        } else {
            wait();
        }
    }
}


