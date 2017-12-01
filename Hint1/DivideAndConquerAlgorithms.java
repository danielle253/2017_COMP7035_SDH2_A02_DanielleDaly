package part1;
/* Danielle Daly
 * R00148814
 * A02: Hint1
 */
/**
 * The class contains the Divide and Conquer-based Algorithms we are using. 
 */
public class DivideAndConquerAlgorithms {

	//----------------------------------------------
	// Class constructor
	//----------------------------------------------	
	/**
	 * Constructor of the class. Do not edit it.
	 */
	public DivideAndConquerAlgorithms(){}

	//-------------------------------------------------------------------
	// 0. iterativeDisplayElements --> Displays all elements of a MyList 
	//-------------------------------------------------------------------	
	/**
	 * Given a concrete MyList, this iterative algorithm displays its elements by screen (if any).
	 * @param m: The MyList we want to display its elements.	  
	 */	
	public void iterativeDisplayElements(MyList<Integer> m){
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
			System.out.println("MyList Contains the following " + size + " items: ");

			//2. We traverse the items
			for (int i = 0; i < size; i++)
				System.out.println("Item " + i + ": " + m.getElement(i));

			break;

		}

	}

	//-------------------------------------------------------------------
	// 1. maxInt --> Computes the maximum item of MyList 
	//-------------------------------------------------------------------	
	/**
	 * The function computes the maximum item of m (-1 if m is empty). 
	 * @param m: The MyList we want to compute its maximum item.
	 * @return: The maximum item of MyList	  
	 */	
	public int maxInt(MyList<Integer> m){
		int res = 0;

		int scenario = 0;

		//1. Identify
		//1.1
		if(m.length() == 0){
			scenario = 1;
		}
		//1.2
		else{
			scenario = 2;
		}

		//2. Implement
		switch(scenario){
		//2.1 Empty
		case 1:
			res = -1;
			break;

			//2.2 Non-Empty
		case 2:

			//Get 1st Element
			int e0 = m.getElement(0);

			//Remove said element
			m.removeElement(0);

			//Recursively solve
			res = maxInt(m);

			//Check smallest
			if(e0 > res){

				res = e0;
			}

			//Add the element back in
			m.addElement(0, e0);

			break;	

		}
		return res;

	}

	//-------------------------------------------------------------------
	// 2. isReverse --> Computes if MyList is sorted in decreasing order 
	//-------------------------------------------------------------------	
	/**
	 * The function computes whether m is sorted in decreasing order or not.  
	 * @param m: The MyList we want to check.
	 * @return: Whether m is sorted in decreasing order or not.  
	 */	
	public boolean isReverse(MyList<Integer> m){
		boolean isSorted = true;
		int scenario = 0;

		//1. Identify
		if(m.length() <= 1){
			scenario = 1;
		}
		else{
			scenario = 2;
		}

		//2. Implement
		switch(scenario){

		case 1: 
			isSorted = true;
			break;

		case 2:
			//Get first 2 elements
			int e0 = m.getElement(0);
			int e1 = m.getElement(1);

			//If first is greater than second, testing continues
			if(e0 >= e1){
				//Remove second element
				m.removeElement(1);

				//Solve recursively
				isSorted = isReverse(m);

				//Add back in
				m.addElement(1, e1);
			}
			//If second bigger than first, we can conclude it's not reverse-sorted
			else{
				isSorted = false;
			}
		}
		return isSorted;	

	}

	//-------------------------------------------------------------------
	// 3. getNumAppearances --> Computes the amount of times that integer appears in MyList  
	//-------------------------------------------------------------------	
	/**
	 * The function computes the amount of times that the integer n appears in m.   
	 * @param m: The MyList we want to use.
	 * @param n: The number we want to compute its appearances for.
	 * @return: The amount of appearances of n into m  
	 */	
	public int getNumAppearances(MyList<Integer> m, int n){
		int numAppearances = 0;
		if(m.length() == 0){
			numAppearances = 0;
		}

		else{

			int e0 = m.getElement(0);
			m.removeElement(0);
			numAppearances = getNumAppearances(m, n);
			if(e0 == n){
				numAppearances++;
			}
			m.addElement(0, e0);

		}

		return numAppearances;
	}

	//-------------------------------------------------------------------
	// 4. power --> Computes the m-est power of n
	//-------------------------------------------------------------------	
	/**
	 * The function computes n to the power of m.
	 * @param n: The base number.
	 * @param m: The power of n we want to compute
	 * @return: n to the power of m.  
	 */	

	public int power(int n, int m){
		int answer = 0;
		// n to the power of m
		//Exponent = m
		//Base = n
		//Anything to the power of 0 is 1
		if(m == 0){
			answer = 1;
		}
		//If the base is even, get the base by half the power and multiply by itself.
		else if(m % 2 == 0){
			answer = power(n, m / 2);
			answer = answer * answer;
		}
		else{
			answer = n * power(n, m-1);
		}

		return answer; 
	}

	//-------------------------------------------------------------------
	// 5. lucas --> Computes the n-est term of the Lucas series
	//-------------------------------------------------------------------	
	/**
	 * The function computes the n-est term of the Lucas series
	 * @param n: The n-est term of the series we want to compute
	 * @return: The term being computed 
	 */	
	public int lucas(int n){
		int res = 0;
		int scenario = 0;

		//1. Identify

		//Look for Index 0
		if(n == 0){
			scenario = 1;
		}
		else{
			//Look for Index 1
			if(n == 1){
				scenario = 2;
			}
			//Look for an index greater than 1
			else{
				scenario = 3;
			}
		}

		//2. Implement

		switch(scenario){

		//Index 0
		case 1: 
			res = 2;
			break;

			//Index 1
		case 2:
			res = 1;
			break;

			//Index greater than 1
		case 3: 
			//Solve smaller problem
			int n1 = lucas(n-1);

			int n2 = lucas(n-2);
			//Add the results
			res = n1 + n2;

			break;

		}
		return res;
	}

	//-------------------------------------------------------------------
	// 6. drawImage --> Prints a pattern of a given length
	//-------------------------------------------------------------------	
	/**
	 * The function prints prints a pattern of a given length.
	 * *
	 * **
	 * ***
	 * ... 
	 * @param n: The length of the desired pattern
	 */	
	public void drawImage(int n){
		String pattern = "*";

		if(n > 0){
			int stars = 0;
			drawImage(n - 1);
			
			while(stars < n) {
				System.out.print(pattern);
				stars++;
			}
			System.out.println(" ");
		}
	}
}
