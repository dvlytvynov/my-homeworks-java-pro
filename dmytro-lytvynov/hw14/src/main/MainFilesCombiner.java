package main;

import connectorandsplitter.FilesConnector;
import connectorandsplitter.FilesSplitter;

import java.io.File;
import java.io.IOException;

public class MainFilesCombiner {
    public static void main(String[] args) throws IOException {
        System.out.println("- - - - - Files Combiner - - - - -");
        String directory = "." + File.separatorChar + "resources";
        String filesType = ".txt";
        String outputFile = "Output.txt";
        String outputFolder = "SplitFiles";
        File dir = new File(directory);
        System.out.printf("This program connect all \"%s\" files, " +
                        "which are contained in folder \"%s\", \nand put them into file \"%s\":\n",
                filesType, dir.getAbsolutePath(), outputFile);
        FilesSearcher searcher = new FilesSearcher();
        String[] targetFiles = searcher.find(directory, filesType);
        if (targetFiles != null) {
            for (int i = 0; i < targetFiles.length; i++) {
                if (targetFiles[i] != null) {
                    if (targetFiles[i].equals(outputFile)) {
                        targetFiles[i] = null;
                    }
                }
            }
            FilesConnector connector = new FilesConnector();
            connector.connect(directory, targetFiles, outputFile);
            System.out.println("\n- Joined files:");
            for (String fileName : targetFiles) {
                if (fileName != null) {
                    System.out.println(fileName);
                }
            }
            FilesSplitter splitter = new FilesSplitter();
            splitter.split(directory, outputFile, outputFolder);
            System.out.printf(
                    "\nAfter that program split file \"%s\" and put split files into folder \"%s\".\n\n",
                    outputFile, dir.getAbsolutePath() + File.separatorChar + outputFolder);
        } else System.out.println("An error occurred: files are not found");
    }
}
