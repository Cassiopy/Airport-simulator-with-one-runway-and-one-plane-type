package ar.edu.unsl.mys.engine;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unsl.mys.resources.Airstrip;
//import ar.edu.unsl.mys.resources.CustomQueue;
//import java.util.Scanner;
//import java.util.Iterator;
import java.io.FileWriter;
//import java.util.ArrayList;
import java.io.BufferedWriter;

import ar.edu.unsl.mys.App;
import ar.edu.unsl.mys.entities.Entity;
import ar.edu.unsl.mys.resources.Server;
//import ar.edu.unsl.mys.entities.Entity;
import ar.edu.unsl.mys.events.ArrivalEvent;
import ar.edu.unsl.mys.events.StopExecutionEvent;
//import ar.edu.unsl.mys.resources.Airstrip;
//import ar.edu.unsl.mys.events.ArrivalEvent;
//import ar.edu.unsl.mys.resources.CustomQueue;
//import ar.edu.unsl.mys.events.StopExecutionEvent;
import ar.edu.unsl.mys.policies.ServerSelectionPolicy;

/**
 * Event oriented simulation of an airport
 */
public class AirportSimulation implements Engine
{
    private String report = "==============================================================================================\n"+
                            "                                        R E P O R T                                           \n"+
                            "==============================================================================================\n"+
                            "\n";

    private int endTime;
    private boolean stopSimulation;
    private FutureEventList fel;
    private List<Server> servers;

    /**
     * Creates the execution engine for the airport simulator.
     * @param airstripQuantity The number of airstrips (servers).
     * @param endTime The amount of time the simulator will simulate (run length).
     * @param policy The object that defines the airstrip selection policy 
     * each time an arrival occurs.
     */
    public AirportSimulation(int airstripQuantity, int endTime, ServerSelectionPolicy policy)
    {
        this.fel = new FutureEventList();
        this.servers = new LinkedList<Server>();
        this.endTime = endTime;
        this.stopSimulation = false;
        for(int i = 0; i < airstripQuantity; i++)
        {
            this.servers.add(new Airstrip());
        }
        this.fel.insert(new StopExecutionEvent(endTime, this));
        this.fel.insert(new ArrivalEvent(0, policy));
    }

    public int getEndTime()
    {
        return this.endTime;
    }

    public void setStopSimulation(boolean stopSimulation)
    {
        this.stopSimulation = stopSimulation;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

        //Incialización de varianbles
        while(!this.stopSimulation)
        {
            this.fel.getImminent().planificate(this.servers, this.fel);
        }
        
        System.out.println("\nSimulation completed successfully\n");
    }

    @Override
    public String generateReport(boolean intoFile, String fileName) {
        // TODO Auto-generated method stub

        this.report += "Aircraft that arrived = " + Entity.getIdCount() + "\n" + "Total waiting time in queue = " + Entity.getTotalWaitingTime() + "\n" + "Average waiting time in queue = " + (float)Entity.getTotalWaitingTime()/(float)Entity.getIdCount() + "\n" + "Max waiting time in queue = " + Entity.getMaxWaitingTime() + "\n" + "Max waiting queue lengh = " + servers.get(0).getWQueue().getMaxSize() + "\n" + "Average airstrip idle time = "+ (float)servers.get(0).getIdleTime()/(float)servers.get(0).getIdleCount() + "\n" + "Total airstrip idle time = " + servers.get(0).getIdleTime() + " (" + (float)(servers.get(0).getIdleTime()*100)/(float)App.getExecutionTime() + "% of 100%)\n" + "Max airstrip idle time = " + servers.get(0).getMaxIdleTime() + "\n" +"Average transit in system time = " + (float)Entity.getAccumulatedTransitTime()/(float)Entity.getIdCount() + "\n" + "Max transit in system time = " + Entity.getMaxTransitTime() + "\n==============================================================================================\n";
 
        if(intoFile)
        {
            try
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(this.report);
                writer.close();
            }
            catch (Exception exception)
            {
                System.out.println("Error when trying to write the report into a file.");
                System.out.println("Showing on screen...");
                System.out.println(this.report);
            }
        }

        System.out.println(this.report);
        return this.report;
    }
    
}