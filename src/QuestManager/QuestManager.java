package QuestManager;

import java.util.ArrayList;
import Game.IOFiles_Interface;
import model.Quest;


public class QuestManager implements IOFiles_Interface <Quest> {
    private FileWriterProxy fileWriterProxy;

    public QuestManager() {
        this.fileWriterProxy = new FileWriterProxy();
    }

    public boolean WriteFile(Quest q) {
        return fileWriterProxy.WriteFile(q);
    }

    public ArrayList<Quest> ReadFile() {
        return fileWriterProxy.ReadFile();
    }

    public void addWord(String word, String hint){
        fileWriterProxy.addWord(word, hint);
    }
    
    public void removeWord(String word){
        fileWriterProxy.removeWord(word);
    }
}
