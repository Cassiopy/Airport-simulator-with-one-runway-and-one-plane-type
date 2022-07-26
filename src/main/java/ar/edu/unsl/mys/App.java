
//Sofía Flores - Ingeniería en Informática

package ar.edu.unsl.mys;

import ar.edu.unsl.mys.engine.AirportSimulation;
import ar.edu.unsl.mys.engine.Engine;
import ar.edu.unsl.mys.policies.OneServerModelPolicy;

public class App 
{
    private static final int MIN_PER_DAY = 1440;
    private static final int NUMBER_OF_DAYS = 28;
    private static final int EXECUTION_TIME = MIN_PER_DAY*NUMBER_OF_DAYS;

    private static int SERVERS_NUMBER = 1;

    public static int getExecutionTime()
    {
        return EXECUTION_TIME;
    }

    public static void main( String[] args )
    {
        Engine engine = new AirportSimulation(SERVERS_NUMBER, EXECUTION_TIME, OneServerModelPolicy.getInstance());
        engine.execute();
        engine.generateReport(true,"report.txt");
    }


}