DOMAIN = src/domain
DRIVERS = src/drivers
PERSISTENCE = src/persistence
PRESENTATION = src/presentation

default: main

main:
	javac -d out $(PERSISTENCE)/*.java $(PRESENTATION)/*.java $(DOMAIN)/*.java

main.jar: main
	cd out; jar cfe domain/Main.jar domain.Main domain/*.class persistence/*.class presentation/*.class; cd ..

mainRunjar: main.jar
	cd out; java -jar domain/Main.jar; cd ..

mainRun: main
	cd out; java domain.Main; cd ..

drivers: main
	javac -d out $(DRIVERS)/*.java

driverWhiteCell.jar: drivers
	cd out; jar cfe drivers/driverWhiteCell.jar drivers.driverWhiteCell drivers/*.class; cd ..

driversWhiteCellRunjar: driverWhiteCell.jar
	cd out; java -jar drivers/driverWhiteCell.jar; cd ..

driverCtrlKakuro.jar: drivers
	cd out; jar cfe drivers/driverCtrlKakuro.jar drivers.driverCtrlKakuro drivers/*.class; cd ..

driversCtrlKakuroRunjar: driverCtrlKakuro.jar
	cd out; java -jar drivers/driverCtrlKakuro.jar; cd ..

tests: main
	javac -d out $(TESTS)/*.java

testWhiteCell.jar: tests
	cd out; jar cfe tests/testWhiteCell.jar tests.testWhiteCell tests/*.class; cd ..

testWhiteCellRunjar: testWhiteCell.jar
	cd out; java -jar tests/testWhiteCell.jar; cd ..

testBlackCell.jar: tests
	cd out; jar cfe tests/testBlackCell.jar tests.testBlackCell tests/*.class; cd ..

testBlackCellRunjar: testBlackCell.jar
	cd out; java -jar tests/testBlackCell.jar; cd ..

testKakuro.jar: tests
	cd out; jar cfe tests/testKakuro.jar tests.testKakuro tests/*.class; cd ..

testKakuroRunjar: testKakuro.jar
	cd out; java -jar tests/testKakuro.jar; cd ..

clean:
	rm -rf out/domain out/persistence out/presentation out/drivers out/tests