package org.example.KnapsackProblem;

import org.jgap.*;
import org.jgap.impl.*;

public class SampleFitnessFunction extends FitnessFunction {
    private final int knapsackVolume;


    public SampleFitnessFunction(int knapsackVolume) {
        this.knapsackVolume = knapsackVolume;
    }

    @Override
    protected double evaluate(IChromosome chromosome) {
        return 0;
    }
}
