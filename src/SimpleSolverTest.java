import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleSolverTest {
	@Test
	public void testConstructor() {
		
	}
	
	@Test
	public void test_first_layer_corner() {
		String state = "obrbbbybg,gybgyygry,orrorywgr,wgyrggywg,owbwwoowb,woboorryw";
		Simple_solver solver = new Simple_solver(state);
		solver.first_layer_corner();
		RubikCube cube = solver.getCube();
		assertEquals("Method first_layer_corner() fails at position 1.", cube.getColor("up", 5), cube.getColor("up", 1));
		assertEquals("Method first_layer_corner() fails at position 3.", cube.getColor("up", 5), cube.getColor("up", 3));
		assertEquals("Method first_layer_corner() fails at position 7.", cube.getColor("up", 5), cube.getColor("up", 7));
		assertEquals("Method first_layer_corner() fails at position 9.", cube.getColor("up", 5), cube.getColor("up", 9));
	}
	
	@Test
	public void test_first_layer_center() {
		String state = "bwgybygyw,gorrowrbo,wwyoybbrr,orooryygo,ybbgwowwg,brrbggwgy";
		Simple_solver solver = new Simple_solver(state);
		solver.first_layer_center();
		RubikCube cube = solver.getCube();
		assertEquals("Method first_layer_center() fails at position 2.", cube.getColor("up", 5), cube.getColor("up", 2));
		assertEquals("Method first_layer_center() fails at position 4.", cube.getColor("up", 5), cube.getColor("up", 4));
		assertEquals("Method first_layer_center() fails at position 6.", cube.getColor("up", 5), cube.getColor("up", 6));
		assertEquals("Method first_layer_center() fails at position 8.", cube.getColor("up", 5), cube.getColor("up", 8));
	}
}
