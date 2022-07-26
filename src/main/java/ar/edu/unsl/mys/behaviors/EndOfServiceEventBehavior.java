package ar.edu.unsl.mys.behaviors;

import ar.edu.unsl.mys.events.Event;
//import ar.edu.unsl.mys.App;
import ar.edu.unsl.mys.entities.Entity;
//import ar.edu.unsl.mys.utils.Randomizer;
import ar.edu.unsl.mys.utils.CustomRandomizer;
import ar.edu.unsl.mys.events.EndOfServiceEvent;

public class EndOfServiceEventBehavior extends EventBehavior
{
    private static EndOfServiceEventBehavior endOfServiceEventBehavior;

    private EndOfServiceEventBehavior(CustomRandomizer randomizer)
    {
        super(randomizer);
    }

    public static EndOfServiceEventBehavior getInstance()
    {
        if(endOfServiceEventBehavior == null) endOfServiceEventBehavior = new EndOfServiceEventBehavior(CustomRandomizer.getInstance());
        return endOfServiceEventBehavior;
    }

    @Override
    public Event nextEvent(Event actualEvent, Entity entity) {
        // TODO Auto-generated method stub
        EndOfServiceEvent aux;
        double random = this.getRandomizer().nextRandom();
        int time;

        if (random <= 0.1) time = 8;
        else if(random > 0.1 && random <= 0.48) time = 10;
        else if(random > 0.48 && random <= 0.8) time = 15;
        else time = 20;
        aux = new EndOfServiceEvent(actualEvent.getClock() + time, entity);
        return aux;
    } 
}

