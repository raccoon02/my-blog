package com.sparta.myblog;

public class Test {
    void cat(){
        System.out.println("매개변수 없음");
    }
    void cat (int a, int b){
        System.out.println("매개변수 : "+a+","+b);
    }
    void cat (String c){
        System.out.println("매개변수 : "+ c);
    }
}
class OverTest{
    public static void main(String[] args) {

        Test ct = new Test();

        ct.cat();

        ct.cat(70,50);

        ct.cat("안녕하세요");
    }
}
