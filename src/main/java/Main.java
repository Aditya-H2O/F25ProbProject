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

        printVaryingExpectedValues(1, 10, 1000);



    }

    public static void printVaryingExpectedValues(int lower, int upper, int trials)
    {
        if(upper <= lower)
        {
            System.out.println("Error: 2nd Argument must be > 1st Argument");
            System.exit(1);
        }


        System.out.println();
        System.out.println("N values on the Y-axis going positive down");
        System.out.println("Expected values on the X-axis going positive right");
        System.out.println("Note that each | represents 0.1");
        System.out.println();

        ArrayList<Double> pairs = new ArrayList<>();
        for (int i = lower; i <= upper; i++)
        {
            ArrayList<ArrayList<Integer>> Graph = runJumps(i, trials);
            double expected = getExpectedValue(Graph);

            System.out.print("N="+i + ": ");
            if (i < 10)
                System.out.print("  ");
            if (i >= 10 && i<=99)
                System.out.print(" ");

            for(double j = 0.1; j < expected; j+=0.1)
            {
                System.out.print("|");
            }

            System.out.println();

            pairs.add(1.0*i);
            pairs.add(expected);
        }

        System.out.println("Summary: Format is [ N | E[X] ]");
        for(int i = 0; i < pairs.size(); i+=2)
        {
            System.out.print("[ " + Math.round(pairs.get(i)) + "|" + pairs.get(i+1) + " ]");
        }


    }

    public static double getExpectedValue(ArrayList<ArrayList<Integer>> Graph)
    {
        double elementTotal = 0;
        for(int i = 0; i < Graph.size(); i++)
        {
            if (!Graph.get(i).isEmpty())
            {
                elementTotal += Graph.get(i).size() * Graph.get(i).getFirst();
            }
        }

        return elementTotal / numOfGraphElements(Graph);
    }


    public static void printExpectedValue(ArrayList<ArrayList<Integer>> Graph)
    {
        System.out.println("Expected Value: " + getExpectedValue(Graph));
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
        if(jumps < 0)
        {
            System.out.println("Error: Tried to Run with Negative Jumps");
            System.exit(1);
        }
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
