// Implementation of Weighted round robin

import java.util.*;

class WeightedRoundRobinBalancer {
  private List<Server> servers;
  private int[] cumulativeWeights;
  private int totalWeight;
  private int currentIndex;
  private Random random;
  
  public WeightedRoundRobinBalancer(List<Server> servers){
    this.servers = new ArrayList<>(servers);
    this.totalWeight = calculateTotalWeight(servers);
    this.cumulativeWeights = calculateCumulativeWeights(servers);
    this.currentIndex = 0;
    this.random = new Random();
  }
  
  private int calculateTotalWeight(List<Server> srevers){
    int totalWeight = 0;
    for (Server server: servers){
      totalWeight += server.getWeight();
    }
    
    return totalWeight;
  }
  
  private int[] calculateCumulativeWeights(List<Server> servers) {
    int[] cumulativeWeights = new int[servers.size()];
    cumulativeWeights[0] = servers.get(0).getWeight();
    
    // servers = [2, 3, 4 , 5] ---> cumulativeWeights = [2, 5, 9, 14]
    
    for(int i=1; i<servers.size(); i++){
      cumulativeWeights[i] = cumulativeWeights[i-1] + servers.get(i).getWeight();
    }
    
    return cumulativeWeights;
  }
  
  public Server getNextServer() {
    int randomValue = random.nextInt(totalWeight);
    
    // randomValue ---> (2,14) --> 4 ---> 8 ---> 0
    
    for( int i =0; i< cumulativeWeights.length;  i++){
      if(randomValue<cumulativeWeights[i]) {
        currentIndex = i;
        break;
      }
    }
    
    return servers.get(currentIndex);
  }
  
  // Inner class representing a server with a weight
  static class Server {
    private String name;
    private int weight;
    
    public Server(String name, int weight) {
      this.name = name;
      this.weight = weight;
    }
    
    public String getName() {
      return name;
    }
    
    public int getWeight() {
      return weight;
    }
  }
}

public class Main {
    public static void main(String[] args) {
      List<WeightedRoundRobinBalancer.Server> serverList = new ArrayList<>();
      serverList.add(new WeightedRoundRobinBalancer.Server("Server 0", 1));
      serverList.add(new WeightedRoundRobinBalancer.Server("Server 1", 2));
      // serverList.add(new WeightedRoundRobinBalancer.Server("Server 2", 1));
      // serverList.add(new WeightedRoundRobinBalancer.Server("Server 3", 1));
      
      WeightedRoundRobinBalancer balancer = new WeightedRoundRobinBalancer(serverList);
      
      for(int i=0; i<10; i++) {
        WeightedRoundRobinBalancer.Server nextServer = balancer.getNextServer();
        System.out.println("Request " + (i+1) + ": Routed to " + nextServer.getName());
      }
      
  }
}
