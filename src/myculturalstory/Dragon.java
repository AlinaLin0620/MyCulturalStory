/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 * Dragon child class
 * Special ability: Double jump
 * @author alina
 * @version 1.0
 * @since 2026-01-18
 */
// imports
import processing.core.PApplet;

public class Dragon extends ZodiacAnimal {
    // variables
    private boolean extraJump = false;
    
    /**
     * Constructs a Dragon animal character
     * @param app PApplet
     * @param x The initial x position
     * @param y The initial y position
     */
    public Dragon(PApplet app, int x, int y) {
        // from parents
        super(app, "dragon", x, y);
    }
    
    /**
     * Override jump to allow for double jump when boosted
     */
    @Override
    public void jump() {
        // if on ground and not stunned
        if (onGround && !stunned) {
            // standard jump logic from parent
            super.jump();
            
            // allows double jump 
            extraJump = boostActive;
        } 
        // logic for double jump
        // if boost is active and extrajump = flase
        else if (boostActive && extraJump) {
            // reset jumpforce
            velocityY = JUMP_HEIGHT;
            // set extra jump to false
            extraJump = false;
        }
    }
}
