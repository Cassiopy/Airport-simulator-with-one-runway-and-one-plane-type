package ar.edu.unsl.mys.resources;

public class Airstrip extends Server
{

    public Airstrip()
    {
        super(new CustomQueue());
    }

    @Override
    public String toString()
    {
        return "Airstrip "+this.getId()+" -- busy? : "+this.isBusy()+" -- attending: "+this.getServedEntity();
    }
}