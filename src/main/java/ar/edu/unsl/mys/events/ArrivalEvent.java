package ar.edu.unsl.mys.events;

import java.util.List;
import ar.edu.unsl.mys.resources.Server;
import ar.edu.unsl.mys.entities.Aircraft;
//import ar.edu.unsl.mys.entities.Entity;
import ar.edu.unsl.mys.engine.FutureEventList;
import ar.edu.unsl.mys.policies.ServerSelectionPolicy;
import ar.edu.unsl.mys.App;
import ar.edu.unsl.mys.behaviors.ArrivalEventBehavior;
import ar.edu.unsl.mys.behaviors.EndOfServiceEventBehavior;
//import ar.edu.unsl.mys.resources.CustomQueue;

public class ArrivalEvent extends Event
{
    private ServerSelectionPolicy selectionPolicy;
    private EndOfServiceEventBehavior endOfServiceEventBehavior;

    public ArrivalEvent(int clock, ServerSelectionPolicy policy)
    {
       super(clock, new Aircraft(), ArrivalEventBehavior.getInstance());
       this.getEntity().setEvent(this);
       this.selectionPolicy = policy;
       this.endOfServiceEventBehavior = EndOfServiceEventBehavior.getInstance();
    }

    public ServerSelectionPolicy getSelectionPolicy()
    {
        return this.selectionPolicy;
    }

    public EndOfServiceEventBehavior getEndOfServiceEventBehavior()
    {
        return this.endOfServiceEventBehavior;
    }

    @Override
    public String toString()
    {
        return String.format("Type: Arrival        -- Clock: %"+Event.END_TIME_DIGITS+"s -- entity: %s", this.getClock(), this.getEntity().toString());
    }

    @Override
    public void planificate(List<Server> servers, FutureEventList fel) {
        // TODO Auto-generated method stub
        Server server = this.selectionPolicy.selectServer(servers);
        this.getEntity().setAttendingServer(server);
        
        if (server.isBusy()) 
            server.getWQueue().enqueue(this.getEntity());
        else
        {
            server.setBusy(true);
            if(!(this.getClock() > App.getExecutionTime())) 
            {
                server.setIdleCount();
                server.setIdleTimeFinishMark(this.getClock());
            }
            server.setServedEntity(this.getEntity());
            fel.insert(EndOfServiceEventBehavior.getInstance().nextEvent(this, this.getEntity()));
        }
        fel.insert(this.getEventBehavior().nextEvent(this, this.getEntity()));   
    }
}