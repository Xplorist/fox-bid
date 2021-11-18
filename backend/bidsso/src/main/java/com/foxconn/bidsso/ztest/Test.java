package com.foxconn.bidsso.ztest;

public class Test {
    private int a;
    private int b;

    public Test() {}

    public Test(int a, int b) {
        this.a = a;
        this.b =b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void test2(Test test) {
        test.setA(2);
        test.setB(3);
    }

    public static void main(String[] args) {
        //test1();
        Test test = new Test(1, 1);
        test.test2(test);
        System.out.println("test.a=" + test.getA());
        System.out.println("test.b=" + test.getB());
    }

    // 測試網址
    public static void test1() {
        String str = "";
        String[] strArr = str.split("/");
        System.out.println(strArr.length);
    }

}
