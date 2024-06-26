package QuestManager;

import model.Quest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import Constants.Constants;
import Popup.PopupFrame;
import abstracts.*;

public class RealFileWriter implements FileWriterInterface {
    // write a new quest on the file
    // if q = null, resets the file

    @Override
    public boolean WriteFile (Quest q){
        try{
            File file = new File(Constants.QUESTS_PATH);

            if(q == null){
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("");
                writer.close();
                return true;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String s = q.toString();
            writer.write(s);
            writer.close();
            return true;
        }
        catch(IOException e) {
            new PopupFrame("Error", "An error occurred while writing on the quests file:" + e.getMessage());
            return false;
        }
    }

    // read the file an returns an array of quests
    public ArrayList<Quest> ReadFile(){
        ArrayList<Quest> questItems = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.QUESTS_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String clue = parts[1].trim();
                    Quest quest = new Quest(word, clue);
                    questItems.add(quest);
                }
            }
        } catch (IOException e) {
            new PopupFrame("Error", "An error occurred while reading the quests file: " + e.getMessage());
            return null;
        }
        return questItems;
    }

    // add a quest to quests.txt
    public void addWord(String word, String hint){
        try{
            //remove invalid characters
            word = word.replaceAll("[^A-Za-z\\s]", "");
            word = word.trim();
            word = word.replaceAll("\\s+", " ");

            hint = hint.replaceAll("[^A-Za-z\\s]", "");
            hint = hint.trim();
            hint = hint.replaceAll("\\s+", " ");

            //catch errors
            if(word.equals("")){
                new PopupFrame("Error", "Write a word.");
            }
            else if(hint.equals("")){
                new PopupFrame("Error", "Write a hint.");
            }
            else if(findLine(this.ReadFile(), word) != -1){
                new PopupFrame("Error", "Word already added.");
            }

            //add the quest
            else{
                this.WriteFile(new Quest(word, hint));
                new PopupFrame("", "Quest has been added.");
            }
        }

        //error message
        catch(Exception e) {
            new PopupFrame("Error", "Some unexpected error occurred adding a Quest:\n" + e.getMessage());
        }
    }

    // remove a quest from quests.txt
    public void removeWord(String word){
        try{
            ArrayList<Quest> quests = this.ReadFile();

            //remove invalid characters
            word = word.replaceAll("[^A-Za-z\\s]", "");
            word = word.trim();
            word = word.replaceAll("\\s+", " ");
            
            //find the word line
            int line = findLine(quests, word);
            
            //catch errors
            if(word.equals("")){
                new PopupFrame("Error", "Write a word.");
            }
            else if(line == -1){
                new PopupFrame("Error", "Word not found.");
            }

            //remove the line with the given word
            else{
                this.WriteFile(null);
                for(Quest q : quests){
                    if(!q.getWord().equals(word)){
                        this.WriteFile(q);
                    }
                }
    
                new PopupFrame("", "Quest has been removed.");
            }

        }

        //error message
        catch(Exception e) {
            new PopupFrame("", "Some unexpected error occurred removing a Quest:\n" + e.getMessage());
        }
    }

    //find the line with a given word in quests.txt
    public static int findLine(ArrayList<Quest> quests, String word){
        int line = -1;
        boolean ok = false;
        
        //try to find the word
        for(Quest q : quests){
            String w = q.getWord(); line ++;

            if(word.equals(w)){
                ok = true;
                break;
            }
        }

        if (ok) return line;
        else return -1;
    }
}