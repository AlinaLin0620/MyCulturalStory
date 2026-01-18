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

public class ZodiacAnimal {
    // initialize varriables
    protected PApplet app;
    protected PImage image;
    protected String name;
    protected int x;
    protected int y;
    protected boolean hasGoodDeed = false;
    
    // physics
    protected float velocityY = 0;
    protected final float GRAVITY = 0.8f;
    protected final float JUMP_HEIGHT = -12;
    protected boolean onGround = false;
    protected final int GROUND_Y = 400;
    
    // boost system
    protected boolean boostActive = false;
    protected int boostStartTime = 0;
    protected static final int BOOST_DURATION = 15000;
    
    // stun system 
    protected boolean stunned = false;
    protected int stunStartTime;
    protected static final int STUN_DURATION = 3000;
    
    // quest system
    protected boolean fishingKeyPressed = false;
    private boolean collectKeyPressed = false;
    
    // zodiac animal constructor
    public ZodiacAnimal(PApplet app, String name, int x, int y) {
        this.app = app;
        this.name = name;
        this.x = x;
        this.y = y;
        
        image = app.loadImage("images/" + name + ".png");
    }
    
    // draw in images
    public void draw() {
        app.image(image, x, y);
        drawBoostBar();
        drawStunIndicator();
    }
    
    public void update() {
        // physics
        velocityY += GRAVITY;
        y += velocityY;
        
        // stop at ground 
        if (y >= GROUND_Y) {
            y = GROUND_Y;
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }
        
        // check boost timer
        if (boostActive) {
            // learned millis from PApplet documentation and it returns number of milliseconds since being activated
            if (app.millis() - boostStartTime >= BOOST_DURATION) {
                boostActive = false;
                hasGoodDeed = false;
                onBoostEnd();
            }
        }
        
        // stun system
        updateStun();
    }
    
    // jump method
    public void jump() {
        if (onGround && !stunned) {
            velocityY = JUMP_HEIGHT;
            onGround = false;
        }
    }
    
    public void receiveGoodDeed(GoodDeed deed) {
        hasGoodDeed = true;
    }
    
    // boost/ability methods
    public void useAbility() {
        if (hasGoodDeed && !boostActive) {
            boostActive = true;
            boostStartTime = app.millis();
            applyBoost();
        }
    }
    
    public void applyBoost() {
        // default is nothing
    }
    
    public void onBoostEnd() {
        // default is nothing
    }
    
    // stun methods
    public void stun() {
        if (!stunned) {
            stunned = true;
            stunStartTime = app.millis();
            velocityY = 0;
        }
    }
    
    public void updateStun() {
        if (stunned) {
            int elapsed = app.millis() - stunStartTime;
            if (elapsed >= STUN_DURATION){
                stunned = false;
            }
        }
    }
    
    public boolean isStunned() {
        return stunned;
    }
    
    public void move(int dx) {
        if (stunned)
            return;
        if (boostActive) {
            x += dx * 2;
        } else {
            x += dx;
        }
    }
    
    // draw boost bar
    private void drawBoostBar() {
        if (!boostActive)
            return;
        
        int elapsed = app.millis() - boostStartTime;
        float percent = 1 - (float) elapsed / ZodiacAnimal.BOOST_DURATION;
            
            // background 
            app.fill(0);
            app.rect(30, 50, 200, 20);
            
            // timer bar
            app.fill(0, 200, 0);
            app.rect(30, 40, 200 * percent, 20);
            
            app.fill(0);
            app.text("Boost Active", 30, 40);
        }
    
    // draw stun indicator 
    private void drawStunIndicator() {
        if(!stunned)
            return;
        
        app.fill(0);
        app.rect(870, 20, 85, 25);
            
        app.fill(255, 0, 0);
        app.textSize(20);
        app.text("STUNNED", 870, 40);
    }
    
    // fishing input keys
    public void setFishingKey(boolean state) {
        fishingKeyPressed = state;
    }
    
    public boolean isFishingKeyPressed() {
        return fishingKeyPressed;
    }
    
    // collect firewood key
    public void setCollectKey (boolean state) {
        collectKeyPressed = state;
    }
    
    public boolean isCollectKeyPressed() {
        return collectKeyPressed;
    }
    
    public void resetStatus() {
        this.boostActive = false;
        this.hasGoodDeed = false;
        this.onBoostEnd();
        this.stunned = false;
    
        this.velocityY = 0;
    
        this.fishingKeyPressed = false;
        this.collectKeyPressed = false;
        
    }
    
    // getters
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return image.width;
    }
    
    public int getHeight() {
        return image.height;
    }
}
