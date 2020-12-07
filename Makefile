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
	cd src; javac -d ../out drivers/*.java; cd ..

driverWhiteCell.jar: drivers
	cd out; jar cfe drivers/DriverWhiteCell.jar drivers.DriverWhiteCell drivers/*.class domain/*.class persistence/*.class presentation/*.class; cd ..

driverWhiteCellRunjar: driverWhiteCell.jar
	cd out; java -jar drivers/DriverWhiteCell.jar; cd ..

driverCtrlKakuro.jar: drivers
	cd out; jar cfe drivers/DriverCtrlKakuro.jar drivers.DriverCtrlKakuro drivers/*.class drivers/*.class domain/*.class persistence/*.class presentation/*.class; cd ..

driverCtrlKakuroRunjar: driverCtrlKakuro.jar
	cd out; java -jar drivers/DriverCtrlKakuro.jar; cd ..

tests: tests/domain/*.java main
	javac -cp lib/junit-4.12.jar -d ./out/ tests/domain/*.java src/domain/*.java src/persistence/*.java src/presentation/*.java

testWhiteCell.jar: tests
	cd out; jar cfe domain/WhiteCellTest.jar domain.WhiteCellTest domain/*.class persistence/*.class presentation/*.class; cd ..

testWhiteCellRun: tests
	cd out; java -cp ../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore domain.WhiteCellTest; cd ..

testBlackCell.jar: tests
	cd out; jar cfe domain/BlackCellTest.jar domain.BlackCellTest domain/*.class persistence/*.class presentation/*.class; cd ..

testBlackCellRun: tests
	cd out; java -cp ../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore domain.BlackCellTest; cd ..

testKakuro.jar: tests
	cd out; jar cfe domain/KakuroTest.jar domain.KakuroTest domain/*.class persistence/*.class presentation/*.class; cd ..

testKakuroRun: tests
	cd out; java -cp ../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore domain.KakuroTest; cd ..

clean:
	rm -rf out/domain out/persistence out/presentation out/drivers out/tests