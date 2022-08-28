package test;

import exception.SerializationException;
import input.output.FileReaderWriter;
import input.output.ReaderWriter;
import sample.Detail;
import sample.Sample;
import serializer.DsvObjectSerializer;
import serializer.Serializer;
import serializer.XmlObjectSerializer;

import java.io.File;

public class FileFormatTest {
    public void test() throws SerializationException {
        Sample detail = new Detail();
        setObjectFields(detail);
        String directory = "." + File.separatorChar + "resources";

        System.out.println("\n- - Test DSV Serializer - -");
        Serializer<Sample> dsvSerializer = new DsvObjectSerializer<>(",");
        ReaderWriter<Sample> fileReaderWriter =
                new FileReaderWriter<>(dsvSerializer, ".csv");
        System.out.println("Native object:\n" + detail);
        String fileName = fileReaderWriter.saveObject(directory, detail);
        Sample savedDetail = fileReaderWriter.loadObject(directory, fileName);
        System.out.println("Loaded object from file:\n" + savedDetail);

        System.out.println("\n- - Test XML Serializer - -");
        Serializer<Sample> xmlSerializer = new XmlObjectSerializer<>();
        ReaderWriter<Sample> xmlReaderWriter =
                new FileReaderWriter<>(xmlSerializer, ".xml");
        System.out.println("Native object:\n" + detail);
        String xmlFileName = xmlReaderWriter.saveObject(directory, detail);
        Sample xmlSavedDetail = xmlReaderWriter.loadObject(directory, xmlFileName);
        System.out.println("Loaded object from file:\n" + xmlSavedDetail);
    }

    private void setObjectFields(Sample obj) {
        obj.setName("Object Detail");
        obj.setAltName("Detail - version 1");
        obj.setPosition((byte) 56);
        obj.setQuantity((short) 5640);
        obj.setCharacter('@');
        obj.setNumber(125899);
        obj.setPersonalCode(102030405060888795L);
        obj.setAvailable(true);
        obj.setWidth(2.456F);
        obj.setLength(50.23);
    }
}
