How to set up the project
================================
To run this project locally, do the following in order
+ run the first RemoteWorker with command line arguments -- "Peer1 15215", corresponding to Peer1 in the RemoteWorker class
+ run the first RemoteWorker with command line arguments -- "Peer2 15217", corresponding to Peer2 in the RemoteWorker class
+ run the CoordinatorServer with command line arguments -- "15212"
+ run client

How to schedule sample tasks and see their results
================================
+ You can change the tasks in the Client class, just by adding task into TaskList
or removing a task from TaskList
+ After running the Client class, you can see the result log in the separated RemoteWorker console. 
+ After all the tasks has been completely executed, the Client can get the result, and print the log result in its own console.
+ You can add the number of RemoteWorker in the CoordinatorServer main function.




