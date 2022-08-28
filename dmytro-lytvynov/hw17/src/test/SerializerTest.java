package test;

import detail.A;
import detail.B;
import detail.C;
import detail.Detail;
import detail.ExtraC;
import exception.SerializationException;
import serialization.Serializer;
import serialization.alternative.serializer.FileNameChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializerTest<T> {
    private Serializer<T> serializer;

    public void test() throws SerializationException {
        String directory = "." + File.separatorChar + "resources";
        serializer = new Serializer<>(directory);

        A detailA = new A();
        settingObjectA(detailA);
        C detailC = new C();
        settingObjectC(detailC);
        B detailB = new B();
        settingObjectB(detailB, detailC);

        System.out.println("\n- - Test standard serializer:");
        saveAndLoad(detailA);
        saveAndLoad(detailB);
        saveAndLoad(detailC);

        System.out.println("\n- - Test Externalizable class ExtraC:");
        ExtraC DetailExtraC = new ExtraC();
        settingObjectExtraC(DetailExtraC);
        saveAndLoadExtraC(directory, DetailExtraC);
    }

    private void saveAndLoad(Detail obj) throws SerializationException {
        System.out.println("\nNative object: \n" + obj.getDetailInfo());
        String fileName = serializer.writeObject((T) obj);
        System.out.println("New file name of saved object "
                + obj.getClass().getSimpleName() + ": " + fileName);
        Detail savedObject = (Detail) serializer.readObject(fileName);
        System.out.println("Loaded object from file:");
        System.out.println(savedObject.getDetailInfo());
    }

    private void settingObjectA(A detail) {
        detail.setName("Frame");
        detail.setNumber(55);
        detail.setWeight(124.2);
        detail.setCurrentTemperature(44.5);
    }

    private void settingObjectC(C detail) {
        detail.setType("Screw");
        detail.setDiameter(12.0);
        detail.setLength(55);
        detail.setNumber(94);
    }

    private void settingObjectB(B detail, C innerDetail) {
        detail.setName("FrameB");
        detail.setNumber(55);
        detail.setWeight(124.2);
        detail.setCurrentTemperature(44.5);
        detail.setLength(155.2);
        detail.setWidth(24.85);
        detail.setScrew(innerDetail);
        detail.setScrewQuantity(12);
    }

    private void settingObjectExtraC(ExtraC detail) {
        detail.setType("Screw");
        detail.setHolesQuantity((byte) 25);
        detail.setShortNumber((short) 30254);
        detail.setCharacter('Y');
        detail.setNumber(94);
        detail.setLongNumber(8529637410L);
        detail.setAvailable(true);
        detail.setWidth(25.369F);
        detail.setDiameter(12.0);
        detail.setLength(55);
    }

    private void saveAndLoadExtraC(String directory, ExtraC detail) throws SerializationException {
        System.out.println("\nNative object: \n" + detail.getDetailInfo());
        FileNameChooser<ExtraC> fileNameChooser = new FileNameChooser<>();
        String newFileName = fileNameChooser.chooseFileName(directory, detail);
        String path = directory + File.separatorChar + newFileName;
        File file = new File(path);
        try {
            boolean crFile = file.createNewFile();
        } catch (IOException e) {
            throw new SerializationException("an I/O error occurred while creating a file", e);
        }
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file))) {
            detail.writeExternal(out);
        } catch (IOException e) {
            throw new SerializationException("an I/O error occurred while saving a file", e);
        }
        System.out.println("New file name of saved object "
                + detail.getClass().getSimpleName() + ": " + newFileName);

        ExtraC savedExtraC = new ExtraC();
        try (ObjectInput in = new ObjectInputStream(new FileInputStream(path))) {
            savedExtraC.readExternal(in);
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException("an I/O error occurred while reading a file", e);
        }
        System.out.println("Loaded object from file:");
        System.out.println(savedExtraC.getDetailInfo());
    }
}
