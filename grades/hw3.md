hw3 Feedback
============

#### Demonstrate mastery of earlier learning goals, especially the concepts of information hiding and polymorphism, software design based on informal specifications, testing, and testing best practices. (47/60)

* Information hiding (10/10)
[summary] Your implementation effectively demonstrates your understanding of information hiding. Well done!

* Compliance with specification (28/30)
[summary] Your solution mostly meets the specification, although we have found the following issues:

  * Permutation Generator (8/10)
  * -2, Your solution unnecessarily duplicates code for your three implementation. [MEDIUM]
  * Cryptarithm Representation (7/7)
  * Cryptarithm Solver (7/7)
  * Anagram Solver (6/6)
  * Your anagram solver adds a prefix of your resources folder to the taken path. This reduces usability of your program and is generally bad practice. [MINOR]

* Testing practices (4/10)
[summary] Your solution demonstrates some proficiency in testing, but we identified several issues:

  * -3, You did not test that your permutation generator produces permutations that only contain elements from the original list. [MEDIUM]

  * -3, You did not test your permutation generator produces unique permutations that contain all the elements from the original set. [MEDIUM]

* Java coding best practices and style (5/10)
[summary] There are several repeated issues regarding documentation and style that you might want to address:

  * -1, Your permutation generator and cryptarithm solver should be in separate packages. It is important to keep an organized package structure to facilitate information hiding and code reuse. [MINOR]

  * -2, Please remove commented out code or dead code, your submissions should be finalized. These include unused imports, private methods, variables, and initializations. (ProblemSolver:33) [MEDIUM]

  * -2, Your commit messages are not very descriptive of the changes you make. The first line of a commit message should always contain a concise description of the specific changes you made, and you should commit sufficiently regularly that it's easy to describe your changes with such a concise, specific message.  In practice commit messages are often very important to track changes in a project. Please attempt more descriptive changes in the future. [MEDIUM]

#### Use inheritance and delegation, and design patterns effectively to achieve design flexibility and code reuse (30/35)
[summary] Your solution demonstrates basic proficiency in design, but we identified a few issues:

  * -5, You are storing a collection of all permutations in memory. This is an unnecessary burden on the system. Instead, you can use either the strategy, template method or iterator pattern to execute the client operation as you generate and only store valid results, resulting in an overall more customizable and better performance design. [SEVERE]

  * The Strategy pattern should have taken ProblemSolver into the perm method [MINOR]

  * You should implement Iterable when using the iterator pattern in Java. [MINOR]

#### Discuss the relative advantages and disadvantages of alternative design choices (5/5)
[summary]Your discussion effectively justifies your design and an understanding of tradeoffs. Well done!

---

#### Total (82/100)

Late days used: 0 (5 left)

---

#### Additional Notes

Graded by: Evans Hauser (ehauser@andrew.cmu.edu)
