package ar.edu.unsl.mys.resources;
import java.util.Iterator;
import java.util.LinkedList;
import ar.edu.unsl.mys.entities.Entity;

public class CustomQueue extends Queue
{
    public CustomQueue()
    {
        super(new LinkedList<Entity>());
    }

    @Override
    public String toString()
    {
        String ret = "server queue "+this.getAssignedServer().getId()+":\n\t";

        Iterator<Entity> it = this.getQueue().iterator();

        while(it.hasNext())
        {
            ret += it.next().toString();
        }

        return ret;
    }

    @Override
    public void enqueue(Entity entity) {
        // TODO Auto-generated method stub
        this.getQueue().add(entity);
        this.setMaxSize();
    }

    @Override
    public Entity next() {
        // TODO Auto-generated method stub
        return this.getQueue().poll();
    }

    @Override
    public Entity checkNext() {
        // TODO Auto-generated method stub
        return this.getQueue().peek();
    }
}