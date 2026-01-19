/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Parent class of animal characters, handles loading in image, giving special abilities, stuns, etc
 * @author alina
 * @version 1.0
 * @since 2026-01-18
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
    protected static final int STUN_DURATION = 2000;
    protected boolean immune = false;
    protected static final int IMMUNITY_DURATION = 1000;
    protected int stunCount = 0;
    
    // quest system
    protected boolean fishingKeyPressed = false;
    private boolean collectKeyPressed = false;
    
    /**
     * Constructor to initialize zodiac animal w/ name and starting position
     * @param app PApple variable for drawing 
     * @param name The name of the animal which is used to load in the image file
     * @param x Starting Horizontal position
     * @param y Starting vertical position
     */
    public ZodiacAnimal(PApplet app, String name, int x, int y) {
        // set up each variable
        this.app = app;
        this.name = name;
        this.x = x;
        this.y = y;
        // load in specific image based on name var
        image = app.loadImage("images/" + name + ".png");
    }
    
    // draw in images
    public void draw() {
        // draw image
        app.image(image, x, y);
        // call on methods to draw boost bar and stun indicator
        drawBoostBar();
        drawStunIndicator();
    }
    /**
     * Updates physics, gravity and timer logic
     */
    public void update() {
        // physics
        // apply gravity to vertical velocity
        velocityY += GRAVITY;
        // move animal based on current velocity
        y += velocityY;
        
        // stop at ground 
        if (y >= GROUND_Y) {
            // bring char back to ground
            y = GROUND_Y;
            // stops animal falling
            velocityY = 0;
            // allows jumping again
            onGround = true;
        } else {
            // when player is in the middle of the air
            onGround = false;
        }
        
        // check boost timer
        if (boostActive) {
            // learned millis from PApplet documentation and it returns number of milliseconds since being activated
            if (app.millis() - boostStartTime >= BOOST_DURATION) {
                // turns boost off
                boostActive = false;
                // sets the the dedd used for boost off
                hasGoodDeed = false;
                // specific items cleaned up for specific animal boosts
                onBoostEnd();
            }
        }
        
        // stun system
        updateStun();
    }
    
    /**
     * Makes animal jump if on ground and not stunned
     */
    public void jump() {
        // checks if animal is on ground and not stunned
        if (onGround && !stunned) {
            // allows upward force at to the heigh of the constant variable
            velocityY = JUMP_HEIGHT;
            // set player to be off the ground
            onGround = false;
        }
    }
    
    /**
     * Allows the animal a "good deed orb" from the villager to unlock their special ability 
     * @param deed 
     */
    public void receiveGoodDeed(GoodDeed deed) {
        // unlocks special ability for animals
        hasGoodDeed = true;
    }
    
    /**
     * activates the special ability of the player if conditions are met
     */
    public void useAbility() {
        // checks that player has good deed and doesnt have boost already active
        if (hasGoodDeed && !boostActive) {
            // turns on boost
            boostActive = true;
            // sets the start time
            boostStartTime = app.millis();
            // runs the specific boost logic for each animal
            applyBoost();
        }
    }
    /**
     * Overridable method for specific animal boost
     */
    public void applyBoost() {
        // default is nothing
    }
    /**
     * Overridable method for specific animal cleanup after boost
     */
    public void onBoostEnd() {
        // default is nothing
    }
    
    /**
     * Triggers stun state when player hits obstacle
     */
    public void stun() {
        // checks that player isnt stunned or in immune period
        if (!stunned && !immune) {
            // sets stun state to be enabled
            stunned = true;
            // records the time you are stunned
            stunStartTime = app.millis();
            // stops jump if hit mid air
            velocityY = 0;
            // adds to stun counter for end screen
            stunCount++;
        }
    }
    /**
     * Manages the time for stun and the post stun immunity
     * Once again PApplet documentation was used to learn about how to use millis()
     * and asked AI "how can I incorperate time in my game like a time duration 
     * for stunning or to have a time tracker with PApplet and PImage imports"
     * to find out about millis() and find the doucmentation
     * link to documentation https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html
     */
    public void updateStun() {
        if (stunned) {
            // checks if stun time is over by subtracting how long stuns should last from how long the stun has been going for
            if (app.millis() - stunStartTime >= STUN_DURATION) {
                // turns stun off
                stunned = false;
                // start immunity period
                immune = true;
                
                // reset timer for immunity check
                stunStartTime = app.millis();
            }
        // checks if immunity time is over by subtracting how long its been going from when it started
        } else if (immune && (app.millis() - stunStartTime >= IMMUNITY_DURATION)) {
            // sets immunity off
            immune = false;
        }
    }
    /**
     * Checks if the animal is currently stunned
     * @return returns true if stunned
     */
    public boolean isStunned() {
        return stunned;
    }
    /**
    * Moves the animal left and right
    * @param dx How much to move (neg or pos for left and right)
    */
    public void move(int dx) {
        // doesn't move is player is stunned
        if (stunned)
            return;
        // moves left or right depending on dx value 
            x += dx;
    }
    
    /**
     * draws in a boost bar indicator 
     */
    private void drawBoostBar() {
        // checks if boost isnt active
        if (!boostActive)
            // returns nothing as there is no boost 
            return;
        // calculates time paseed
        int elapsed = app.millis() - boostStartTime;
        // gets the bar percentage by taking time that has passed divided by total time and subtract it from a whole (1)
        float percent = 1 - (float) elapsed / ZodiacAnimal.BOOST_DURATION;
            
            // background 
            // fill background bar as black
            app.fill(0);
            // draw the box
            app.rect(30, 50, 200, 20);
            
            // timer bar
            // turns bar green
            app.fill(0, 200, 0);
            /** draws the bar to have same width as black background but shrinks 
             * becuase of the width being multipled by the precent
             * Also shifts the bar slightly lower compared to background
             */
            app.rect(30, 40, 200 * percent, 20);
            
            // black text
            app.fill(0);
            // shows text stating the bars are displaying the boost timer
            app.text("Boost Active", 30, 40);
        }
    
    /**
     * draws in stun indicator
    */
    private void drawStunIndicator() {
        // checks if player is not stunned and doesn't draw
        if(!stunned)
            return;
        
        // black background
        app.fill(0);
        // rectangle at top left area
        app.rect(30, 80, 85, 25);
        
        // red text
        app.fill(255, 0, 0);
        // text size
        app.textSize(20);
        // text placement insdie of background
        app.text("STUNNED", 30, 100);
    }
    
    /**
     * Updates the status of the fishing key
     * @param state is true if pressed
     */
    public void setFishingKey(boolean state) {
        // updates the fishing state
        fishingKeyPressed = state;
    }
    /**
     * Checks if fishing key is pressed
     * @return the state of the fishing key (pressed or not pressed)
     */
    public boolean isFishingKeyPressed() {
        // returns the status of the key
        return fishingKeyPressed;
    }
    
    /**
     * Updates the status of the collection key
     * @param state is true if pressed
     */
    public void setCollectKey (boolean state) {
        // updates wood collection state
        collectKeyPressed = state;
    }
    /**
     * Checks if collection key is pressed
     * @return  the state of the collection key (pressed or not)
     */
    public boolean isCollectKeyPressed() {
        // returns the status of the key
        return collectKeyPressed;
    }
    /**
     * Fully resets animal stats
     */
    public void resetStatus() {
        // set boost and deed to false (turn off)
        this.boostActive = false;
        this.hasGoodDeed = false;
        // ends boost if it was active
        this.onBoostEnd();
        // resets the stun count to 0
        this.stunCount = 0;
        // sets stun and immunity to false (turn off)
        this.stunned = false;
        this.immune = false;
        // resets falling speed
        this.velocityY = 0;
        // resets key inputs
        this.fishingKeyPressed = false;
        this.collectKeyPressed = false;
        
    }
    
    // getters
    /**
     *  Gets the number of stuns for end screen
     * @return stunCount how many times the player gets stunned
    */
    public int getStunCount() {
        return stunCount;
    }
    /**
     * Gets the X position
     * @return x the horizontal position 
     */
    public int getX() {
        return x;
    }
    /**
     * Gets the Y position
     * @return y the vertical position 
     */
    public int getY() {
        return y;
    }
    /**
     * Gets the player width
     * @return image.width the width of player 
     */
    public int getWidth() {
        return image.width;
    }
    /**
     * Gets the player height
     * @return image.height the height of player 
     */
    public int getHeight() {
        return image.height;
    }
}
