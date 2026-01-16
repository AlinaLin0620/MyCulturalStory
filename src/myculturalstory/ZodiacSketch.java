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

public class ZodiacSketch extends PApplet {
    // initialize
    // images
    private PImage gameImg;
    private PImage selectionImg;
    private PImage leftTri;
    private PImage rightTri;
    
    // frame system
    private int gameState = 0;
    private PImage[] frames;
    private int currentFrame = 0;
    private final int TOTAL_FRAMES = 7;
    
    // player
    private ZodiacAnimal player;
    
    // screen selction variables 
    private int leftTriX = 350;
    private int rightTriX = 600;
    private int triY = 500;
    
    // character selection
    private ZodiacAnimal[][] allAnimals;
    private int selectedIndex = 0;
    private final int ROWS = 3;
    private final int COLS = 4;
    
    // villager quests
    private boolean fishing = false;
    
    
    public static void main(String[] args) {
        PApplet.main("myculturalstory.ZodiacSketch");
    }
    
    // set frame size
    public void settings() {
        size(1000, 700);
    }
    
    // set up background and player
    public void setup() {
        // background images
        selectionImg = loadImage("images/characterSelection.png");
        // selection frame images
        leftTri = loadImage("images/left_tri.png");
        rightTri = loadImage("images/right_tri.png");
        
        // initialize animal all in order
        allAnimals = new ZodiacAnimal[ROWS][COLS];
            allAnimals[0][0] = new Rat(this, width / 2 - 86, height / 2 + 54);
            allAnimals[0][1] = new Ox(this, width / 2 - 59, height / 2 + 63);
            allAnimals[0][2] = new Tiger(this, width / 2 - 60, height / 2 + 71);
            allAnimals[0][3] = new Rabbit(this, width / 2 - 69, height / 2 + 53);
            
            allAnimals[1][0] = new Dragon(this, width / 2 - 52, height / 2 + 50);
            allAnimals[1][1] = new Snake(this, width / 2 - 53, height / 2 + 73);
            allAnimals[1][2] = new Horse(this, width / 2 - 53, height / 2 + 52);
            allAnimals[1][3] = new Sheep(this, width / 2 - 58, height / 2 + 43);
            
            allAnimals[2][0] = new Monkey(this, width / 2 - 75, height / 2 + 75);
            allAnimals[2][1] = new Rooster(this, width / 2 - 47, height / 2 + 53);
            allAnimals[2][2] = new Dog(this, width / 2 - 76, height / 2 + 73);
            allAnimals[2][3] = new Pig(this, width / 2 - 61, height / 2 + 59);       
        
        // default player
        selectedIndex = 0;
        player = allAnimals[0][0];
        
        // load frame images
        frames = new PImage[TOTAL_FRAMES];
        for (int i =0; i < TOTAL_FRAMES; i++) {
            frames[i] = loadImage("images/background_" + (i+1) + ".png");
        }
    }
    
    // draw in frames
    public void draw() {
        if (gameState == 0) {
            drawStartScreen();
        } else {
            drawGameScreen();
        }
    }
    
    // start screen / frame
    private void drawStartScreen() {
        // background 
        if (selectionImg != null) {
            image(selectionImg, 0, 0, width, height);
        }
        fill(0);
        textSize(40);
        text("Choose your zodiac Animal", width / 2 - 215, 70);
        textSize(20);
        text("Press Enter to confirm selection", 370, 120);
        
        // draw triangles
        if (leftTri != null) 
            image(leftTri, leftTriX, triY);
        if (rightTri != null) 
            image(rightTri, rightTriX, triY);
        
        // character info box 
        fill(255);
        stroke(0);
        rect(370, 210, 260, 100);
        
        fill(0);
        textAlign(LEFT, TOP);
        textSize(26);
        text(getAnimalName(selectedIndex), 380, 220);
        
        textSize(16);
        text(getAnimalDescription(selectedIndex), 380, 250, 560, 50);
        
        // draw player character
        if (player != null) player.draw();
    }
    
    // game screen / frame
    private void drawGameScreen() {
        // background 
        if (frames != null && currentFrame >= 0 && currentFrame < frames.length) {
            image(frames[currentFrame], 0, 0, width, height);
        }
        
        if (player != null) {
            player.update();
            player.draw();
        }
        
        // player movement instructions
        fill(0);
        textSize(20);
        text("Press Arrow Keys to Move, Space to Jump and F to Use Special Ability", 10, 20);
        
    }
    
    private boolean isClicked(PImage img, int imgX, int imgY) {
        return mousePressed 
               && mouseX >= imgX 
               && mouseX <= imgX + img.width 
               && mouseY >= imgY 
               && mouseY <= imgY + img.height;
    }
    // key controls
    public void keyPressed() {
        // player selection
        if (gameState == 0) {
            // change frame
            if (keyCode == ENTER && player != null) {
                gameState = 1;
                
                // new start position for player 
                int START_X = 100;
                int START_Y = 370;
                
                // center the image horizontally 
                player.x = START_X - player.getWidth() / 2;
                // place animal vetically so image touches ground
                player.y = START_Y - player.getHeight();
            }
        }
        
        // enable movement in new frame
        else if (gameState == 1 && player != null) {
            // move left and right 
            if (keyCode == LEFT) {
                player.move(-5);
                
                if (currentFrame ==0 && player.x < 0) {
                    player.x = 0;
                } else if (currentFrame > 0 && player.x < 0) {
                    currentFrame--;
                    player.x = width - player.getWidth();
                }
            }
            else if (keyCode == RIGHT) {
                player.move(5);
                
                if (currentFrame == TOTAL_FRAMES - 1 && player.x > width - player.getWidth()) {
                    player.x = width - player.getWidth();
                } else if (currentFrame < TOTAL_FRAMES -1 && player.x > width - player.getWidth()) {
                    currentFrame++;
                    player.x = 0;
                }
            }
            
            // jump 
            if (key == ' ') {
                player.jump(); 
            }
            
            // special ability 
            if (key == 'f' || key == 'F') {
                player.useAbility();
            }
            
            // fishing 
            if (key == 'q' || key == 'Q') {
                player.useAbility();
            }
        }
    }
    
    // mouse controls
    public void mousePressed() {
        if (gameState == 0) {
            // check left tri
            if (isClicked(leftTri, leftTriX,triY)) {
                // move to previous animal in the index
                selectedIndex--;
                // checks if you surpass the index and brings you back to the last index
                if (selectedIndex < 0) selectedIndex = ROWS * COLS - 1;
            }
            // check right tri
            else if (isClicked(rightTri, rightTriX, triY)) {
                // move to animal in the next index
                selectedIndex++;
                // check if you surpass the index and brings you back to the first index
                if (selectedIndex >= ROWS * COLS) selectedIndex = 0;
            }
            
            int row = selectedIndex / COLS;
            int col = selectedIndex % COLS;
            player = allAnimals[row][col];
        }
    }
    
     // character names 
    private String getAnimalName(int index) {
        String[] names = {
            "Rat", "Ox", "Tiger", "Rabbit", 
            "Dragon", "Snake", "Horse", "Sheep", 
            "Moneky", "Rooster", "Dog", "Pig"
        };
        return names[index];
    }
    
    // character descriptions 
    private String getAnimalDescription(int index) {
        String[] description = {
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
            "Special Ability:",
        };
        return description[index];
    }
}