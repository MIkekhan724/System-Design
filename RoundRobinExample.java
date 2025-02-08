// Round robin Load Balancer implementation


import java.util.*;
import java.util.ArrayList;
import java.util.List;

class LoadBalancer {
  private List<String> servers;
  private int currentIndex;
  
  public LoadBalancer(List<String> servers){
    this.servers = new ArrayList<>(servers);
    this.currentIndex = 0;
  }
  
  public String getNextServer() {
    String nextServer = servers.get(currentIndex);
    currentIndex = (currentIndex + 1) % servers.size();
    return nextServer;
  }
  
}

class RoundRobinExample {
  public static void run() {
    // Sample list of servers;
    List<String> serverList = new ArrayList<>();
    serverList.add("Server 1");
    serverList.add("Server 2");
    serverList.add("Server 3");
    
    // Create a load balancer with server List
    LoadBalancer loadBalancer = new LoadBalancer(serverList);
    
    // Simulate requests to the load balancer
    // Mocking the client requests--> 10 requests
    for(int i=0; i<100; i++) {
      String nextServer = loadBalancer.getNextServer();
      System.out.println("Request "+ (i+1) + ": Router to " + nextServer );
    }
    
  }
}

public class Main {
    public static void main(String[] args) {
      RoundRobinExample.run();
  }
}
