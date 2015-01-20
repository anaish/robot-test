package com.acme.robot;

import static org.junit.Assert.*;
import org.junit.Test;

import com.acme.robot.Robot.BEARING;
import com.acme.robot.tabletop.Geometry;


/**
 * Test class for robot commands
 *
 */
public class GeometryTest {
	
	
	/**
	 * Tests a point is within a boundary
	 */
	@Test
	public void testIsWithin(){
		
		
		assertTrue(Geometry.isWithin(1, 1, 0, 0, 5, 5));
		assertFalse(Geometry.isWithin(0, 6, 1, 1, 5, 5));
	
	}
	
	/**
	 * Tests the conversion between a bearing and an angle
	 */
	@Test
	public void testToAngle(){
		
		assertEquals(Geometry.toAngle(BEARING.NORTH), 0);
		assertEquals(Geometry.toAngle(BEARING.EAST), 90);
		assertEquals(Geometry.toAngle(BEARING.SOUTH), 180);
		assertEquals(Geometry.toAngle(BEARING.WEST), 270);
		
	
	}
	
	/**
	 * Tests the conversion between a bearing and an angle
	 */
	@Test
	public void testToBearing(){
		
		assertEquals(Geometry.toBearing(0), BEARING.NORTH);
		assertEquals(Geometry.toBearing(90), BEARING.EAST);
		assertEquals(Geometry.toBearing(180), BEARING.SOUTH);
		assertEquals(Geometry.toBearing(270), BEARING.WEST);
	
	}
	/**
	 * Tests the ability for the robot to ignore everything until a valid place command
	 */
	@Test
	public void testDeltaMove(){
		
		assertEquals(Geometry.toDeltaX(BEARING.NORTH), 1);
		assertEquals(Geometry.toDeltaY(BEARING.EAST), 1);
		assertEquals(Geometry.toDeltaX(BEARING.SOUTH),-1);
		assertEquals(Geometry.toDeltaY(BEARING.WEST),-1);
		
		
	}
	

}
