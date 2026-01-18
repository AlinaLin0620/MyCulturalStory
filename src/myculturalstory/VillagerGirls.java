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

public class VillagerGirls extends Villager { 
    // variables 
    private Fish fish;
    private boolean fishCaught = false;

    // constructor 
    public VillagerGirls(PApplet app, int x, int y, Fish fish) {
        super(app, "villager_girl", "vg", x, y); 
        this.fish = fish;
    }

    @Override
    public void update(ZodiacAnimal player, boolean interactPressed) {
        super.update(player, interactPressed);
        
        if (questGiven && !questComplete) {
            fish.update(player, interactPressed);
            if (fish.isCaught() && !questComplete) {
                fishCaught = true;
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
        if (fish.isCaught() && !questComplete) {
            questComplete = true; 
        }
    }
}
