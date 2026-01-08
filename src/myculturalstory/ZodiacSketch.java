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
        // default player as rat
        player = new Rat(this, 100, 300);
    }
    
    public void draw() {
        background(200);
        player.draw();
        
        if (player != null) {
            player.draw();
        }
        
        displayInstructions();
    }
    
    private void displayInstructions() {
        fill(0);
        textSize(12);
        text("Select Zodiac Animal:", 10, 20);
        text("1-Rat  2-Ox  3-Tiger  4-Rabbit  5-Dragon  6-Snake  7-Horse  8-Sheep  9-Monkey  Q-Rooster  W-Dog  E-Pig", 10, 40);
        text("Arrow Keys to Move", 10, 60);
    }
    
    public void keyPressed() {
        //player selection
        if (key =='1') player = new Rat(this, 100, 300);
        else if (key == '2') player = new Ox(this, 100, 300);
        else if (key == '3') player = new Tiger(this, 100, 300);
        else if (key == '4') player = new Rabbit(this, 100, 300);
        else if (key == '5') player = new Dragon(this, 100, 300);
        else if (key == '6') player = new Snake(this, 100, 300);
        else if (key == '7') player = new Horse(this, 100, 300);
        else if (key == '8') player = new Sheep(this, 100, 300);
        else if (key == '9') player = new Monkey(this, 100, 300);
        else if (key == 'q' || key == 'Q') player = new Rooster(this, 100, 300);
        else if (key == 'w' || key == 'W') player = new Dog(this, 100, 300);
        else if (key == 'e' || key == 'E') player = new Pig(this, 100, 300);
        
        // movement
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