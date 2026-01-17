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
    
    // speech bubble text
    protected String speechText = "";
    protected int speechTimer = 0; 
    protected static final int SPEECH_DURATION = 8000; 
    
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
    
    protected boolean isQuestionGiven() {
        return questGiven;
    }
    
    protected boolean isQuestComplete() {
        return questComplete;
    }
    
    // getters 
    public int getWidth() {
        return img.width;
    }

    public int getHeight() {
        return img.height;
    }
    
    protected void showSpeech() {
        if (speechText.isEmpty())
            return;
        
        app.fill(255);        
        app.stroke(0); 
        // rounded rectangle just above and to the left of the villager
        app.rect(x - 10, y - 50, app.textWidth(speechText) + 10, 40, 10); 

        app.fill(0);             
        app.textSize(16);
        app.textAlign(app.CENTER, app.CENTER);
        app.text(speechText, x + getWidth()/2 + 100, y - 30);
        
        // reset text alignment 
        app.textAlign(app.LEFT, app.TOP);
    }
}
