package observablefilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Folder implements Observable {
    private final String directory;
    private final List<Observer> observersList = new ArrayList<>();

    public Folder(String directory) {
        this.directory = directory;
    }

    @Override
    public void subscribe(Observer observer) {
        observersList.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observersList.remove(observer);
    }

    @Override
    public void sendChanges() {
        for (Observer ob : observersList) {
            ob.update(directory);
        }
    }

    public void addFile(String directory, String fileName) throws IOException {
        File file = new File(directory);
        file.mkdirs();
        file = new File(directory + "/" + fileName);
        file.createNewFile();
        System.out.printf("%n ----- Added file: %s -----%n", file.getAbsolutePath());
        sendChanges();
    }

    public void deleteFile(String directory, String fileName) {
        File file = new File(directory + "/" + fileName);
        file.delete();
        System.out.printf("%n ----- Deleted file: %s -----%n",  file.getAbsolutePath());
        sendChanges();
    }
}
