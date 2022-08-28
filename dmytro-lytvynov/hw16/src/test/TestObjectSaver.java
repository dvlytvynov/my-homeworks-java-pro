package test;

import cars.Car;
import file.ObjectReader;
import file.ObjectSaver;

import java.io.File;
import java.io.IOException;

public final class TestObjectSaver {

    private TestObjectSaver() {
    }

    public static void testFileSaver() throws IOException {
        Car redFord = new Car("Red Ford", "Tourneo Connect", 75.8);
        redFord.startJourney(3, 115.8, 10.2);

        String directory = "resources";
        String fileName = "SavedObject.txt";
        char sep = File.separatorChar;
        File file = new File("." + sep);
        String dirPath = file.getAbsolutePath() + sep + directory;
        String filePath = file.getAbsolutePath() + sep + directory + sep + fileName;
        (new File(dirPath)).mkdir();
        (new File(filePath)).createNewFile();

        ObjectSaver objectSaver = new ObjectSaver();
        objectSaver.saveToFile(redFord, filePath);
        System.out.println("Object \"" +
                redFord.getClass().getSimpleName() + "\" was output to file \"" + filePath + "\"");
        ObjectReader objectReader = new ObjectReader();
        Car savedCar = objectReader.readFromFile(filePath);
        System.out.println("\nNative object \"" +
                redFord.getClass().getSimpleName() + "\"\n" + redFord.getCarInfo(10.5));
        System.out.println("\nSaved object \"" +
                savedCar.getClass().getSimpleName() + "\"\n" + savedCar.getCarInfo(10.5));
    }
}
