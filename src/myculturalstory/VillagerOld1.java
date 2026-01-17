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

public class VillagerOld extends Villager {
    // variables 
    private LostItem lostItem;

    // constructor
    public VillagerOld(PApplet app, int x, int y, LostItem lostItem) {
        super(app, "villager_old", "vo", x, y); 
        this.lostItem = lostItem;
    }

    @Override 
    public void update(ZodiacAnimal player, boolean interactPressed) {
        super.update(player, interactPressed);
        
        // update quest if given
        if (questGiven && !questComplete) {
            lostItem.update(player);
            checkQuestProgress(player);
        }

        // display dialogue bubble
        displayDialogue();
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
            showDialogue(); 
            giveReward();   
        }
    }
}
