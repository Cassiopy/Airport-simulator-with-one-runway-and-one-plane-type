package ar.edu.unsl.mys.behaviors;

import ar.edu.unsl.mys.events.ArrivalEvent;
import ar.edu.unsl.mys.events.Event;
import ar.edu.unsl.mys.policies.OneServerModelPolicy;
import ar.edu.unsl.mys.entities.Entity;
//import ar.edu.unsl.mys.utils.Randomizer;
//import ar.edu.unsl.mys.events.ArrivalEvent;
import ar.edu.unsl.mys.utils.CustomRandomizer;

public class ArrivalEventBehavior extends EventBehavior
{
    private static ArrivalEventBehavior arrivalEventBehavior;

    private ArrivalEventBehavior(CustomRandomizer randomizer)
    {
        super(randomizer);
    }

    public static ArrivalEventBehavior getInstance()
    {
        if(arrivalEventBehavior == null) 
        {
            arrivalEventBehavior = new ArrivalEventBehavior(CustomRandomizer.getInstance());
        }
        return arrivalEventBehavior;
    }

    @Override
    public Event nextEvent(Event actualEvent, Entity entity) {
        // TODO Auto-generated method stub
        ArrivalEvent aux;
        double random = this.getRandomizer().nextRandom();
        int time;
        if (random <= 0.3) time = 10;
        else if(random > 0.3 && random <= 0.7) time = 15;
        else time = 20;
        aux = new ArrivalEvent(actualEvent.getClock() + time,OneServerModelPolicy.getInstance());
        return aux;
    }
}