package com.company.companyadda.Helper;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.List;
import java.util.Random;

public abstract class Captcha {
	 public Bitmap image;
	public String answer = "";
	public int width;
	 public int height;
	protected int x = 0;
	protected int y = 0;
	protected static List<Integer> usedColors;
	
	protected abstract Bitmap image();

	public static int color(){
    	Random r = new Random();
    	int number;
    	do{
    		number = r.nextInt(9);
    	}while(usedColors.contains(number));
    	usedColors.add(number);
    	switch(number){
	    	case 0: return Color.WHITE;
	    	case 1: return Color.WHITE;
	    	case 2: return Color.WHITE;
	    	case 3: return Color.WHITE;
	    	case 4: return Color.WHITE;
	    	case 5: return Color.WHITE;
	    	case 6: return Color.WHITE;
	    	case 7: return Color.WHITE;
	    	case 8: return Color.WHITE;
	    	case 9: return Color.WHITE;
	    	default: return Color.WHITE;
    	}
    }

	public static int color1(){
		Random r = new Random();
		int number;
		do{
			number = r.nextInt(9);
		}while(usedColors.contains(number));
		usedColors.add(number);
		switch(number){
			case 0: return Color.BLACK;
			case 1: return Color.BLACK;
			case 2: return Color.BLACK;
			case 3: return Color.BLACK;
			case 4: return Color.BLACK;
			case 5: return Color.BLACK;
			case 6: return Color.BLACK;
			case 7: return Color.BLACK;
			case 8: return Color.BLACK;
			case 9: return Color.BLACK;
			default: return Color.BLACK;
		}
	}
    
    public int getWidth(){
    	return this.width;
    }
    
    public void setWidth(int width){
    	if(width > 0 && width < 10000){
    		this.width = width;
    	}else{
    		this.width = 300;
    	}
    }
    
    public int getHeight(){
    	return this.height;
    }
    
    public void setHeight(int height){
    	if(height > 0 && height < 10000){
    		this.height = height;
    	}else{
    		this.height = 100;
    	}
    }
    
	public Bitmap getImage() {
		return this.image;
	}

	public boolean checkAnswer(String ans) {
		return ans.equals(this.answer);
	}
}
