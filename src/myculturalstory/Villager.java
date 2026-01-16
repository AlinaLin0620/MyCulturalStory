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
    
    protected boolean questGiven = false;
    protected boolean questComplete = false;
    protected boolean rewardGiven = false;
    
    // constructor 
    public Villager (PApplet app, String imgName, int x, int y) {
        this.app = app;
        this.x = x;
        this.y = y;
        img = app.loadImage("images/" + imgName + ".png");
    }
    
    //
    public void update(ZodiacAnimal player, boolean interactPressed) {
        draw();
        
        // checks if quest has been given and gives quest
        if (touchingPlayer(player) && interactPressed) {
            if (!questGiven) {
                giveQuest();
            } 
            // checks if quest has been completed and give reqard
            else if (questComplete && !rewardGiven) {
                giveReward(player);
            }
        }
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
    
    protected void giveReward(ZodiacAnimal player) {
        rewardGiven = true;
        // gives orb
        player.receiveGoodDeed(null);
    }
    
    public boolean isQuestionGiven() {
        return questGiven;
    }
    
    public boolean isQuestComplete() {
        return questComplete;
    }
    
}
