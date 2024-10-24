package org.example.KnapsackProblem;

import org.example.CoinsProblem.SampleFitnessFunction;
import org.jgap.*;
import org.jgap.impl.*;

public class KnapsackExercise {
    public static final int NUMBER_EVOLUTIONS = 100;
    public static final int NUMBER_OBJECTS = 10;
    public static final int POPULATION_SIZE = 100;

    public static void main(String[] args) throws InvalidConfigurationException {
        int knapsackVolume = 30;

        Configuration configuration = new DefaultConfiguration();
        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);

        configuration.setFitnessEvaluator(new DeltaFitnessEvaluator());
        configuration.setPreservFittestIndividual(true);
        configuration.setKeepPopulationSizeConstant(true);

        FitnessFunction fitnessFunction = new SampleFitnessFunction(knapsackVolume);
        configuration.setFitnessFunction(fitnessFunction);

        Gene[] genes = new Gene[NUMBER_OBJECTS];

        for (int i = 0; i < NUMBER_OBJECTS; i++) {
            genes[i] = new IntegerGene(configuration, 0, i);
        }

        IChromosome chromosome = new Chromosome(configuration, genes);

        configuration.setSampleChromosome(chromosome);

        configuration.setPopulationSize(POPULATION_SIZE);
        Genotype population = Genotype.randomInitialGenotype(configuration);

        for (int i = 0; i < NUMBER_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome bestSolutionSoFar = population.getFittestChromosome();
            CurrentSolution(bestSolutionSoFar);
        }

    }

    public static void CurrentSolution(IChromosome bestSolution) {
        System.out.print("Fitness value: " + bestSolution.getFitnessValue());
        System.out.print(" ");
    }
}