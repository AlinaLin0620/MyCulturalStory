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
import processing.core.PImage;

public class Villager {
    // variables 
    protected int x, y;
    protected PImage img;
    protected PApplet app;
    
    // quest system
    protected boolean questGiven = false;
    protected boolean questComplete = false;
    protected boolean rewardGiven = false;
    protected GoodDeed goodDeedOrb;
    
    // speech bubble text
    protected PImage startTextImg;
    protected PImage endTextImg;
    protected PImage imgToShow;
    protected boolean showDialogue = false;
    protected int dialogueStartTime = 0; 
    protected static final int DIALOGUE_DURATION = 4000; 
    
    // constructor 
    public Villager (PApplet app, String villagerImg, String prefix, int x, int y) {
        this.app = app;
        this.x = x;
        this.y = y;
        img = app.loadImage("images/" + villagerImg + ".png");
        startTextImg = app.loadImage("images/" + prefix + "_startText.png");
        endTextImg = app.loadImage("images/" + prefix + "_endText.png");
    }
    
    //
    public void update(ZodiacAnimal player, boolean interactPressed) {
        draw();
        
        // checks if quest has been given and gives quest
        if (touchingPlayer(player) && interactPressed) {
            if (!questGiven) {
                giveQuest();
                showDialogue();
             
            // checks if quest has been completed and give reqard
            } else if (questComplete && !rewardGiven) {
                giveReward();
                showDialogue();
            }         
        }
        if (questGiven && !questComplete) {
            checkQuestProgress(player);
        }
        if (goodDeedOrb != null && !goodDeedOrb.isCollected()) {
            goodDeedOrb.update(player, app);
        }
        
        // display dialogue 
        displayDialogue();
    }
    
    public void draw() {
        app.image(img, x, y);
    }
    
    protected boolean touchingPlayer (ZodiacAnimal player) {
        return player.getX() + player.getWidth() > x &&
               player.getX() < x + img.width &&
               player.getY() + player.getHeight() > y &&
               player.getY() < y + img.height;
    }
   
    protected void giveQuest() {
        questGiven = true;
    }
    
    protected void checkQuestProgress(ZodiacAnimal player) {
        
    }
    
    protected void giveReward() {
        rewardGiven = true;
        // spawn in orb
        goodDeedOrb = new GoodDeed(app, x + img.width + 50, y + 50);
        
        // count number of villagers save 
        if (app instanceof ZodiacSketch) {
            ((ZodiacSketch)app).countVillagersSaved();
        }
    }
    
    protected void showDialogue() {
        showDialogue = true;
        dialogueStartTime = app.millis();
    }
    
    protected void displayDialogue() {
        if (!showDialogue)
            return;

        if (app.millis() - dialogueStartTime > DIALOGUE_DURATION) {
            showDialogue = false;
            return;
        }
        
        if (questComplete) {
            imgToShow = endTextImg;
        } else {
            imgToShow = startTextImg;
        }
        app.image(imgToShow, x + img.width / 2 - imgToShow.width / 2, y - imgToShow.height - 10);
    }
}
