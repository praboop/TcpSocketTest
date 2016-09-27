package org.pxbu.tools.tcp;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.pxbu.tools.tcp.util.*;

public class RunServer extends IoHandlerAdapter {
	
	private TCPTestServer tcpServer;
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	
	private String str(IoSession session) {
		return session.getRemoteAddress().toString();
	}
	
	private void log(String msg) {
		System.out.println(sdf.format(new Date()) + ": " + msg);
	}
	
    @Override
	public void sessionOpened(IoSession session) throws Exception {
    	session.getConfig().setIdleTime(IdleStatus.READER_IDLE, 3);
    	log("Session opened " + str(session));
    }
    
    @Override
	public void sessionClosed(IoSession session) throws Exception {
    	log("Session closed " + str(session));
    }
    
    @Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    	log(str(session) + ": Session is idle, read idle count: " + session.getReaderIdleCount());
    }
	
    @Override 
    public void messageReceived(IoSession session, Object message) 
            throws Exception { 
        TestTuple tuple = (TestTuple) message; 
        log("Got the message: " + tuple.getTesterId() + ", " + new String(tuple.getTupleData()));
    }
	
	public RunServer() throws Exception {		
        tcpServer = new TCPTestServer(1234, this); 
 
        InetSocketAddress testAddr = tcpServer.start(); 
        log("Test server started at : " + testAddr.toString());
	}

	public static void main(String... args) throws Exception {
		new RunServer();
	}
}
