package ar.edu.unsl.mys.events;

import java.util.List;
import ar.edu.unsl.mys.entities.Entity;
//import ar.edu.unsl.mys.resources.Queue;
import ar.edu.unsl.mys.resources.Server;
import ar.edu.unsl.mys.engine.FutureEventList;
import ar.edu.unsl.mys.App;
import ar.edu.unsl.mys.behaviors.EndOfServiceEventBehavior;

public class EndOfServiceEvent extends Event
{
    public EndOfServiceEvent(int clock, Entity entity)
    {
        super(clock,entity,EndOfServiceEventBehavior.getInstance());
        this.getEntity().setEvent(this);
        
    }

    @Override
    public String toString()
    {
        return String.format("Type: End of Service -- Clock: %"+Event.END_TIME_DIGITS+"s -- entity: %s", this.getClock(), this.getEntity().toString());
    }

    @Override
    public void planificate(List<Server> servers, FutureEventList fel) {
        // TODO Auto-generated method stub
        Server server = this.getEntity().getAttendingServer();
    
        if(!(server.getWQueue().isEmpty()))
        {
            Entity entity;
            entity = server.getWQueue().next();
            if(!(this.getClock() > App.getExecutionTime())) entity.setWaitingTime(this.getClock() - entity.getArrivalEvent().getClock()); //Wtime
            fel.insert(this.getEventBehavior().nextEvent(this, entity)); //Próximo fin de servicio
        }
        else
        {
            server.setBusy(false);
            server.setIdleTimeStartMark(this.getClock());
        }

        this.getEntity().setTransitTime(this.getEntity().getEndOfServiceEvent().getClock() - this.getEntity().getArrivalEvent().getClock());
    }
}