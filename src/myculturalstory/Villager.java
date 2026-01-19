/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Parent class for all NPC villagers
 * handles dialogue, collision detection and other quest related items
 * @author alina
 * @version 1.0
 * @since 2026-01-18
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
    
    /**
     * Constructs a new villager
     * @param app PApplet
     * @param villagerImg the file name of the villager characters
     * @param prefix the string prefix used to find the start and end dialogue of the specified villager
     * @param x The initial x position
     * @param y The initial y position
     */ 
    public Villager (PApplet app, String villagerImg, String prefix, int x, int y) {
        // set up vars
        this.app = app;
        this.x = x;
        this.y = y;
        // load in images
        img = app.loadImage("images/" + villagerImg + ".png");
        startTextImg = app.loadImage("images/" + prefix + "_startText.png");
        endTextImg = app.loadImage("images/" + prefix + "_endText.png");
    }
    
    /**
     * Updates villager logic, checks for interactions and handles dialogue
     * @param player The current player object
     * @param interactPressed Represents if E key is used 
     */
    public void update(ZodiacAnimal player, boolean interactPressed) {
        draw();
        
        // checks if quest has been given and gives quest
        if (touchingPlayer(player) && interactPressed) {
            // if quest no given
            if (!questGiven) {
                // give quest and show dialogue
                giveQuest();
                showDialogue();
             
            // checks if quest has been completed and give reward + shows dialogue
            } else if (questComplete && !rewardGiven) {
                giveReward();
                showDialogue();
            }         
        }
        // While quest is active and not complete check if requirements are met
        if (questGiven && !questComplete) {
            checkQuestProgress(player);
        }
        // Update to check if orb has been given or not
        if (goodDeedOrb != null && !goodDeedOrb.isCollected()) {
            goodDeedOrb.update(player, app);
        }
        
        // display dialogue 
        displayDialogue();
    }
    
    public void draw() {
        app.image(img, x, y);
    }
    /**
     * Determines if player is within the villagers boundaries
     * @param player The current player object
     * @return true if the images overlap
     */
    protected boolean touchingPlayer (ZodiacAnimal player) {
        // checks if player x/y boundaries touch with the images x/y boundaries
        return player.getX() + player.getWidth() > x &&
               player.getX() < x + img.width &&
               player.getY() + player.getHeight() > y &&
               player.getY() < y + img.height;
    }
    /**
     * Initialize the quest state to be true
     */
    protected void giveQuest() {
        questGiven = true;
    }
    /**
     * Method to be overwritten for child class specification
     * @param player Checks agaisnt player 
     */
    protected void checkQuestProgress(ZodiacAnimal player) {
        
    }
    /**
     * Completes the quest, spawns the orb and increased villagers saved counter
     */
    protected void giveReward() {
        // set reward given to true
        rewardGiven = true;
        // spawn in orb
        goodDeedOrb = new GoodDeed(app, x + img.width + 50, y + 50);
        
        // count number of villagers save 
        if (app instanceof ZodiacSketch) {
            ((ZodiacSketch)app).countVillagersSaved();
        }
    }
    /**
     * activates the visibility of the dialogue images
     */
    protected void showDialogue() {
        showDialogue = true;
        // start dialogue time
        dialogueStartTime = app.millis();
    }
    /**
     * Handles the visual display and timing of the dialogue
     */
    protected void displayDialogue() {
        // checks the dialogue is suppose to be shown
        if (!showDialogue)
            return;
        // auto hides dialogue after the dialogue duration has passed
        // takes current time - time dialogue starts and if its greater than the duration we close dialogue
        if (app.millis() - dialogueStartTime > DIALOGUE_DURATION) {
            showDialogue = false;
            return;
        }
        // if quest complete
        if (questComplete) {
            // show end text img
            imgToShow = endTextImg;
        } else {
            // otherwiseshow start text img 
            imgToShow = startTextImg;
        }
        app.image(imgToShow, x + img.width / 2 - imgToShow.width / 2 + 60, y - imgToShow.height - 10);
    }
}
