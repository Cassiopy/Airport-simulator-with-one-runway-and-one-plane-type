package ar.edu.unsl.mys.entities;

import java.util.List;
//import java.util.Comparator;
import java.util.LinkedList;

import ar.edu.unsl.mys.events.Event;
import ar.edu.unsl.mys.resources.Server;
import ar.edu.unsl.mys.events.ArrivalEvent;
import ar.edu.unsl.mys.events.EndOfServiceEvent;

public abstract class Entity
{
    private static int idCount = 0;
    private static int totalWaitingTime;
    private static int maxWaitingTime;
    private static int totalTransitTime;
    private static int maxTransitTime;

    //attributes
    private int id;
    private int waitingTime;
    private int transitTime;

    //associations
    private Server attendingServer;
    private List<Event> events; //En 0 guardo el de evento de llegada y en 1 el evento de salida
    
    // others
    /**
     * Used if it is necessary to order chronologically the events of this entity.
     */
    //private Comparator<Event> comparator;

    public Entity() //Como creo una entidad cada vez que hay un arribo, solo necesito el id ya que el tiempo de espera y de tránsito de esta entidad aún no lo tengo
    {
        idCount++;
        this.id = idCount;
        this.events = new LinkedList<Event>();
    }

    public static int getTotalWaitingTime()
    {
        return totalWaitingTime;
    }

    public static int getIdCount()
    {
        return idCount;
    }

    public static int getMaxWaitingTime()
    {
        return maxWaitingTime;
    }

    public static int getAccumulatedTransitTime()
    {
        return totalTransitTime;
    }

    public static int getMaxTransitTime()
    {
        return maxTransitTime;
    }

    public int getId()
    {
        return this.id;
    }

    public int getWaitingTime()
    {
        return this.waitingTime;
    }

    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
        if(waitingTime > maxWaitingTime) maxWaitingTime = waitingTime;
        totalWaitingTime += waitingTime;
    }

    public int getTransitTime()
    {
        return this.transitTime;
    }

    public void setTransitTime(int transitTime)
    {
        this.transitTime = transitTime;
        if(transitTime > maxTransitTime) maxTransitTime = transitTime;
        totalTransitTime += transitTime;
    }

    public ArrivalEvent getArrivalEvent()
    {
        return (ArrivalEvent)this.events.get(0);
    }

    public EndOfServiceEvent getEndOfServiceEvent()
    {
        return (EndOfServiceEvent)events.get(1);
    }

    public Server getAttendingServer()
    {
        return this.attendingServer;
    }

    public void setAttendingServer(Server attendingServer)
    {
        this.attendingServer = attendingServer;
    }

    public List<Event> getEvents()
    {
        return events;
    }

    public void setEvent(Event event)
    {
        if(event instanceof ArrivalEvent) events.add(0, event); //Evento de llegada
        else events.add(1, event); //Evento de salida
    }
}