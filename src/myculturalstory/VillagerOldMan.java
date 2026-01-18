/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 *
 * @author alina
 */
// imports
import processing.core.PApplet;

public class VillagerOldMan extends Villager {
    // variables 
    private LostPendant lostItem;

    // constructor
    public VillagerOldMan(PApplet app, int x, int y, LostPendant lostItem) {
        super(app, "villager_old", "vo", x, y); 
        this.lostItem = lostItem;
    }

    @Override 
    public void update(ZodiacAnimal player, boolean interactPressed) {
        super.update(player, interactPressed);
        
        // update quest if given
        if (questGiven && !questComplete) {
            lostItem.update(player);
            if (lostItem.isCollected()) {
                questComplete = true;
            }
        }
    }

    @Override 
    protected void giveQuest() {
        questGiven = true;
        showDialogue(); 
    }

    @Override 
    protected void checkQuestProgress(ZodiacAnimal player) {
        if (lostItem.isCollected() && !questComplete) {
            questComplete = true; 
        }
    }
}

