
package bankersalgorithmgui;

/**
 *
 * @author Raymundo Ismael
 */
public class Algorithm {
   
    // Function to find the system is in safe state or not
    
public static void calculateNeed(int need[][], int maxm[][],int allot[][], int P, int R)
{
    // Calculating Need of each P
    for (int i = 0 ; i < P ; i++)
        for (int j = 0 ; j < R ; j++)
 
            // Need of instance = maxm instance -
            //                    allocated instance
            need[i][j] = maxm[i][j] - allot[i][j];
}    
    
public static boolean isSafe(int processes, int resources, int avail[], int maxm[][],int allot[][])
{
    int P = processes; // # of processes
    int R = resources; // # of Resources
    
    int[][] need = new int[P][R];
 
    // Function to calculate need matrix
    calculateNeed(need, maxm, allot, P, R);
 
    // Mark all processes as infinish
    int finish[] = new int[P];  // Values are 0

    // To store safe sequence
    int safeSeq[] = new int [P];
 
    // Make a copy of available resources
    int work[] = new int [R];
    
    
    for (int i = 0; i < R ; i++)
        work[i] = avail[i];
 
    // While all processes are not finished
    // or system is not in safe state.
    
    int count = 0;
    while (count < P)
    {
        // Find a process which is not finish and
        // whose needs can be satisfied with current
        // work[] resources.
        boolean found = false;
        for (int p = 0; p < P; p++)
        {
            // First check if a process is finished,
            // if no, go for next condition
            if (finish[p] == 0)
            {
                // Check if for all resources of
                // current P need is less
                // than work
                int j;
                for (j = 0; j < R; j++)
                    if (need[p][j] > work[j])
                        break;
 
                // If all needs of p were satisfied.
                if (j == R)
                {
                    // Add the allocated resources of
                    // current P to the available/work
                    // resources i.e.free the resources
                    for (int k = 0 ; k < R ; k++)
                        work[k] += allot[p][k];
 
                    // Add this process to safe sequence.
                    safeSeq[count++] = p;
 
                    // Mark this p as finished
                    finish[p] = 1;
 
                    found = true;
                }
            }
        }
 
        // If we could not find a next process in safe
        // sequence.
        if (found == false)
        {
            System.out.println("System is not in safe state");
            return false;
        }
    }
 
    // If system is in safe state then
    // safe sequence will be as below
    System.out.println("System is in safe state.\nSafe \nsequence is: ");
    
    for (int i = 0; i < P ; i++)
        System.out.print(safeSeq[i] + " ");
 
    System.out.println(" ");
    return true;
} 
}
