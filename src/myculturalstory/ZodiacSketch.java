/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myculturalstory;

/**
 *
 * @author alina
 */
import processing.core.PApplet;
public class ZodiacSketch extends PApplet {
    
    ZodiacAnimal player;

    public static void main(String[] args) {
        PApplet.main("myculturalstory.ZodiacSketch");
    }
    public void settings() {
        size(800, 600);
    }
    public void setup() {
        // testing
        player = new ZodiacAnimal(this, "dog", 100, 300);
    }
    public void draw() {
        background(200);
        player.draw();
    }
    public void keyPressed() {
        if (keyCode == LEFT) {
            player.move(-5, 0);
        } else if (keyCode == RIGHT) {
            player.move(5, 0);
        } else if (keyCode == UP) {
            player.move(0, -5);
        } else if (keyCode == DOWN) {
            player.move(0, 5);
        }
    }
}