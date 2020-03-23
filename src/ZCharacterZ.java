//Things this needs to do (and doesn't)
//Find a Target, idk how to iterate over all characters, otherwise the setup works
//Do the whole Display Options routine
//Not Teleport

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.*;
public class ZCharacter {
    private String zombie;
    public String getCharacterType(){return zombie;}

    private int maxHP = 1;
    public int getMapHP(){return maxHP;}
    private int currentHP = maxHP;
    public int getCurrentHP(){return currentHP;}

    private int speed = 1;
    public int getSpeed(){return speed;}

    private int x, y;
    public int getX(){return x;}
    public int getY(){return y;}
    public void moveTo(int x, int y){}
    private Rectangle rect = new Rectangle(MapTile.tileSize - offset, MapTile.tileSize - offset);
    private Map map;
    public Map getMap(){return map;}

    private Pane pane;
    public Pane getPane(){return pane;}

    public void onClick(){
        //Called when clicked from map
        displayOptions();
    }
    public void displayOptions(){
        // Show (and color) all tiles available to move to
        // Show (different color) tiles that can be interacted with
        // Blue for movement, red for enemies, green for items?
        // Tell map that the player is interacting with this character,
        // Momentarily pause other map items from being interacted with

        // If interact with object, interactWithObjectAt(area clicked);
        // If that object is ZCharacter, also call thatObject.beInteractedWith(this);
    	
    	//Not entirely sure exactly how to do this
    	//Want three options
    	//Go to Tile
    	//Go to Character
    	//Do Whatever
    	//Specifically this method seems rather generic 
    	rect.setTranslateX((x+1) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y+1) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x+1) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y+0) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x+0) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y+1) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x-1) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y+1) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x+1) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y-1) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x-1) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y-1) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x+0) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y-1) * MapTile.tileSize + (offset+1) / 2);
        
        rect.setTranslateX((x-1) * MapTile.tileSize + (offset+1) / 2);
        rect.setTranslateY((y+0) * MapTile.tileSize + (offset+1) / 2);
        int[] target = findTarget;
        Queue<Integer> path = pathfind(target[0],target[1]);
        move(path);
    }
    
    private void path(Queue<Integer> path) {
    	int move = path.remove();
    	while(!path.isEmpty()) {
    		if(move == 0) {
    			moveTo(x+1,y);
    		}else if(move == 1) {
    			moveTo(x-1,y);
    		}else if(move == 2) {
    			moveTo(x,y+1);
    			
    		}else if(move == 3) {
    			moveTo(x,y-1);
    		}
    		move = path.remove();
    	}
    }
    
    private int[] findTarget() {
    	double distance = Integer.MAX_VALUE;
    	int targetX = 0;
    	int targetY = 0;
    	for() {//Cycle through all Civilian/Resistance characters
    		if((Math.sqrt(a.getX * a.getX + a.getY * a.getY) < distance){
    			targetX = a.getX;
    			targetY = a.getY;
    			distance = Math.sqrt(a.getX * a.getX + a.getY * a.getY);
    		}
    	}
    	int[] target = new int[2];
    	target[0]=targetX;
    	target[1]=targetY;
    	return target;
    }
    
    private Queue<Integer> pathfind(int targetX,int targetY) {
    	Queue<Integer> path = new LinkedList<Integer>();
    	if(x < targetX) {
    		for(int count = x; count <= targetX; count++) {
    			path.add(0);
    		}
    	}else {
    		for(int count = targetX; count <= x; count++) {
    			path.add(1);
    		}
    	}
    	
    	if(y < targetY) {
    		for(int count = y; y < targetY; count++) {
    			path.add(2);
    		}
    	}else {
    		for(int count = targetY; y < targetY; count++) {
    			path.add(3);
    		}
    	}
    	
    	return path;
    	
    }
    
    public void interactWithObjectAt(int x, int y){ 
    	    
    }
    public void beInteractedWith(ZCharacter z){ 
    	currentHP--;
    	if(currentHP == 0) {
    		moveTo(-1,-1);
    	}
    }
}

/*
Need to do:
Interact with Target
Be Interacted With DONE
Move HALF DONE
go to point
*/
