package org.pxbu.tools.tcp;

import java.net.Inet4Address;

import org.pxbu.tools.tcp.util.TCPTestClient;
import org.pxbu.tools.tcp.util.TestTuple;

import java.net.*;

public class RunClient {

	public static void main(String... args) throws Exception {
		
		String ip = Inet4Address.getLocalHost().getHostName();
		int port = 1234;
		System.out.println("Connecting to IP: " + ip + ":" + port);
		
        TCPTestClient testClient = new TCPTestClient(new InetSocketAddress(ip, port));
        testClient.connect();
        
        for (int i=0; i < 3; i++) {
            TestTuple tuple = new TestTuple(i, ("Echo " + i).getBytes());
            testClient.writeTuple(tuple);
            Thread.sleep(1000);
        }

        System.out.println("Sleeping.......");
        
        Thread.sleep(1000000);
	}
}
