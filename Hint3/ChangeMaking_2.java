package part3;

/**
 * Classical Change making problem with an unlimited amount of coins of each type. <br> 
 * Version 2: Selection function with more elaborated policy: First biggest-coin.<br> 
 * Depending on the type of coins, it can lead to an optimal solution.<br>
 * The class encapsulates all the functions of the Greedy schema<br>
 */

public class ChangeMaking_2 {

	//---------------------------------------
	//	Constructor
	//---------------------------------------
	/**
	 * Constructor of the class. Do not edit it.
	 */
	public ChangeMaking_2(){}

	//-------------------------------------------------------------------
	// 0. displayElements --> Displays all elements of a MyList 
	//-------------------------------------------------------------------	
	/**
	 * Given a concrete MyList, this function displays its elements by screen (if any).
	 * @param m: The MyList we want to display its elements.	  
	 */	
	public void displayElements(MyList<Integer> m){
		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//-----------------------------
		// I. SCENARIO IDENTIFICATION
		//-----------------------------
		int scenario = 0; 

		//Rule 1. MyList is empty
		if (m.length() == 0) 
			scenario = 1;
		//Rule 2. MyList is non-empty
		else
			scenario = 2;

		//-----------------------------
		// II. SCENARIO IMPLEMENTATION 
		//-----------------------------
		switch(scenario){	

		//Rule 1. MyList is empty
		case 1: 
			//1. We print the empty message
			System.out.println("Empty MyList");

			break;

			//Rule 2. MyList is non-empty
		case 2: 
			//1. We print the initial message
			int size = m.length();
			System.out.print("MyList has " + size + " items: [");

			//2. We traverse the items
			for (int i = 0; i < size - 1; i++)
				System.out.print(m.getElement(i) + ", ");
			System.out.println(m.getElement(size - 1) + "]");

			break;

		}

	}

	//-------------------------------------------------------------------
	// 1. getCandidate --> It selects the next candidate to be considered.  
	//-------------------------------------------------------------------	
	/**
	 * Given a current solution that is not a final solution, this function selects the new candidate to be added to it.<br> 
	 * The policy followed is more elaborated: Pick the biggest non-discarded type of coin.
	 * @param changeGenerated: The quantity of change we have generated so far. 
	 * @param discarded: The MyList stating whether a candidate has been discarded so far or not.
	 * @param coinValues: A MyList containing the value of each type of coin supported. 
	 * @return: The index of candidate to be selected.
	 */	
	public int getCandidate(int changeGenerated, 
			MyList<Integer> discarded, 
			MyList<Integer> coinValues){

		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		int res = -1;

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//OP1. Auxiliary variables:
		//We use 'size' to compute just once the length of MyList 'items'.
		int size = coinValues.length();

		//We use 'index' to state the index of the candidate being checked.
		int index = 0;
		int biggestNum = 0;
		int indexHolder = 0;

		//OP1. We traverse all elements in items, so as to find the first one not being picked so far. 
		while ((res == -1) && (index < size)){
			//OP1.1. If the item has not been picked before, we pick it

			if (discarded.getElement(index) == 0) {

				if(coinValues.getElement(index) > biggestNum) {
					biggestNum = coinValues.getElement(index);
					indexHolder = index;
				}

			}

			//OP1.2. We increase 'index' so as to try the next item
			index++;
		}

		res = indexHolder;


		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;		
	}

	//-------------------------------------------------------------------
	// 2. isValid --> It selects if a candidate can be added to the solution.  
	//-------------------------------------------------------------------	
	/**
	 * Given a current solution and a selected candidate, this function 
	 * states whether the candidate must be added to the solution or discarded.<br> 
	 * @param coinValues: A MyList containing the value of each type of coin supported. 
	 * @param amount: The amount of change we want to generate.
	 * @param changeGenerated: The quantity of change we have generated so far. 
	 * @param itemSelected: The index of the candidate selected.
	 * @return: Whether the candidate fits or not into the solution.
	 */	

	public boolean isValid(MyList<Integer> coinValues,
			int amount,
			int changeGenerated,
			int itemSelected){


		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		boolean res = false;

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//1. We check if the candidate fits or not
		if(coinValues.getElement(itemSelected) + changeGenerated <= amount){
			res = true;
		}

		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;		
	}

	//-------------------------------------------------------------------
	// 3. isFinal --> It selects if the current solution is the final solution  
	//-------------------------------------------------------------------	
	/**
	 * Given a current solution, this function states whether it is a final solution or it can still be improved.<br> 
	 * To determine it, it checks whether there is (at least) one more coin that can be used as part of the change.
	 * @param changeGenerated: The change generated by the current solution. 
	 * @param discarded: The MyList stating whether a candidate has been discarded so far or not.
	 * @param coinValues: The MyList containing the set of coins supported. 
	 * @param amount: The amount of change we want to generate.
	 * @return: Whether the current solution is the final solution.
	 */	
	public boolean isFinal(int changeGenerated,
			MyList<Integer> discarded,
			MyList<Integer> coinValues, 
			int amount){

		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		boolean res = true;

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		//1. Auxiliary Variables:
		//Size to compute the length of coinValues
		int size = coinValues.length();

		//Index to state the index of the item being checked.
		int index = 0;

		//2. Traverse all elements to see if one not being selected so far can be added. 
		while(res && index < size)
		{
			//If item hasn't been picked before
			if(discarded.getElement(index) == 0){	

				//Check will it fit our solution - if yes then solution can be improved
				if(changeGenerated + coinValues.getElement(index) <= amount){
					res = false;
				}
				else {
					discarded.addElement(index, coinValues.getElement(index));
				}
			}
			//Increase index to try the next item
			index++;
		}

		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;		
	}

	//-------------------------------------------------------------------
	// 4. objectiveFunction --> This function computes the value of the final solution.  
	//-------------------------------------------------------------------	
	/**
	 * Given the final solution to the problem, this function 
	 * computes its value according to:<br>
	 * How many coins are used in the solution.<br>
	 * How accurate it is the change.<br> 
	 * @param sol: The MyList containing the solution to the problem.
	 * @param changeGenerated: The change generated by the current solution. 
	 * @param amount: The amount of change we want to generate. 
	 * @return: The value of such solution.
	 */	
	public MyList<Integer> getQuality(MyList<Integer> sol, 
			int changeGenerated, 
			int amount){

		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		MyList<Integer> res = null;

		//-----------------------------
		//SET OF OPS
		//-----------------------------

		int numberOfCoins = 0;
		//Count the number of coins
		for(int i = 0; i < sol.length(); i++){
			numberOfCoins += sol.getElement(i);
		}

		//Initialize output variable
		res = new MyDynamicList<Integer>();

		//The number of a given coin in the solution
		res.addElement(0, numberOfCoins);

		//Change remaining 
		res.addElement(1, amount - changeGenerated);


		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;		
	}

	//-------------------------------------------------------------------
	// 5. solve --> This function solves the problem using a greedy algorithm.  
	//-------------------------------------------------------------------	
	/**
	 * Given an instance of the GP1 problem, this function solves it using 
	 * a greedy algorithm.<br> 
	 * @param coinValues: A MyList containing the value of each type of coin supported. 
	 * @param amount: The amount of change we want to generate.
	 * @return: A MyList containing the amount of coins of each type being selected.
	 */	
	public MyList<Integer> solve(MyList<Integer> coinValues, int amount){
		//-----------------------------
		//Output Variable --> InitialValue
		//-----------------------------
		MyList<Integer> res = null;
		MyList<Integer> solutionValue = null;
		MyList<Integer> discarded = null;

		//-----------------------------
		//SET OF OPS
		//-----------------------------
		//Compute length of coin values
		int size = coinValues.length();

		//State the amount of change generated so far
		int changeGenerated = 0;

		//Initialize output variable and set all values to 0
		res = new MyDynamicList<Integer>();
		for (int i = 0; i < size; i++){
			res.addElement(0, 0);
		}

		//OP1.2. 'discarded' is initialized to be a list of the size of items, with all values being 0.
		discarded = new MyDynamicList<Integer>();
		for (int i = 0; i < size; i++)
		{
			discarded.addElement(0, 0);
		}

		//Construct the final solution
		while(isFinal(changeGenerated, discarded, coinValues, amount) == false)
		{
			//We use 'itemSelected' to state the index of the candidate being selected.
			int itemSelected = -1;

			//OP2.1. We pick the most promising candidate
			itemSelected = getCandidate(changeGenerated, discarded, coinValues);

			//Check feasibility
			if(isValid(coinValues, amount, changeGenerated, itemSelected) == true)
			{
				int item = res.getElement(itemSelected) + 1;
				res.removeElement(itemSelected);
				res.addElement(itemSelected, item);

				//Update Capacity
				changeGenerated += coinValues.getElement(itemSelected);
			}
			else
			{
				discarded.removeElement(itemSelected);
				discarded.addElement(itemSelected, 1);
			}
		}

		//Print solution with associated value
		displayElements(res);

		solutionValue = getQuality(res, changeGenerated, amount);

		//Display the answer and any change outstanding
		System.out.println("Answer has used " + solutionValue.getElement(0) + " coins. "
				+ solutionValue.getElement(1) + "c outstanding");
		//-----------------------------
		//Output Variable --> Return FinalValue
		//-----------------------------		
		return res;			
	}

}
