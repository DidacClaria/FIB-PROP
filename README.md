#**PROP FIB:**
### Kakuro Project

We are a group of students from UPC, that developed this project for the subject PROP.
The main functionalities of this project as it is right now are:
   - The generation of Kakuros.
   - The solver of Kakuros.
   
We aim to improve the algorithm of generation for further versions, because as it is right now there is set a limited size of the Kakuro for the generator.

The whole project has the documentation with *Javadoc* utility, and it can be seen inside the **/docs** folder.
Inside this folder you can also found a complete description of the project as well as the static class diagram made with UML.

In order to execute and try the program there is provided a Makefile. The output of all the executables is stored into the **/out** folder.

If you want to compile the main funcionallities of the system you can execute the following command:

```shell script
make main
```
Then if you want to extract a **.jar** executable, you can use:

```shell script
make main.jar
```

Either way if you want to test the drivers or the unit tests from the JUNIT (version 4.12) library, you can do it with the following command:

```shell script
make drivers
```

If you also want the **.jar** executables, you can do:

```shell script
make drivers.jar
```