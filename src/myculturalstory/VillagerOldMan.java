/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Old man NPC that gives player quest to find lost item
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class VillagerOldMan extends Villager {
    // variables 
    private LostPendant lostItem;

    /**
     * Constructor for old man
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     * @param lostItem lost pendant specific to this class
     */
    public VillagerOldMan(PApplet app, int x, int y, LostPendant lostItem) {
        // sets this up from parent class
        super(app, "villager_old", "vo", x, y); 
        // setups lostItem
        this.lostItem = lostItem;
    }
    /**
     * Updates old man quest logic and pendant state
     * @param player The current player object
     * @param interactPressed Represents if E key is used  
     */
    @Override 
    public void update(ZodiacAnimal player, boolean interactPressed) {
        // from parent class
        super.update(player, interactPressed);
        
        // update quest if given and isnt complete
        if (questGiven && !questComplete) {
            // keep updating the pendant
            lostItem.update(player);
            // if the pendant is collected
            if (lostItem.isCollected()) {
                // quest is completed
                questComplete = true;
            }
        }
    }
    /**
     * Shows initial dialogue
     */
    @Override 
    protected void giveQuest() {
        questGiven = true;
        showDialogue(); 
    }
    /**
     * Checks if pendant is picked up
     * @param player The current player object
     */
    @Override 
    protected void checkQuestProgress(ZodiacAnimal player) {
        // if item is collected and quest isnt complete
        if (lostItem.isCollected() && !questComplete) {
            questComplete = true; 
        }
    }
}

