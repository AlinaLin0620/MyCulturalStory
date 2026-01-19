/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Boy NPC that gives quest to collect firewood
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class VillagerBoys extends Villager {
    // variables
    // array for multiple firewood bundles
    private Firewood[] firewood;
    // coutns collect firewood
    private int collected = 0;

    /**
     * Constructor for boy
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     * @param firewood Array of firewood objects specific to this class
     */
    public VillagerBoys(PApplet app, int x, int y, Firewood[] firewood) {
        // from parent
        super(app, "villager_boy", "vb", x, y);
        // class specific object firewood set up
        this.firewood = firewood;
    }
    /**
     * Updates boys quest logic and firewood state
     * @param player The current player object
     * @param interactPressed Represents if E key is used  
     */
    @Override
    public void update(ZodiacAnimal player, boolean interactPressed) {
        // track firewood if quest is given and not complete
        if (questGiven && !questComplete) {   
            // set collect to 0
            collected = 0;
            // loops thru all firewood
            for (int i = 0; i < firewood.length; i++) {
                // if firewood is nto collected
                if (!firewood[i].isCollected()) {
                    // keep updating the firewood
                    firewood[i].update(player, player.isCollectKeyPressed());
                }
                // if it is collected
                if (firewood[i].isCollected()) {
                    // add to collected counter
                    collected++;
                }          
            }
            checkQuestProgress(player);
        }
        // from parent
        super.update(player, interactPressed);
    }
    /**
     * Shows initial dialogue
     */
    @Override
    protected void giveQuest() {
        questGiven = true;
        showDialogue(); // display start text bubble
    }
    /**
     * Checks if firewood is collected
     * @param player The current player object
     */
    @Override
    protected void checkQuestProgress(ZodiacAnimal player) {
        // if collected is greater or equal to the amount of firewood
        if (collected >= firewood.length) {
            // quest set to complete
            questComplete = true;
        }
    }
}
