import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class set_covering {
    // calculate minimum cost of set cover
    public static void minCost(Set universalSet, Set subset[], int cost[], int m) {
        // Set I is used to get minimum cost
        Set<Integer> I = new HashSet<Integer>();
        int minCost = 0;
        System.out.print("Best possible Solution sets is: \n->");
        // Loop continues till the time I contains all the elements of U
        while (!I.equals(universalSet)) {

            // calculate the min cost
            double min = Double.MAX_VALUE;
            // loop to find the minimum cost
            int index = -1;
            double effCost[] = new double[m];
            for (int i = 0; i < m; i++) {
                // diff is difference between S[i] and I
                Set<Integer> diff = new HashSet<Integer>(subset[i]);
                diff.removeAll(I);
                // if size of diff != 0
                if (diff.size() != 0) {
                    effCost[i] = cost[i] / diff.size();
                }
                // if size of diff = 0
                else {
                    effCost[i] = Double.MAX_VALUE;
                }
                // change min if effCost[i] < min
                if (min > effCost[i]) {
                    min = effCost[i];
                    index = i;
                }
            }
            // Printing the S[i]
            System.out.print("S[" + (index + 1) + "] ");
            // Used to get union of I and S[index]
            Set<Integer> union = new HashSet<Integer>(I);
            union.addAll(subset[index]);
            // I = union of I and S[index]
            I = union;
            // Min cost
            minCost = minCost + cost[index];
        }
        System.out.println(" Minimum cost: " + minCost);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // no of elements in U

        System.out.println("------------ Subset Covering Prob ------------\n");
        System.out.print("Enter the elements in universalSetersal Set : ");
        int n = sc.nextInt();
        // for storing the values in universalSetersal set
        System.out.print("Enter the elements -> ");
        Set<Integer> universalSet = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            universalSet.add(temp);
        }
        // no of sets in S
        System.out.print("Enter the number of sets : ");
        int m = sc.nextInt();
        Set<Integer> S[] = new HashSet[m];
        int cost[] = new int[m];
        for (int i = 0; i < m; i++) {
            S[i] = new HashSet<Integer>();
            System.out.print("\n--------S[" + (i + 1) + "]--------\n");
            // no of elements in S[i]
            System.out.print("\nEnter the elements of S[" + (i + 1) + "]: ");
            int size = sc.nextInt();
            System.out.print("Enter the elements : ");
            for (int j = 0; j < size; j++) {
                int temp = sc.nextInt();
                S[i].add(temp);
            }
            // Cost of S[i]
            System.out.print("Enter the cost(S[" + (i + 1) + "]) : ");
            cost[i] = sc.nextInt();
        }
        // method to get min cost
        minCost(universalSet, S, cost, m);
        sc.close();
    }
}