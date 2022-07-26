package ar.edu.unsl.mys.utils;

import java.util.Random;

public class CustomRandomizer implements Randomizer
{
    private static CustomRandomizer customRandomizer;
    private Random randomizerImp;
    
    private CustomRandomizer()
    {
        this.randomizerImp = new Random(System.currentTimeMillis());
    }

    public static CustomRandomizer getInstance()
    {
        if(CustomRandomizer.customRandomizer == null) CustomRandomizer.customRandomizer = new CustomRandomizer();
        return CustomRandomizer.customRandomizer;
    }

    @Override
    public double nextRandom() {
        // TODO Auto-generated method stub
        return this.randomizerImp.nextDouble();
    }
}