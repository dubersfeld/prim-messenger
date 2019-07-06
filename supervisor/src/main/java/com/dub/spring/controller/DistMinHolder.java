package com.dub.spring.controller;

import com.dub.spring.minimumSpanningTree.DistMin;

/** 
 * Helper class used for communication 
 * between Controller and Message Handler
 * initialized as a bean  
 * */

public class DistMinHolder {
	
	DistMin[] distMin;
	boolean[] finished;
	
	
	public DistMinHolder(int Nworkers) {
		distMin = new DistMin[Nworkers];
		finished = new boolean[Nworkers];
	}

	public DistMin[] getDistMin() {
		return distMin;
	}

	public void setDistMin(DistMin[] distMin) {
		this.distMin = distMin;
	}

	public boolean[] getFinished() {
		return finished;
	}

	public void setFinished(boolean[] finished) {
		this.finished = finished;
	}
	
	public void displayFinished() {
		System.out.println("\ndisplayFinished"); 
		for (int k = 0; k < finished.length; k++) {
			System.out.print(finished[k] + " "); 
		}
		System.out.println(); 
	}
	

}
