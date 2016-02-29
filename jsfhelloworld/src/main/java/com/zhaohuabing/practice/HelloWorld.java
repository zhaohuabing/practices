package com.zhaohuabing.practice;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorld", eager = true)
public class HelloWorld {
   public HelloWorld() {
      System.out.println("HelloWorld started!");
   }
   public String getMessage() {
      return "Hello World!";
   }

   public static void main(String[] args){
      System.out.println("Hello World");
   }
}