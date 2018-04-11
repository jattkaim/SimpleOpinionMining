package com.jattkaim;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
/***************************************

 * This is a Controller class and contains all methods related to the mining of the sentence

 * bugs to fix: (recognize full stop at the end of the sentence)

 * to do: commenting needed, and more...

 ***************************************/
public class MiningHelper {

    List<Dict> adjectiveList = DictWords.wordsList;
    private List<List<String>> clauses = new ArrayList<List<String>>();
    List<Extracted> extractedList = new ArrayList<Extracted>();

    /*ignore method name for this for now, will change*/
    public void findAdverbs(String[][] c, String[] adverbs){
        List<String[]> sentences = new ArrayList<String[]>();
        ArrayList<String> fConj = new ArrayList<String>();
        for (String [] b: c){
            sentences.add(b);
        }

        for (String sentence[]: sentences){
            for(String word:sentence){
                for(String a:adverbs){
                    if (word.equals(a)){
                        /*finding the conjunctions used in this sentence*/
                        //running the method to break sentence into before and after conjunction
                        fConj.add(a);

                        //breaking(sentence,a);
                    }
                }
            }

            breaking(sentence, fConj);
        }

        finding(clauses);

    }

    /*Breaking up sentence into two parts, before a a conjunction, after a conjunction*/
    public void breaking(String sentence[], ArrayList<String> conjunctions){




        List <String> tempClause = new ArrayList<String>();


        for (String word: sentence){
            for( String c: conjunctions){
                if (word.equals(c)){
                    if(tempClause.size()>0){
                        clauses.add(tempClause);
                    }
                    tempClause = new ArrayList<>();

                }


            }

            tempClause.add(word);


        }
        if(tempClause.size()>0){
            clauses.add(tempClause);

        }

        /* TO VIEW THE BROKEN UP SENTENCES
         //[This, doctor, was, not, very, good]
         //[and, took, too, long]
         //[so, im, giving, him, two, stars]
         //[this, is, me, breaking, up, the, sentences]
        System.out.println("----");
        for(List<String> c:clauses){
            System.out.println("");

            System.out.print(" "+c);

        }
        System.out.println("");
        System.out.println("----");
        */

        if(clauses.size()<1){
            clauses.add(tempClause);
        }

        //return clauses;
    }

    public  void finding(List<List<String>> clausesList) {

        String realAds[] = {"very"};
        String features[] = {"time", "communication"};
        String comFeatures[] = {"tell","inform", "communication", "listen", "friendly", "empathetic", "informative", "considerate"};
        String listening[] = {"",""};
        String conj[] = {"and","but","so", "although"};
        Boolean neg = false;
        Boolean found = false;
        System.out.println("");
        System.out.println("LIST OF SEPERATED CLAUSES DISPLAYED BELOW");
        for (List<String> cl : clausesList) {
            System.out.println(cl);
        }


        for (List<String> clause : clausesList) {
            Extracted extracted = new Extracted(new ArrayList<Dict>(),"",false);
            System.out.println("");
            System.out.println("-------------------------------------------");
            System.out.println("Searching clause: " +clause);
            neg=false;
            found = false;
            for (String con : conj) {
                if (clause.get(0).equals(con)) {
                    System.out.println("there is a conjunction in this clause: " + con);
                    extracted.setConjunction(con);
                }
            }



            List<Dict> foundList = new ArrayList<Dict>();

            for (String word : clause) {

                        for (Dict adj: adjectiveList) {
                         //   long startTime = System.nanoTime();
                            if (word.equals(adj.getWord())) {
                                System.out.println("FOUND Adjective: " + adj.getWord());
                                foundList.add(adj);
                                found =true;

                            }
                            //long stopTime = System.nanoTime();
                            //System.out.println("time taken to compare the two words: "+ (stopTime - startTime));
                        }
                if(word.equals("not") || word.equals("never") ||word.equals("doesn't") ||word.equals("wont") ){
                            neg=true;
                            extracted.setNot(neg);

                }

            }
            extracted.setAdjFound(foundList);
            if (neg){
                System.out.println("This Clause contains a NOT, so negative of adjective is taken");
            }
            if (!found){
                System.out.println("NO adjectives in this clause");
            }
            if(extracted!=null){
                extractedList.add(extracted);
            }

        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("******************************************************************************************");
        System.out.println("******************************************************************************************");
        System.out.println("");
        System.out.println("EXTRACTED ITEM LIST FOR MATHS USE\nthis part of the program will show the adjectives, there respective scores, polarity and give information\nabout how the ranking system will create a final score for the review");
        System.out.println("");
        System.out.println("******************************************************************************************");
        System.out.println("******************************************************************************************");
        System.out.println("");
        for (Extracted e: extractedList){
            System.out.println("clause : "+extractedList.indexOf(e));
            System.out.println("Adjectives and there information: ");
            for (Dict d :e.getAdjFound()){
                System.out.println("\tAdjective: "+d.getWord()+", Score: "+d.getScore()+", Polarity: "+d.getPol());
                for(String ft: comFeatures){
                    if(d.getWord().equals(ft)){
                        System.out.print("\t Also A Communication Feature ");
                        System.out.println("");
                    }
                }

            }
            if(e.getAdjFound().size()<0){
                System.out.println("\tAdjective: NONE ");
            }
            System.out.println("Clause information: ");
            if(e.getConjunction()!=""){
                System.out.print("\tConjunction: "+e.getConjunction()+" , negation: "+e.isNot());
            }else{
                System.out.print("\tConjunction: NONE "+" , negation: "+e.isNot());
            }

            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("");
        }

    System.out.println("RATING FOR THIS REVIEW :" +maths(extractedList));
    }

    public double maths (List<Extracted> eList){
        double quickrating =0 ;
        double comrating =0;
        int cntr = 0;
        int cmCntr = 0;
        String comFeatures[] = {"tell","inform", "communication", "listen", "friendly", "empathetic", "informative", "considerate"};
        for (Extracted e:eList){

            for (Dict d: e.getAdjFound()){

                cntr++;
                double tempScore= d.getScore();

                if(e.getConjunction().equals("and")){

                }if(e.getConjunction().equals("but")){
                    if(tempScore<5){
                        tempScore = tempScore*0.5;
                    }
                    if(tempScore>5){
                        tempScore = tempScore*1.5;
                    }

                }if(e.getConjunction().equals("so")){

                }if(e.isNot()){
                    tempScore = tempScore/2;
                }
                for(String c:comFeatures){
                    if(d.getWord().equals(c)){
                        cmCntr++;
                        comrating+=d.getScore();
                    }

                }

                    quickrating +=tempScore;

            }
        }
        comrating = comrating/cmCntr;
        quickrating = quickrating/cntr;

        System.out.println("communication skills rating: "+comrating);

        return quickrating;
    }


}
