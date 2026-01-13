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
    public Dragon(PApplet app, int x, int y) {
        super(app, "dragon", x, y);
    }
    
    @Override
    public void applyBoost(){
        if (velocityY > 0) {
            velocityY *= 0.85f;
        }
    }
}
