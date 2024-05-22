package QuestManager;

import java.util.ArrayList;

import abstracts.*;
import model.Quest;

public class FileWriterProxy implements FileWriterInterface {
    private RealFileWriter realFileWriter;

    public FileWriterProxy() {
        this.realFileWriter = new RealFileWriter();
    }

    public boolean WriteFile(Quest q) {
        // Aquí puedes controlar el acceso al RealFileWriter
        // Por ejemplo, podrías agregar lógica para verificar si q es null
        // antes de delegar la llamada al RealFileWriter
        return realFileWriter.writeFile(q);
    }

    public ArrayList<Quest> ReadFile() {
        return realFileWriter.ReadFile();
    }
}
