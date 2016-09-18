package com.li.tools.utils.mbean;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.junit.Test;

public class MBeanTest {
	public static void main(String[] args) {
		MBeanTest mBeanTest = new MBeanTest();
		mBeanTest.test1();
		try {
		    Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	@Test
	public void test1(){
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		Echo echoMBean = new Echo();
		ObjectName objectName = null;
		try {
			objectName = new ObjectName("com.li.tools.utils.mbean:type=Echo");
			mBeanServer.registerMBean(echoMBean, objectName);
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mBeanServer.invoke(objectName, "print", new String[]{"lijuntao"}, new String[]{"java.lang.String"});
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
