package org.example.CoinsProblem;

//import org.jgap.Configuration;
//import org.jgap.InvalidConfigurationException;
//import org.jgap.impl.DefaultConfiguration;

import org.jgap.*;
import org.jgap.impl.*;


public class ConstraintExercise {
    private static final int NR_EVOLUTIONS = 100;
    public static void main(String[] args) throws InvalidConfigurationException {
        double targetAmount = 1.84;

        Configuration configuration = new DefaultConfiguration();
        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);

        configuration.setFitnessEvaluator(new DeltaFitnessEvaluator());
        configuration.setPreservFittestIndividual(true);
        configuration.setKeepPopulationSizeConstant(true);

        FitnessFunction fitnessFunction = new SampleFitnessFunction(targetAmount);
        configuration.setFitnessFunction(fitnessFunction);

        Gene[] sampleGenes = new Gene[4];
        sampleGenes[0] = new IntegerGene(configuration, 0, 20);
        sampleGenes[1] = new IntegerGene(configuration, 0, 30);
        sampleGenes[2] = new IntegerGene(configuration, 0, 50);
        sampleGenes[3] = new IntegerGene(configuration, 0, 80);

        IChromosome sampleChromosome = new Chromosome(configuration, sampleGenes);

        configuration.setSampleChromosome(sampleChromosome);

        configuration.setPopulationSize(200);
        Genotype population = Genotype.randomInitialGenotype(configuration);

        for (int i = 0; i < NR_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            DisplayIndividual(bestSolutionSoFar);
        }


    }

    public static void DisplayIndividual(IChromosome chromosome) {
        System.out.print("Fitness value: " + chromosome.getFitnessValue());
        System.out.print(", Coins: ");

        for (int i = 0; i < 4; i++) {
            System.out.print(SampleFitnessFunction.getNrCoinsAtGene(chromosome, i) + " ");
        }
        System.out.println(", total change: " + SampleFitnessFunction.amountOfChange(chromosome));

    }
}