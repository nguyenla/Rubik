/**
 * RubikMain is a class containing only static methods for running experiment on solving Rubik cube.
 * A main method is included for running these searches from the command line.
 * 
 * @author Lam Nguyen, Sasha Jouravlev, Dickinson College
 * @version December 2016
 */
public class RubikMain {
	/**
	 * The state of a solved cube
	 */
	public static final RubikCube goalCube = new RubikCube("bbbbbbbbb,ooooooooo,yyyyyyyyy,rrrrrrrrr,wwwwwwwww,ggggggggg"); 

	private static void usage() {
		System.out.println("Please specify the following 6 arguments:");
		System.out.println("The search algorithm to use, which can be: bfs | dfs | as3d | as3dim");
		System.out.println("Type of search to use, which can be: tree | graph");
		System.out.println("The number of random shuffles");
		System.out.println("The maximum number of nodes to be expanded");
		System.out.println("The maximum depth of the search tree");
		System.out.println("The number of repetitions to run in the experiment");
		System.exit(-1);
	}

	public static void experiment(String algorithm, ClassicalSearch.SearchType searchType, int shuffle, int maxNodes, int maxDepth, int iterations) {
		double totalTime = 0;
		double totalExpanded = 0;
		double totalGenerated = 0;
		String solved_state = "bbbbbbbbb,ooooooooo,yyyyyyyyy,rrrrrrrrr,wwwwwwwww,ggggggggg";
		RubikWorldState goal_state = new RubikWorldState(goalCube);

		for (int i = 0 ; i < iterations; i++) {
			System.out.println("Iteration " + (i + 1));
			RubikCube initialCube = new RubikCube(solved_state);
			initialCube.random_shuffle(shuffle);
			RubikWorldState initial_state = new RubikWorldState(initialCube);

			SearchNode initialNode = null;
			if (algorithm.equals("bfs")) {
				initialNode = new BreadthFirstSearchNode(null, initial_state, null);
			}
			else if (algorithm.equals("dfs")) {
				initialNode = new DepthFirstSearchNode(null, initial_state, null);
			}
			else if (algorithm.equals("as3d")) {
				initialNode = new AStar3DManhattan(null, initial_state, null);
			}
			else if (algorithm.equals("as3dim")) {
				initialNode = new AStar3DManhattanImproved(null, initial_state, null);
			}

			// Create the ClassicalSearch object
			ClassicalSearch classical_search = new ClassicalSearch(initialNode, goal_state, maxNodes, maxDepth, searchType);

			// Start the experiment and record the relevant information
			long startTime = System.nanoTime();
			if (classical_search.search()) {
				System.out.println("Solution found.");
			} else {
				System.out.println("No solution found.");
			}
			long endTime = System.nanoTime();
			double timeTaken = ((double) (endTime - startTime)) / 1000000; // calculate the time taken in milliseconds

			// Update the totals
			totalTime += timeTaken;
			totalExpanded += classical_search.getExpandedNodes();
			totalGenerated += classical_search.getGeneratedNodes();
		}

		System.out.println("Average time taken is " + totalTime / 100);
		System.out.println("Average number of nodes expanded is " + totalExpanded/100);
		System.out.println("Average number of nodes generated is " + totalGenerated/100);
	}

	/**
	 * 
	 * 
	 * @param args The command line arguments are: 
	 * - bfs | dfs | as3D | as3Dimproved : Search algorithm to use
	 * - tree|graph : Type of search to use
	 * - numShuffle : Number of random shuffles from a solved cube
	 * - maxNodes : maximum number of nodes to be expanded
	 * - maxDepth : maximum depth of the search tree
	 * - iterations : number of repetitions in the experiment 
	 */
	public static void main(String[] args) {

		//////////////////////////////////////////////////////////////////
		// Process command line arguments
		//////////////////////////////////////////////////////////////////
		if (args.length != 6) {
			usage();
		}

		String searchAlgorithm = args[0];
		String searchTypeString = args[1];
		int numShuffle = 0;
		int maxNodes = -1;
		int maxDepth = -1;
		int iterations = 0;
		try {
			numShuffle = Integer.parseInt(args[2]);
			maxNodes = Integer.parseInt(args[3]);
			maxDepth = Integer.parseInt(args[4]);
			iterations = Integer.parseInt(args[5]);
		} 
		catch (NumberFormatException e) {
			usage();
		}

		ClassicalSearch.SearchType searchType = null;
		if (searchTypeString.equals("tree")) {
			searchType = ClassicalSearch.SearchType.Tree;
		} else if (searchTypeString.equals("graph")) {
			searchType = ClassicalSearch.SearchType.Graph;
		} else {
			usage();
		}

		//////////////////////////////////////////////////////////////////
		// Run the experiment.
		//////////////////////////////////////////////////////////////////
		try {
		experiment(searchAlgorithm, searchType, numShuffle, maxNodes, maxDepth, iterations);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			usage();
		}

		//////////////////////////////////////////////////////////////////
		// SOME LEGACY TEST CASES
		//////////////////////////////////////////////////////////////////
		// String state1 = "bbybbybby,ooooooooo,yywyywyyw,rrrrrrrrr,wwgwwgwwg,bggbggbgg"; // 1 rotation away
		// String state2 = "bbbbbbggg,ggwoooooo,ooobyybyy,byyrrrrrr,ywwywwyww,rrrggwggw"; // 2 rotations away
		// String state3 = "obbbbbbgg,woogoogoo,yooyyyyyy,byyrrrrrr,wwwwwwrww,rrgggbggb"; // 3 rotations away
		// String state4 = "bbbbbbbbb,rrrooorrr,gggyyyggg,ooorrrooo,wwwwwwwww,yyygggyyy"; // 4 rotations away
		// String state5 = "bbyobyoby,owwwoogoo,yywbywbyw,rrrrrgrrg,ywgywgrrw,bggoggobb"; // 5 rotations away
		// String state6 = "rryybywyy,yoooobogy,gwwyyowww,rrbgrwrbr,oogrwggwg,obbrgbbgb"; // 6 rotations away, looking very shuffled
		// String state7 = "ggrbbryyw,bywrooroo,rrgbywbyo,rbbrroywo,ywwywgbbg,yoowggwgg";
		// String stateU = "gyyorooyo,bggwyrybg,wrywworbg,byrbgowgy,brrgowwrw,bbowbyrgo"; // <unknown> rotations away
	}
}