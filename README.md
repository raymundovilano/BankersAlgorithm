# BankersAlgorithm
A user interface that simulates bankers algorithm for resource allocation and deadlock avoidance.

This user interface is built with JavaFX, the purpose of this project was for me to understand and apply my algorithms knowledge and my coding skills in order to produce a user interface that could simulate Banker's Algorithm.

The Banker algorithm, sometimes referred to as the detection algorithm, is a resource allocation and deadlock avoidance algorithm developed by Edsger Dijkstra that tests for safety by simulating the allocation of predetermined maximum possible amounts of all resources, and then makes an "s-state" check to test for possible deadlock conditions for all other pending activities, before deciding whether allocation should be allowed to continue.

The software works as follows: once you run the program you will be prompted to select the number of processes and number of resources you will use. This will create the arrays based on the specifications.

Afterwards, the program will let the user input the data inside the arrays, once the arrays are filled with data, the user is prompted to collect the data.

Fianlly, the system displays the generated matrices and lets the user select the Run Algorithm button.

After running the algorithm, the system will prompt a message, wether if the System is in safe state or not.

Note:
I like this program because I tackled the problem and was able to convert this algorithm into a fun project. Since I strugglet a lot with the error handling and the data structures and loops, I was able to learn how to tackle this problems.
