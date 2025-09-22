import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Main {


    public static void main(String[] args)
    {

        ArrayList<ArrayList<Integer>> Graph = runJumps(10, 100);

        printGraph(Graph);

        printProbability(Graph, 3);

        printExpectedValue(Graph);

    }

    public static void printExpectedValue(ArrayList<ArrayList<Integer>> Graph)
    {
        double elementTotal = 0;
        for(int i = 0; i < Graph.size(); i++)
        {
            if (!Graph.get(i).isEmpty())
            {
                elementTotal += Graph.get(i).size() * Graph.get(i).getFirst();
            }
        }

        System.out.println("Expected Value: " + elementTotal / numOfGraphElements(Graph));
    }

    public static void printProbability(ArrayList<ArrayList<Integer>> Graph, int in)
    {
        double probability = findGraphProbability(Graph, in);
        System.out.println("Probability of " + in + " is " + probability);
        System.out.println("P_X[ x = " + in + " ] = " + probability);
    }


    public static double findGraphProbability(ArrayList<ArrayList<Integer>> Graph, int in)
    {
        double converter = numOfGraphElements(Graph);
        if(!Graph.get(in).isEmpty())
        {
            return Graph.get(in).size() / converter;
        }
        else {
            return 0.0;
        }
    }

    public static int numOfGraphElements(ArrayList<ArrayList<Integer>> Graph)
    {
        int sum = 0;
        for(ArrayList<Integer> value : Graph)
        {
            sum += value.size();
        }
        return sum;
    }



    public static void printGraph(ArrayList<ArrayList<Integer>> input)
    {
        System.out.println();
        System.out.println("Y-Axis (Printing Down) is the number of jumps it took to reach the end");
        System.out.println("X-Axis (Sideways) is the number of trials where it took that number of jumps to reach the end");
        System.out.println("Each # represent the number of trials that ended with that many jumps");
        System.out.println();

        for(int i = 0; i < input.size(); i++)
        {
            if (!input.get(i).isEmpty())
            {
                System.out.print(input.get(i).getFirst() + ": ");
            }
            for(int j = 0; j < input.get(i).size(); j++)
            {
                System.out.print("#");
            }
            System.out.println();
        }

        System.out.println("Summary: Format is [# of Jumps | # of Trials of that # of jumps]");
        for(int i = 0; i < input.size(); i++)
        {
            if (!input.get(i).isEmpty())
            {
                System.out.print("[ " + input.get(i).get(0) + " | " + input.get(i).size() + " ]");
            }
        }

        System.out.println();
        System.out.println( "# of Graph Elements = " + numOfGraphElements(input));
        System.out.println();

    }


    //When N = 10, what is the probability that it takes the frog exactly 3 jumps?
    public static ArrayList<ArrayList<Integer>> runJumps(int jumps, int trials)
    {
        //Creates and initializes 2D Array to Store results (like a graph)
        ArrayList<ArrayList<Integer>> toReturn = new ArrayList<>();
        for (int i = 0; i <= jumps; i++)
        {
            toReturn.add(new ArrayList<>());
        }

        //Runs a random number generator and runs the frog-jumping experiment for the number of trials
        Random rand = new Random();
        for (int i = 0; i < trials; i++)
        {
            int jumpTotal = 0;
            int j = jumps;

            while (j > 0)
            {
                int toSub = rand.nextInt(jumps) + 1;
                jumpTotal += 1;

                j-=toSub;
            }

            toReturn.get(jumpTotal).add(jumpTotal);
        }


        return toReturn;
    }




}
