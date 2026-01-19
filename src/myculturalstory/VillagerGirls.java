/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Girl NPC that gives quest to catch fish
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class VillagerGirls extends Villager { 
    // variables 
    private Fish fish;
    // checks if fish is caught or not
    private boolean fishCaught = false;

    /**
     * Constructor for girl
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     * @param fish  the fish object specific to this class
     */
    public VillagerGirls(PApplet app, int x, int y, Fish fish) {
        // from parent class
        super(app, "villager_girl", "vg", x, y); 
        // class specific object fish set up
        this.fish = fish;
    }
    /**
     * Updates girls quest logic and fish state
     * @param player The current player object
     * @param interactPressed Represents if E key is used  
     */
    @Override
    public void update(ZodiacAnimal player, boolean interactPressed) {
        // from parent class
        super.update(player, interactPressed);
        // if quest is given and not complete
        if (questGiven && !questComplete) {
            // keep updating the fish
            fish.update(player, interactPressed);
            // if fish is caught and quest is not complete
            if (fish.isCaught() && !questComplete) {
                // set fish as caught and quest complete
                fishCaught = true;
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
     * Checks if fish is caught
     * @param player The current player object
     */
    @Override 
    protected void checkQuestProgress(ZodiacAnimal player) {
        // if fish caught and quest not completed
        if (fish.isCaught() && !questComplete) {
            // set quest as completed
            questComplete = true; 
        }
    }
}
