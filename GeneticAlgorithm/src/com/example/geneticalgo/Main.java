/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.geneticalgo;

import com.example.geneticalgo.utils.*;
import java.util.ArrayList;

/**
 *
 * @author Itay
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int elementSize = 60;
       int populationSize = 1000;
       double targetVal =2.718281828459045;
       double accuFactor = 0.00000000000001;
       double mutationFactor = 0.3;
       int generationAmount = 20000;

       GeneticPopulation population = new GeneticPopulation(elementSize, targetVal, accuFactor, mutationFactor, populationSize, generationAmount);
       GeneticElement result = population.evolve();
       ArrayList<Sign> signsRes = result.getSigns();
       ArrayList<Integer> numRes = result.getNumbers();

       StringBuffer sb = new StringBuffer("Best element output = " + result.getOutputValue() + "\n");
       sb.append("Best element string: ");
       for(int i = 0; i < elementSize; ++i){
           sb.append(Sign.toString(signsRes.get(i)) + " " + numRes.get(i) + " ");
       }
       sb.append("\n");

       System.out.println(sb.toString());

    }

}
