package SBoardServer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {

    private File file;
    private SimpleDateFormat simpleDateFormat;

    public Debug(String name, Path path) {
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd:MM:yyyy");
        file = new File(path.toString(), name + ".log");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(String line, String prefix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append("[").append(simpleDateFormat.format(new Date(System.currentTimeMillis()))).append("] ");
            bw.append(prefix).append(" " + line);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(String messages) {
        write(messages, "[ERROR]");
    }

    public void info(String messages) {
        write(messages, "[INFO]");
    }

    public void warning(String messages) {
        write(messages, "[WARNING]");
    }
}
