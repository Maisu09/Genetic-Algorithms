package org.example.CoinsProblem;

import org.jgap.*;

public class SampleFitnessFunction extends FitnessFunction {

    private final double targetAmount;

    public SampleFitnessFunction(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    @Override
    public double evaluate(IChromosome chromosome) {
        double changeAmount = amountOfChange(chromosome);
        int totalCoins = getTotalNumberOfCoins(chromosome);

        double changeDifference = Math.abs(targetAmount - changeAmount);
        double fitness = 300 * changeDifference * changeDifference;
        fitness += totalCoins > 1 ? totalCoins : 0;
        return fitness;
    }

    private static int getTotalNumberOfCoins(IChromosome chromosome) {
        int totalCoins = 0;
        for (int i = 0; i < chromosome.size(); i++) {
            totalCoins += getNrCoinsAtGene(chromosome, i);
        }
        return totalCoins;
    }

    public static double amountOfChange(IChromosome chromosome) {
        int numberOfQuorters = getNrCoinsAtGene(chromosome, 0);
        int numberOfDimes = getNrCoinsAtGene(chromosome, 1);
        int numberOfNickels = getNrCoinsAtGene(chromosome, 2);
        int numberOfPennies = getNrCoinsAtGene(chromosome, 3);

        return (numberOfQuorters * 0.25) + (numberOfDimes * 0.1) + (numberOfNickels * 0.05) + (numberOfPennies * 0.01);
    }

    public static int getNrCoinsAtGene(IChromosome chromosome, int positions) {
        Integer numCoins = (Integer) chromosome.getGene(positions).getAllele();
        return numCoins;
    }

}
