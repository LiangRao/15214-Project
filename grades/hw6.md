hw6 Feedback
============
 
#### Part 1: Documentation (12/20)
Your synchronization strategy is mostly understandable from your annotations and/or other documentation, but we needed to infer some decisions from the implementation.
 - The implementation uses @GuardedBy annotations on final fields. Synchronization on final fields is not necessary, because those fields cannot change. [minor]
 - We cannot identify from the documentation how the invariant is established that transferring money between two accounts is safe (i.e., the total balance remains the same). Your synchronization block holds a lock on the bank object instead of the accounts as documented by you. [medium]
 - We cannot identify from the documentation how the invariant is established that every living person has a bank account. [minor]


#### Part 1: Safety, Lock Contention, Deadlocks (17/30)
Your is unsafe and may lead to surprising effects due to race conditions.
 - Your solution is safe but performs very coarse-grained locking which leaves little room for parallelism: You cannot transfer money between unrelated bank accounts in the same bank at the same time. [e.g locks on the bank, accounts or economy rather than on the involved account(s)] [severe]
 - Your solution is unsafe: We cannot identify how you ensure that every person has a bank account. We would have expected some form of synchronization around operations that access or modify both the list of people in the economy and the list of bank accounts in the bank. [medium]
 
#### Part 2: Remote Execution (20/20)
The chosen mechanism for communicating between coordinator and workers seems appropriate, well done.

#### Part 2: Concurrency and Safety (30/30)
You perform work of subtasks concurrently and you ensure that shared state is handled safely. Well done.
 
#### Part 2: Framework Design and Documentation (24/30)
Your framework fulfills most of our requirements, with a few exceptions:
 - It is unclear how a subtask would be able to create other subtasks (e.g., create a subtask for analyzing every commit message, based on the number of commit messages received in a different subtask) [medium]
 - Cannot identify any attempt to interrupt subtasks or handle interruption for graceful termination. [medium]
 
#### Part 2: Task Implementations (20/20)
You implemented all three requested tasks with reasonable granularity so that parallelism can be exploited. Well done.

#### Part 2: Design Discussion (10/10)
Your design discussion is reasonable. It describes the overall structure and your concurrency strategy. Good job.
 
#### Documentation, Style, and Git Practices (20/20)
Your implementation meets our expectations regarding documentation and style and you use good practices for committing to Github.


---
 
#### Total (153/180)
 
Late days used: 0
 
---
 
#### Additional Notes
 
Graded by: Alvin Shi (xiangs1@andrew.cmu.edu