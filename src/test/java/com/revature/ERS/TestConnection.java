package com.revature.ERS;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestConnection{
	
	@Test
	public void connectionTest() throws ClassNotFoundException {
		assertNotNull(ConnectionFactory.getConnection());
		//Class.forName("oracle.jdbc.OracleDriver");
	}
}
