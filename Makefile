DOMAIN = src/domain
DRIVERS = src/drivers
PERSISTENCE = src/persistence
PRESENTATION = src/presentation

default: main

main:
	javac -d out $(PERSISTENCE)/*.java $(PRESENTATION)/*.java $(DOMAIN)/*.java

main.jar: main
	jar cvmf out/domain/Main.jar "Main-Class: domain.Main" -C out/domain/ .

mainRunjar: main.jar
	jar cfe out/domain/Main.jar domain.Main out/domain/*.class

mainRun: main
	java -cp ./out;. domain.Main

drivers: main
	javac -d out $(DRIVERS)/*.java

driverWhiteCell.jar: drivers
	jar cvf out/drivers/driverWhiteCell.jar -C out/drivers/ .

driversWhiteCellRunjar: driverWhiteCell.jar
	java -jar out/drivers/driverWhiteCell.jar

driverCtrlKakuro.jar: drivers
	jar cvf out/drivers/driverCtrlKakuro.jar -C out/drivers/ .

driversCtrlKakuroRunjar: driverCtrlKakuro.jar
	java -jar out/drivers/driversCtrlKakuro.jar

tests: main
	javac -d out $(TESTS)/*.java

testWhiteCell.jar: tests
	jar cvf out/tests/testWhiteCell.jar -C out/tests/ .

testWhiteCellRunjar: testWhiteCell.jar
	java -jar out/tests/testWhiteCell.jar

testBlackCell.jar: tests
	jar cvf out/tests/testBlackCell.jar -C out/tests/ .

testBlackCellRunjar: testBlackCell.jar
	java -jar out/tests/testBlackCell.jar	

testKakuro.jar: tests
	jar cvf out/tests/testKakuro.jar -C out/tests/ .

testKakuroRunjar: testKakuro.jar
	java -jar out/tests/testKakuro.jar

clean:
	rm -rf out/domain out/persistence out/presentation out/drivers out/tests