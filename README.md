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

##Folder organization
**/docs:** In this folder there is the complete documentation of the program, as well as the statement of the project.
**/src:** This folder contains the source code of the program, from the models from domain to the tests and drivers.
**/out:** In this folder there are always some files used by the drivers in order to read different Kakuros format, but the main utility of this folder is that is where all the compiled classes and executables goes to.
**/lib:** It contains the jar of external libraries.

##How to run and compile

In order to execute the main functionallity of the program you can run either:
```shell script
make mainRun
```
or
```shell script
make mainRunjar
```

Then if you also want to test the drivers or the unit tests from the JUNIT (version 4.12) library, you can do it with the following commands:

```shell script
make driverWhiteCellRunjar
make driverCtrlKakuroRunjar
make testWhiteCellRun
make testBlackCellRun
make testKakuroRun
```