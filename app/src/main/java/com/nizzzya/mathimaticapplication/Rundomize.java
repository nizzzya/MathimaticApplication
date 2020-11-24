package com.nizzzya.mathimaticapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Rundomize {
    private int a;
    private int b;
    private int corectAnswer;


    private int maxNum;
    private int numGeneration;

    private List<Integer> resultList;

    public List<Integer> getResultList() {
        return resultList;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getCorectAnswer() {
        return corectAnswer;
    }

    public Rundomize(int maxNum, int numGeneration) {
        this.maxNum = maxNum;
        this.numGeneration = numGeneration;
        this.genetatorA();
        this.genetatorB();
        this.CorectAnswer(a, b);
        this.ListAnswer();
    }

    private int rundomNMum (int maxNum) {
        final Random random = new Random();
        int result = random.nextInt(maxNum/2)+1;
        return result;
    }

    private void genetatorA() {
        a = this.rundomNMum(this.maxNum/2)+1;
    }

    private void genetatorB() {
        b = this.rundomNMum(this.maxNum/2)+1;
    }

    private void CorectAnswer(int a, int b) {
        corectAnswer = a + b;
    }

    private int UncorectAnswer(int maxNum) {
        return new Random().nextInt(maxNum);
    }

    private void ListAnswer() {
        Set<Integer> possibleAnswer = new HashSet<>();

        possibleAnswer.add(corectAnswer);

        while (possibleAnswer.size() < numGeneration) {
            possibleAnswer.add(this.UncorectAnswer(this.maxNum));
        }

        resultList = new ArrayList<Integer>(possibleAnswer);
        Collections.shuffle(resultList, new Random(System.nanoTime()));

    }

}
