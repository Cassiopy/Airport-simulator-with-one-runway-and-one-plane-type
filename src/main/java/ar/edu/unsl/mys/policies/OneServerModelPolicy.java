package ar.edu.unsl.mys.policies;

import java.util.List;
import ar.edu.unsl.mys.resources.Server;

public class OneServerModelPolicy implements ServerSelectionPolicy
{
    private static OneServerModelPolicy policy;

    private OneServerModelPolicy()
    {
        // Empty constructor
    }

    public static OneServerModelPolicy getInstance()
    {
        if (policy == null) policy = new OneServerModelPolicy();
        return policy;
    }

    @Override
    public Server selectServer(List<Server> servers) {
        // TODO Auto-generated method stub
        return  servers.get(0);
    }
}