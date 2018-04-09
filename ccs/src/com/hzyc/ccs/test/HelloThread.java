package com.hzyc.ccs.test;

import java.util.Vector;

public class HelloThread implements Runnable {  
    String name;  
    Vector<String> v;  
  
    HelloThread(String name, Vector<String> v) {  
        this.name = name;  
        this.v = v;  
    }  
  
    public void run() {  
        System.out.println(name + "start");  
        while(true) {  
            v.add(name + ".add");  
            System.out.println(name + " vector element0 is " + v.get(0));  
            try {  
            	v.remove(0);
                Thread.sleep(2000);  
            } catch(InterruptedException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
    }  
  
    public static void main(String args[]) throws InterruptedException {  
  
        Vector<String> v1 = new Vector<String>();  
  
        HelloThread hello1 = new HelloThread("hello1", v1);  
        HelloThread hello2 = new HelloThread("hello2", v1);  
  
        Thread h1 = new Thread(hello1);  
        Thread h2 = new Thread(hello2);  
        h1.start();  
        h2.start();  
    }  
}  