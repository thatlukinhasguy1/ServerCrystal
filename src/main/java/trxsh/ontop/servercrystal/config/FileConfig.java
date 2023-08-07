package trxsh.ontop.servercrystal.config;

import trxsh.ontop.servercrystal.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class FileConfig {

    private File file;
    public static String separator = System.getProperty("line.separator");

    public FileConfig(File file) {

        this.file = file;

    }

    public void save() throws IOException {

        FileWriter writer = new FileWriter(file);
        StringBuilder builder = new StringBuilder();

        for(UUID id : Main.players.keySet()) {

            boolean b = Main.players.get(id);
            builder.append(id.toString())
                    .append("::")
                    .append(b)
                    .append(separator);

        }

        writer.write(builder.toString());

    }

    public void load() throws IOException {

        for(String line : Files.readAllLines(file.toPath())) {

            UUID id = UUID.fromString(line.split("::")[0]);
            boolean b = Boolean.parseBoolean(line.split("::")[1]);

            Main.players.put(id, b);

        }

    }

    public boolean exists() {

        return file.exists();

    }

}
