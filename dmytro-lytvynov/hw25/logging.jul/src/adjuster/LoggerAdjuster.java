package adjuster;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerAdjuster {
    public void adjustLogger(Logger logger) {
        String separator = File.separator;
        String configFilePath = "." + separator +
                "logging.jul" + separator + "jul.config" + separator + "logging.properties";
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler();
            FileInputStream stream = new FileInputStream(configFilePath);
            LogManager.getLogManager().readConfiguration(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleFormatter formatter = new SimpleFormatter();
        if (fileHandler != null) {
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } else {
            throw new NullPointerException("FileHandler doesn't exist");
        }
    }
}
