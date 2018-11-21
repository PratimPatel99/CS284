package hw4;

import java.util.ArrayList;
import java.util.Stack;
//I pledge my honor that I have abided by the Stevens Honor System -ppate78
public class Maze implements GridColors {

  
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    public boolean findMazePath() {
        return findMazePath(0, 0); 
    }
//returns true if the path is found
    public boolean findMazePath(int x, int y) {
    	if(x >= 0 && y >= 0 && x < maze.getNCols() && y < maze.getNRows() && maze.getColor(x, y) == NON_BACKGROUND){
        	if(x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)){
        		maze.recolor(x, y, PATH);
        		return true;
        	}
        	else{
        		maze.recolor(x, y, PATH);
        		if(findMazePath(x-1, y) || findMazePath(x+1, y) || findMazePath(x, y-1) || findMazePath(x, y+1)){
        			return true;
        		}
        		else{
        			maze.recolor(x, y, TEMPORARY);
        			return false;
        		}
        	}
        }
        else{
        	return false;
        }
    }
    //returns a list of all solutions
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> res = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0,0,res,trace);
    	return res;
    }
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if(y<0 || x<0 || x > maze.getNCols()-1 || y>maze.getNRows()-1 || maze.getColor(x,y) != NON_BACKGROUND) {
    		return;
    	}
    	else if (x==maze.getNCols()-1 && y== maze.getNRows()-1) {
    		maze.recolor(x, y,PATH);
    		trace.push(new PairInt(x,y));
    		result.add(new ArrayList<PairInt>(trace));
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
    	}
    	else {
    		maze.recolor(x, y, PATH);
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x+1, y, result, trace);
    		findMazePathStackBased(x-1, y, result, trace);
    		findMazePathStackBased(x, y-1, result, trace);
    		findMazePathStackBased(x, y+1, result, trace);
    		trace.pop();
    		maze.recolor(x,y,NON_BACKGROUND);
    	}
    }
    //returns the shortest path in a list of paths
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	maze.recolor(PATH, NON_BACKGROUND);
    	ArrayList<ArrayList<PairInt>> ans = findAllMazePaths(x,y);
    	if(ans.size() != 0){
	    	ArrayList<PairInt> minimum = ans.get(0);
	    	int minLength = minimum.size();
	    	for(int i=1; i<ans.size(); i++){
	    		if(minLength >= ans.get(i).size()){
	    			minimum = ans.get(i);
	    			minLength = minimum.size();
	    		}
	    	}
	    	return minimum;
    	}
    	else{
    		return new ArrayList<PairInt>();
    	}
    }
    

    
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
   
}

