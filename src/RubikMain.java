/**
 * EightsPuzzleMain is a class containing only static methods, useful for
 * running searches on 8-puzzle-like problems. A main method is included for
 * running these searches from the command line.
 * 
 * @author John MacCormick, Dickinson College
 * @version September 2014
 */
public class RubikMain {

	/**
	 * The state of a solved cube
	 */
	public static final RubikCube goalCube = new RubikCube("bbbbbbbbb,ooooooooo,yyyyyyyyy,rrrrrrrrr,wwwwwwwww,ggggggggg"); 

	private static void usage() {
		System.out.println("usage: java RubikMain bfs|dfs|ids|as1|as2 tree|graph maxNodes maxDepth");
		System.exit(-1);
	}

	/**
	 * Runs a search on a cube, printing some details about
	 * the solution.
	 * 
	 * @param args
	 *            The command line arguments are: bfs|dfs|ids|as1|as2 tree|graph
	 *            maxNodes maxDepth. These represent, respectively, the search
	 *            algorithm to be used, the type of search to be used, the
	 *            maximum number of nodes to be expanded, and the maximum depth
	 *            of the search tree.
	 */
	public static void main(String[] args) {
		//////////////////////////////////////////////////////////////////
		// Set some parameters 
		//////////////////////////////////////////////////////////////////
		
		// The goal
		RubikWorldState goal_state = new RubikWorldState(goalCube);
		int maxNodes = 200000000;
		int maxDepth = 20;
		ClassicalSearch.SearchType searchType = ClassicalSearch.SearchType.Tree;
		
		// TEST CASES
		String solved_state = "bbbbbbbbb,ooooooooo,yyyyyyyyy,rrrrrrrrr,wwwwwwwww,ggggggggg";
		String state1 = "bbybbybby,ooooooooo,yywyywyyw,rrrrrrrrr,wwgwwgwwg,bggbggbgg"; // 1 rotation away
		String state2 = "bbbbbbggg,ggwoooooo,ooobyybyy,byyrrrrrr,ywwywwyww,rrrggwggw"; // 2 rotations away
		String state3 = "obbbbbbgg,woogoogoo,yooyyyyyy,byyrrrrrr,wwwwwwrww,rrgggbggb"; // 3 rotations away
		String state4 = "bbbbbbbbb,rrrooorrr,gggyyyggg,ooorrrooo,wwwwwwwww,yyygggyyy"; // 4 rotations away
		String state5 = "bbyobyoby,owwwoogoo,yywbywbyw,rrrrrgrrg,ywgywgrrw,bggoggobb"; // 5 rotations away
		String state6 = "rryybywyy,yoooobogy,gwwyyowww,rrbgrwrbr,oogrwggwg,obbrgbbgb"; // 6 rotations away, looking very shuffled
		//String state7 = "ggrbbryyw,bywrooroo,rrgbywbyo,rbbrroywo,ywwywgbbg,yoowggwgg";
		
		//String stateU = "gyyorooyo,bggwyrybg,wrywworbg,byrbgowgy,brrgowwrw,bbowbyrgo"; // <unknown> rotations away
//		RubikCube initialCube = new RubikCube(state1);
//		
////		initialCube.random_shuffle(10);
//		RubikWorldState initial_state = new RubikWorldState(initialCube);
//		SearchNode initialNode = new BreadthFirstSearchNode(null, initial_state, null);
//		SearchNode AStarNode = new AStar3DManhattan(null, initial_state, null);

		double totalTime = 0;
		double totalExpanded = 0;
		double totalGenerated = 0;

		for (int i = 0 ; i < 100; i++) {
			RubikCube initialCube = new RubikCube(solved_state);
			int shuffle = 1;
			initialCube.random_shuffle(shuffle);
			RubikWorldState initial_state = new RubikWorldState(initialCube);
			SearchNode initialNode = new DepthFirstSearchNode(null, initial_state, null);
			ClassicalSearch classical_search = new ClassicalSearch(initialNode,
					goal_state, maxNodes, maxDepth, searchType);
			long startTime = System.nanoTime();
			if (classical_search.search()) {
				System.out.println("Solution found.");
			} else {
				System.out.println("No solution found.");
			}
			
			long endTime = System.nanoTime();
			double timeTaken = ((double) (endTime - startTime)) / 1000000;
			totalTime += timeTaken;
			totalExpanded += classical_search.getExpandedNodes();
			totalGenerated += classical_search.getGeneratedNodes();
		}
		
		System.out.println("Average time taken is " + totalTime / 100);
		System.out.println("Average number of nodes expanded is " + totalExpanded/100);
		System.out.println("Average number of nodes generated is " + totalGenerated/100);
		
		
		
		
		
		
//		long startTime = System.nanoTime();
//		ClassicalSearch classical_search = new ClassicalSearch(initialNode,
//				goal_state, maxNodes, maxDepth, searchType);
//		if (classical_search.search()) {
//			System.out.println("Solution found.");
//		} else {
//			System.out.println("No solution found.");
//		}
//		System.out.println("Expanded nodes: " + classical_search.getExpandedNodes());
//		System.out.println("Generated nodes: " + classical_search.getGeneratedNodes());
//		
//		long endTime = System.nanoTime();
//		double timeTaken = ((double) (endTime - startTime)) / 1000000;
//		System.out.println("Total time taken: " + timeTaken + " milliseconds");
		//////////////////////////////////////////////////////////////////
		// Process command line arguments
		//////////////////////////////////////////////////////////////////
		
//		if (args.length != 4) {
//			usage();
//		}
//		
//		RubikWorldState goal_state = new RubikWorldState(goal);
//
//		String searchAlgorithm = args[0];
//		if (searchAlgorithm.equals("bfs")) {
//			initial_node = new BreadthFirstSearchNode(null, initial_state, null);
//		} else if (searchAlgorithm.equals("dfs")) {
//			initial_node = new DepthFirstSearchNode(null, initial_state, null);
//		} else if (searchAlgorithm.equals("as1")) {
//			initial_node = new AStarNumTiles(null, initial_state, null, goal_state);
//			((AStarNumTiles) initial_node).setNumMoves(0);
//		} else if (searchAlgorithm.equals("as2")) {
//			initial_node = new AStarManhattan(null, initial_state, null, goal_state);
//			((AStarManhattan) initial_node).setNumMoves(0);
//		} else if (searchAlgorithm.equals("ids")) {
//			throw new RuntimeException("iterative deepening search not implemented yet");
//		} else {
//			usage();
//		}

//		String searchTypeString = args[1];
//		if (searchTypeString.equals("tree")) {
//			searchType = ClassicalSearch.SearchType.Tree;
//		} else if (searchTypeString.equals("graph")) {
//			searchType = ClassicalSearch.SearchType.Graph;
//		} else {
//			usage();
//		}
//
//		try {
//			maxNodes = Integer.parseInt(args[2]);
//			maxDepth = Integer.parseInt(args[3]);
//		} catch (NumberFormatException e) {
//			usage();
//		}


		//////////////////////////////////////////////////////////////////
		// Run the search.
		//////////////////////////////////////////////////////////////////
	}
}
