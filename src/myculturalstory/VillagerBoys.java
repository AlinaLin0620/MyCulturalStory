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

public class VillagerBoys extends Villager {
    // variables
    private Firewood[] firewood;
    private int collected = 0;

    // constructor
    public VillagerBoys(PApplet app, int x, int y, Firewood[] firewood) {
        super(app, "villager_boy", "vb", x, y);
        this.firewood = firewood;
    }

    @Override
    public void update(ZodiacAnimal player, boolean interactPressed) {
        // track firewood if quest is active
        if (questGiven && !questComplete) {   
            collected = 0;
            
            for (int i = 0; i < firewood.length; i++) {
                if (!firewood[i].isCollected()) {
                    firewood[i].update(player);
                }
                if (firewood[i].isCollected()) {
                    collected++;
                }          
            }
            checkQuestProgress(player);
        }
        super.update(player, interactPressed);
    }

    @Override
    protected void giveQuest() {
        questGiven = true;
        showDialogue(); // display start text bubble
    }

    @Override
    protected void checkQuestProgress(ZodiacAnimal player) {
        if (collected >= firewood.length) {
            questComplete = true;
        }
    }
}
