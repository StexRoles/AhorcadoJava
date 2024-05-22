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
        if (q == null) {
            throw new IllegalArgumentException("Quest cannot be null");
        }

        return realFileWriter.WriteFile(q);
    }

    public ArrayList<Quest> ReadFile() {
        return realFileWriter.ReadFile();
    }

    public void addWord(String word, String hint) {
        realFileWriter.addWord(word, hint);
    }

    public void removeWord(String word){
        realFileWriter.removeWord(word);
    }   
}
