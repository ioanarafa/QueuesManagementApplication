# PT2024_30223_Rafa_Ioana_Assignment_2

Design and implement a queues management application which assigns clients to queues such that the waiting time is minimized. 

Queues are commonly used to model real world domains. The main objective of a queue is to provide a place for a "client" to wait before receiving a "service". The management of queue-based systems is interested in minimizing the time amount their "clients" are waiting in queues before they are served. One way to minimize the waiting time is to add more servers, i.e., more queues in the system (each queue is considered as having an associated processor) but this approach increases the costs of the service supplier.  

The queues management application should simulate (by defining a simulation time 𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛) a series of N clients arriving for service, entering Q queues, waiting, being served and finally leaving the queues. All clients are generated when the simulation is started, and are characterized by three parameters: ID (a number between 1 and N), 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 (simulation time when they are ready to enter the queue) and 𝑡𝑠𝑒𝑟𝑣𝑖𝑐𝑒 (time interval or duration needed to serve the client; i.e. waiting time when the client is in front of the queue). The application tracks the total time spent by every client in the queues and computes the average waiting time. Each client is added to the queue with the minimum waiting time when its 𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 time is greater than or equal to the simulation time (𝑡𝑎𝑟𝑟𝑖𝑣𝑎𝑙 ≥𝑡𝑠𝑖𝑚𝑢𝑙𝑎𝑡𝑖𝑜𝑛).


•	Object-oriented programming design 

•	Random Client Generator 

•	Multithreading: one thread per queue  

•	Appropriate synchronized data structures to assure thread safety 

•	Log of events displayed in a .txt file 

•	Implement classes with maximum 300 lines (except the UI classes) and methods with maximum 30 lines 

•	Use the Java naming conventions 

•	Good quality documentation addressing all sections from the documentation structure.

• Strategy pattern and the two strategies (shortest time, shortest queue) for allocating clients to queues 

• Graphical user interface for: (1) simulation setup, and (2) displaying the real-time queue evolution.

• Display of simulation results (average waiting time, average service time, peak hour for the simulation interval) in the graphical user interface/.txt file corresponding to the log events 

