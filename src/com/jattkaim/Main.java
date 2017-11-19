package com.jattkaim;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class Main {



    static String[][] c;
    public static void main(String[] args) {
        MiningHelper mine = new MiningHelper();

        Scanner sc = new Scanner(System.in);

         System.out.println("Please Enter a Review for your doctor: ");
        String review = sc.nextLine();
      //String review = sc.next();

      String ads[] = {"and","but","so", "although"};
      /*String review = "Dr. Neha was excellent as usual but the overall experience was good. " +
              "She has been our doctor, for both my wife and myself for years and always " +
              "listens to what we have to say. She has always been friendly, competent, " +
              "and helpful.";*/
      int reviewRating = 3;
      String a[]=review.split("(?<!Dr)(?<!DR)[.] | (?<!\\.[a-zA-Z]) ");
      System.out.println("Original review Sentence: "+review);
      System.out.println("");
      System.out.println("Breaking up the sentences: ");

      /*Displaying the two sentences*/

      for (String b:a){
          System.out.println("  "+b);
      }
        /*setting 2D array with [Sentence] [array of words in that sentence]*/
        c= new String[a.length][];
        for (int i=0;i<a.length;i++){
          String temp[]=a[i].split(" |, ");

            for(int j=0;j<temp.length;j++){
                c[i]=temp;
            }
      }

        /*Print statements for all words in each sentence*/
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("");
      for (int i =0;i<c.length;i++){

          System.out.print("the "+c[i].length+" words in sentence "+i+" are: ");
          for (int j=00; j<c[i].length;j++){
              System.out.print(","+c[i][j]);

          }
          System.out.println("");
          System.out.println("");
      }


        /*RUN METHOD TO FIND ADVERBS/CONJUNCTIONS*/
      mine.findAdverbs(c,ads);

    }





}
