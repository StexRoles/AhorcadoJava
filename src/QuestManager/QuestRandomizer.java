package QuestManager;

import java.util.ArrayList;
import java.util.Random;

import Popup.PopupFrame;
import model.Quest;

/*
 * Clase para aleatorizar una pregunta
 */

public class QuestRandomizer {
    public QuestManager manager = new QuestManager();

    // elije una pregunta aleatoria de QuestManager
    public Quest selectRandomQuest() {
        ArrayList<Quest> questItems = manager.ReadFile();

        if (questItems.isEmpty()) {
            new PopupFrame("Error", "No quests found!");
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(questItems.size());
        return questItems.get(randomIndex);
    }

}