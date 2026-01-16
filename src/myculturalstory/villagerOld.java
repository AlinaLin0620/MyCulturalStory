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

public class villagerOld extends Villager {
    // variables 
    private lostItem lostitem;
    
    // constructor
    public villagerOld(PApplet app, int x, int y, lostItem lostitem) {
        super(app, "villager_old", x, y);
        this.lostitem = lostitem;
    }
    
    @Override 
    public void update(ZodiacAnimal player, boolean interactPressed) {
        super.update(player, interactPressed);
        
        // update quest if given
        if (questGiven && !questComplete) {
            lostitem.update(player);
            checkQuestProgress(player);
        }
    }
    
    @Override 
    protected void giveQuest() {
        questGiven = true;
        System.out.println("Villager Old: I've lost something important. Please find it");
    }
    
    @Override 
    protected void checkQuestProgress(ZodiacAnimal player) {
        if (lostitem.isCollected()) {
            questComplete = true;
            System.out.println("Item found! Return to the Villager Old.");
        }
    }
}
