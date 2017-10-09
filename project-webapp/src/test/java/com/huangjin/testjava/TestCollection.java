package com.huangjin.testjava;

/**
 * Created by huang on 2016-8-25.
 */
public class TestCollection {

    public static void main(String[] args) throws Exception {
        final AAA aaa = new AAA(1,2);
        aaa.setI(9);
        aaa.setJ(10);

        System.out.println(aaa.getI());
        System.out.println(aaa.getJ());
    }

}

class AAA {
    private int i;
    private int j;

    public AAA(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
