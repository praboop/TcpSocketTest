package org.pxbu.tools.tcp.util;

public class TestTuple { 
	 
    private final Integer testerId; 
    private final byte[] tupleData; 
 
    public TestTuple(Integer testerId, byte[] tupleData) { 
        this.testerId = testerId; 
        this.tupleData = tupleData; 
    } 
 
    public byte[] getTupleData() { 
        return tupleData; 
    } 
 
    public Integer getTesterId() { 
        return testerId; 
    } 
 
}