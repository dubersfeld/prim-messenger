package com.dub.spring.minimumSpanningTree;

import java.util.ArrayList;
import java.util.List;

import com.dub.spring.util.SimpleStack;
import com.fasterxml.jackson.annotation.JsonIgnore;


/** Graph has Vertices and Adjacency Lists */
public class DFSGraph extends Graph {
	
	/**
	 * This subclass of Graph implements a Depth First Search algorithm on an undirected graph
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private SimpleStack<Integer> stack;// here SimpleStack is a custom Stack implementation
	
	private int N;
	private int lastFound = 0;
	private int treeNumber = 0;
	
	private Integer index;// main search loop current index
		
	int time = 0;
	
	
	public DFSGraph(int N) {
		super(N);
		this.N = N;
		stack = new SimpleStack<>();
		index = 0;
	}
	
	public DFSGraph(DFSGraph source) {// deep copy c'tor
		super(source.N);
		this.stack = new SimpleStack<>();
		for (int i = 0; i < source.N; i++) {
			DFSVertex dfsVertex = (DFSVertex)source.getVertices()[i];
			this.getVertices()[i] = new DFSVertex(dfsVertex);
		}
	}
	
	public SimpleStack<Integer> getStack() {
		return stack;
	}

	public void setStack(SimpleStack<Integer> stack) {
		this.stack = stack;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	// main search method
	public StepResult search() {
			
		index = 0;
		StepResult result = null;
			
		boolean fin = false;
		while (!fin) {
			result = searchStep();
			if (result.getStatus().equals(StepResult.Status.FINISHED)) {
				fin = true;
				
			}	
		}// while
		
		return result;
	}// search
	
	
	public StepResult searchStep() {
		/** one vertex is visited at each step */
	
		DFSGraph snapshot = null;
		
		StepResult result = new StepResult();// empty container
		result.setStatus(StepResult.Status.STEP);// default
		
		DFSVertex u = (DFSVertex)this.vertices[index];
		
		// begin with coloring
		if (u.getColor().equals(DFSVertex.Color.BLACK)) {// vertex has just been discovered
			u.setColor(DFSVertex.Color.GREEN);// visited
			time++;
			u.setD(time);
		}
			
		List<WeightedEdge> conn = u.getAdjacency();// present vertex successors 
		
	    Integer first = null;// first successor index if present
	    boolean finish = false;
	    
	    if (conn.isEmpty() || (first = this.findNotVisitedAndMark(conn, index)) == null) {
	    	finish = true;
	    }
	       
	    if (!finish) {// prepare to descend
	         
	        stack.push(index);// push present vertex before descending 	
	        index = first;// save u for the next step
	        
	    } else {// finish present vertex
	    	u.setColor(DFSVertex.Color.BLUE);
	    	time++;
	    	u.setF(time);
	    	u.setTree(treeNumber);
	    	if (!stack.isEmpty()) {
	    		index = stack.pop(); 	
	    	} else {
	    		index = this.findNotVisited();// can be null
	    		treeNumber++;// begin new tree
	    		if (index == null) {
	    			result.setStatus(StepResult.Status.FINISHED);
	    		}	
	    	}
	    }
		
	    // prepare Ajax response
	    
	    snapshot = new DFSGraph(this);
		result.setGraph(snapshot);
		
		return result;
	        
	}// searchStep
			
	
	// look for a non visited vertex to begin a new tree
	public Integer findNotVisited() {
		int nind = 0;
		DFSVertex v = null;
		for (nind = this.lastFound + 1; nind < N; nind++) {
			v = (DFSVertex)this.vertices[nind];
			if (v.getColor().equals(DFSVertex.Color.BLACK)) {
				break;
			}
		}
		
		if (nind < N) {
			this.lastFound = nind;// save as initial value for next lookup 
		
			return nind;
		} else {
			return null;
		}
				
	}// findNotVisited
	
	
	public Integer findNotVisitedAndMark(List<WeightedEdge> list, int from) {
		// successor lookup		
		int nind = 0;
		DFSVertex v = null;
		
		for (nind = 0; nind < list.size(); nind++) {
			int to = list.get(nind).getTo();
			v = (DFSVertex)this.vertices[to];
	 		
			if (v.getColor().equals(DFSVertex.Color.BLACK)) {
				break;
			}
		}
		if (nind < list.size()) {
			return list.get(nind).getTo();
		} else {
			return null;
		}
		
	}// findNotVisited
	
	
	public void displayWeights() {
		System.out.println("displayWeights");
		for (Vertex v : vertices) {// for each vertex
			for (WeightedEdge edge : v.getAdjacency()) {
				System.out.println(v.getName() 
							+ this.vertices[edge.getTo()] 
							+ edge.getWeight());
			}// for
		}// for
	}
	
	
	public MSTGraph getComp() {
		
		this.search();// needed
		
		System.out.println("getComp begin");
		// find the largest component
		
		int maxTreeNum = 0; 
				
		for (int i = 0; i < N; i++) {// for each vertex
			DFSVertex dfsv = (DFSVertex)this.getVertices()[i];
			if (dfsv.getTree() > maxTreeNum) {
				maxTreeNum = dfsv.getTree();
			}
		}// for
			
		int[] treeSizes = new int[maxTreeNum + 1];
		
		for (int i = 0; i < maxTreeNum; i++) {
		      treeSizes[i] = 0;
		}
			    
		for (int i = 0; i < N; i++) {
			treeSizes[((DFSVertex)this.getVertices()[i]).getTree()]++;
		}// for
				 	
		// find largest tree index
	    int indMax = 0;
	    for (int i = 0; i < maxTreeNum; i++) {
	    	if (treeSizes[i] > treeSizes[indMax]) {
	    		indMax = i;
	    	}
	    }
	    
	    System.out.println("largest component index: " + indMax);
	    System.out.println("largest component size: " + treeSizes[indMax]);
		int compSize = treeSizes[indMax];
		
		// now build a new connected graph with new numbering
		
		MSTGraph comp = new MSTGraph(compSize);
			    
		// helper arrays
		Integer[] oldToNew = new Integer[N];  
			    
		Integer newInd = 0;
			   
		for (int i = 0; i < N; i++) {
			// select only vertices that belong to the largest component
			DFSVertex dfsv = (DFSVertex)this.getVertices()[i];
			if (dfsv.getTree() == indMax) {// vertex selected
			    oldToNew[i] = newInd++;
			} else {
			    oldToNew[i] = null;
			}
		}// for
			  
		for (int i = 0; i < N; i++) {
			System.out.println(i + " " +  oldToNew[i]);
		}
			    	    
		int indComp = 0;
		
		for (int i1 = 0; i1 < N; i1++) {// for each original vertex
			          
			// select only vertices that belong to the largest component
			DFSVertex dfsvOld = (DFSVertex)this.getVertices()[i1];
			      	 	
			MSTVertex mstv = new MSTVertex(dfsvOld);
			mstv.setAdjacency(new ArrayList<WeightedEdge>());// initialize with empty list
			  
			if (dfsvOld.getTree() == indMax) {   
				comp.getVertices()[indComp++] = mstv;
			            
				for (int k = 0; k < dfsvOld.getAdjacency().size(); k++) {
					mstv.getAdjacency().add(
			            			new WeightedEdge(oldToNew[dfsvOld.getAdjacency().get(k).getTo()]));	            	
				}
			  
			}// if 
		}// for
		
		comp.display();
		
		// symmetry needed
	    for (int i1 = 0; i1 < compSize; i1++) {
	    	Vertex u = comp.getVertices()[i1];
	    	List<WeightedEdge> conn = u.getAdjacency();
	    	for (int k = 0; k < conn.size(); k++) {
	    		Vertex v = comp.getVertices()[conn.get(k).getTo()];
	    		if (v.getAdjIndex(i1) == null) {
	    			v.getAdjacency().add(
	    					new WeightedEdge(i1, conn.get(k).getWeight()));
	    		}// if 
	    	}// for
	    	
	    }// for 
	    
	    comp.display();
	    comp.display2();
	    comp.display3();
	    
	    // initialize weights
	    comp.randomizeWeights();
	    comp.displayWeights();
	      
		return comp;
	}// getComp

}
