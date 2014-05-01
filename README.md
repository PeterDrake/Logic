Logic
=====

Logic trainer for Software Development, Spring 2014

Development instructions:
- Install [eclipse](http://eclipse.org/) and the [ANTLR 4 IDE plugin](https://github.com/jknack/antlr4ide)
- Make sure that all files are UTF8 encoded
- Clone the source (https://github.com/PeterDrake/Logic)

Running instructions:
The overall main method is in LogicTrainer.java. Once run, a window is opened with three buttons, 
that allows the user to choose which part of the logic trainer they would like to use. The three 
options are: make a truth table, a truth functional wff checker, or a quantificational wff checker. 
There is also a drop down menu with an option to bring up a preference pane. This allows the user 
to change which syntax is used for certain symbols when inputting formulas through the button panels 
provided. The truth table builder initially brings up a button panel for truth functional logic, 
through which the user inputs a formula and if it is well formed, a truth table will be built based 
on that target formula. The user then has the option to add another formula, add a scratch work 
column, or check the values they have input in the truth table. The truth functional logic and 
quantificational logic buttons bring up a button panel that allows the user to enter a formula and 
it will tell them whether or not it is well formed for the desired logic type.
