package net.justcoded.mc_core.components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public final class JsonService<S> {

    private final Gson gson;
    private final Path path;
    private S object;


    public JsonService(S object, String fileName, Path path) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.object = object;
        this.path = Path.of(
                        path + File.separator +
                        fileName  + ".json");
    }

    public boolean saveObjectDataToJson() {
        try {
            Writer writer = Files.newBufferedWriter(path);
            gson.toJson(object, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean readObjectDataFromJson(Class<S> clazz) {
        try {
            Reader reader = Files.newBufferedReader(path);
            this.object = gson.fromJson(reader, clazz);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<S> getObject() {
        return Optional.ofNullable(object);
    }
}
