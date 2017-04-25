hw5b grading rubric/notes


hw5a & hw5b Feedback
============

#### Presentation completeness and quality (10/10)

#### Design quality (15/15)

#### Planning document (5/5)

---

#### 5a Total (30/30)

---

#### Core Framework Implementation (63/80)
* Your framework meets the technical requirements to a reasonable degree. Good job!
* Your framework mostly works with a decent level of testing, but we identified the following issues: 
  * Your unit tests for your framework seem to use a test stub for the [DATA PLUGIN], but do not use a test stub for the [VISUALIZATION PLUGIN].  To adequately test your framework you should write test stubs for both types of plugins. 
  * Tests should be split up into the smallest testable parts of a program, not lumped into large test methods. 
* Note that your data plugins can't be parameterized with user inputs like credentials or anything other than a path/url. [minor]
* The general design of your framework is reasonable, but we identified the following issues: 
  * Your framework allows visualization plugins to modify the data passed to them. This is problematic, as the data received may depend on which plugin gets the data first. You should either make the data read-only, or pass a copy of the data to the visualization plugin.
  * Instead of passing a list data representing the data model you created for your domain, it’s more standard to pass in a `Data` object, which contains a list of these data objects. This way, if you choose to provide plugins with more data, it can be done without rewriting any code in the plugins.

#### Sample Plugins (35/35)
* Your provided sample plugins are adequate and meet our expectations. Good job!

#### High quality documentation and style (30/30)
* Your documentation meets our expectations. Good job!
* Build automation using gradle seems to work fine and we were able to start your framework using `gradle run`. Nice! 
* Your implementation meets our expectations regarding style.

#### Extra Credit
* +10, Your team’s framework was selected for Milestone C! Good job! :star:

---

#### 5b Total (138/145)

Late days used: 2 (0 left for hw5)
Don't worry about late days you don't need to use late days for 5c. 

---

#### Additional Notes

5a Graded by: Zilei Gu (zileig@andrew.cmu.edu)
5b Graded by: Zilei Gu (zileig@andrew.cmu.edu)

