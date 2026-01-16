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

public class villagerGirl extends Villager { 
    // variables 
    private Fish fish;
    private boolean fishCaught = false;
    
    // constructor 
    public villagerGirl(PApplet app, int x, int y, Fish fish) {
        super(app, "villager_girl", x, y);
        this.fish = fish;
    }
    
    @Override
    public void update(ZodiacAnimal player, boolean interactPressed) {
        super.update(player, interactPressed);
        
        if (questGiven && !questComplete) {
            fish.update(player, interactPressed);
            if (fish.isCaught()) {
                fishCaught = true;
                questComplete = true;
            }
        }
    }
    
    @Override 
    protected void giveQuest() {
        questGiven = true;
        System.out.println("Villager Girl: Please catch me a fish!");
    }
    
    @Override 
    protected void checkQuestProgress(ZodiacAnimal player) {
        if (fish.isCaught()) {
            questComplete = true;
            System.out.println("Fish caught! Return to the Villager Girl.");
        }
    }
}
