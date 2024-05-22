package abstracts;

import java.util.ArrayList;

import model.Quest;

public interface FileWriterInterface {
    boolean WriteFile(Quest q);
    ArrayList<Quest> ReadFile();
    void addWord(String word, String hint);
    void removeWord(String word);
}