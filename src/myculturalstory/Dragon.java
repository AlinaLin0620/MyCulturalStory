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
// dragon class
public class Dragon extends ZodiacAnimal {
    // varibale
    private boolean extraJump = false;
    public Dragon(PApplet app, int x, int y) {
        super(app, "dragon", x, y);
    }
    
    // double jump
    @Override
    public void jump() {
        if (onGround && !stunned) {
            velocityY = JUMP_HEIGHT;
            extraJump = boostActive;
            onGround = false;
        } 
        else if (boostActive && extraJump) {
            velocityY = JUMP_HEIGHT;
            extraJump = false;
        }
    }
}
