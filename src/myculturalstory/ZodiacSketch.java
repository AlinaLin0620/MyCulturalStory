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
    // initialize variables
    private PApplet app;
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
    private VillagerBoy villagerB;
    private VillagerOld villagerO;
    private VillagerGirl villagerG;
    
    // quest objects 
    private Firewood[] firewood;
    private Fish fish;
    private LostItem lostItem;
    
    // input for quest 
    private boolean interactPressed = false;
    
    
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
            allAnimals[0][0] = new Rat(this, width / 2 - 72, height / 2 + 85);
            allAnimals[0][1] = new Ox(this, width / 2 - 53, height / 2 + 83);
            allAnimals[0][2] = new Tiger(this, width / 2 - 55, height / 2 + 87);
            allAnimals[0][3] = new Rabbit(this, width / 2 - 57, height / 2 + 83);
            
            allAnimals[1][0] = new Dragon(this, width / 2 - 43, height / 2 + 83);
            allAnimals[1][1] = new Snake(this, width / 2 - 48, height / 2 + 88);
            allAnimals[1][2] = new Horse(this, width / 2 - 44, height / 2 + 83);
            allAnimals[1][3] = new Sheep(this, width / 2 - 44, height / 2 + 85);
            
            allAnimals[2][0] = new Monkey(this, width / 2 - 70, height / 2 + 85);
            allAnimals[2][1] = new Rooster(this, width / 2 - 38, height / 2 + 84);
            allAnimals[2][2] = new Dog(this, width / 2 - 70, height / 2 + 85);
            allAnimals[2][3] = new Pig(this, width / 2 - 52, height / 2 + 84);       
        
        // default player
        selectedIndex = 0;
        player = allAnimals[0][0];
        
        // load frame images
        frames = new PImage[TOTAL_FRAMES];
        for (int i =0; i < TOTAL_FRAMES; i++) {
            frames[i] = loadImage("images/background_" + (i+1) + ".png");
        }
        // quest objects 
        // firewood
        firewood = new Firewood[3];
        firewood[0] = new Firewood(this, 260, 430);
        firewood[1] = new Firewood(this, 450, 430);
        firewood[2] = new Firewood(this, 640, 430);
        
        // fish
        fish = new Fish(this, 600, 380);
        
        // jade pendant 
        lostItem = new LostItem(this, 510, 290);
        
        // create villagers 
        villagerB = new VillagerBoy(this, 500, 350, firewood);
        villagerO = new VillagerOld(this, 500, 350, lostItem);
        villagerG = new VillagerGirl(this, 500, 350, fish);
        
    }
    
    // draw in frames
    public void draw() {
        if (gameState == 0) {
            showStartScreen();
        } else {
            showGameScreen();
        }
    }
    
    // start screen / frame
    private void showStartScreen() {
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
    private void showGameScreen() {
        // background 
        if (frames != null && currentFrame >= 0 && currentFrame < frames.length) {
            image(frames[currentFrame], 0, 0, width, height);
        }
        
        // draw in all quest objects 
        // draw in all firewood 
        if (currentFrame == 1) {
            for(Firewood f : firewood) {
                f.update(player);
            }
        }
        
        // draw in lost item 
        if (currentFrame == 3) {
            lostItem.update(player);
        }
        
        // draw in fish
        if (currentFrame == 5) {
            fish.update(player, player.isFishingKeyPressed());
        }
        
        // load in villagers at specific frames 
        if (currentFrame == 0) {
            villagerB.update(player, interactPressed);
        }
        if (currentFrame == 2) {
            villagerO.update(player, interactPressed);
        }
        if (currentFrame == 4) {
            villagerG.update(player, interactPressed);
        }
        
        if (player != null) {
            player.update();
            player.draw();
        }
        
        // reset each frame 
        interactPressed = false;
        
        // player movement instructions
        fill(0);
        textSize(20);
        text("Arrow Keys = Move | Space = Jump | F = Ability | Q = Fish | E = Talk", 10, 20);
        
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
                player.setFishingKey(true);
            }
            
            // villager interaction
            if (key == 'e' || key == 'E') {
                interactPressed = true;
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