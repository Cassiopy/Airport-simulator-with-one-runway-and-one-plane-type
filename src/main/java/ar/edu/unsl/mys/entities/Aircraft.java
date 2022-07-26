package ar.edu.unsl.mys.entities;

public class Aircraft extends Entity
{
    public Aircraft()
    {
        // constructor vacio 
    }
    
    @Override
    public String toString()
    {
        return "id = "+this.getId()+" >> defaul aircraft type";
    }
}