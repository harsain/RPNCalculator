# Reverse Polish Notation Calculator

## Design Pattern

Using **Interpreter Pattern**

This pattern allows us to specify how to evaluate user input and calculate results.

I have defined an _Expression_ interface with _interpret_ method, so each implementation of Expression interface will define how to interpret the expression, which in our case are Add, Divide, Subtract, Divide etc.

## Available Commands

- \+ : Add last 2 operands from the stack
- \- : Subtract last 2 operands from the stack
-  \/ : Divide last 2 operands from the stack
- \* : Multiply last 2 operands from the stack
- sqrt : Calculates the square root of the last operand in the stack
- undo : Reverses the last operation
- clear : Clears the stack
- exit : Exits the application

## Setup

I have used Maven to setup the project.

### Installation

**Clean and Compile the project**

`mvn -e clean compile`

**Clean and install the project, will produce the jar also**

`mvn -e clean install`

**Clean and run the tests**

`mvn -e clean test`

### Reports and jar
To generate **jar** we will need to run the above mentioned Maven commands.
The **jar** will be created under a new directory `target/` under the project root.
All test reports and test coverages will be available following path under project
`target/`

The _jar_ will be generated under **/target** path:
`<PROJECT_PATH>/target/RPNCalculator-1.0-SNAPSHOT.jar`

### Run the jar
- Assumption that we are in the `<PROJECT_ROOT>` path. 

`java -cp target/RPNCalculator-1.0-SNAPSHOT.jar com.harsain.RPNCalculator.App`

## Constraints

### Java Version

This application requires Java version of **1.8** and above, as I am using Java streams API