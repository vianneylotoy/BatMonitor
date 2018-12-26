package main;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import connexion.CommunicationRemote;
import linux.battery.BatteryLvls;

public class Main {
	
	public static Context getContext() throws NamingException
	{
		Properties jndiProps = new Properties();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        Context ctx = new InitialContext(jndiProps);
        return ctx;
	}
	
	//TODO: manage exceptions
	public static void main(String[] args) throws FileNotFoundException, InterruptedException, NamingException {
		BatteryLvls batlvls = new BatteryLvls();
		System.out.println(batlvls.getBatteryLvl());
		
		//add batterylvls to the list untill your batterylvl decreases then add one batterylvl, then stops 
		int i = batlvls.getBatteryLvl();
		while(i == batlvls.getBatteryLvl()) {
			batlvls.addBatteryLvl();
			Thread.sleep(10000);
		}
		batlvls.addBatteryLvl();
		System.out.println("Battery lvls liste : " + batlvls.getBatteryLvls().toString());
		
		Context ctx = Main.getContext();
		NamingEnumeration<Binding> e = ctx.listBindings("");
		System.out.println("calculator");
		while(e.hasMore())
		{
			Binding xx = e.nextElement();
			System.out.println("jndi " + xx.getName());
		}
		CommunicationRemote remote=(CommunicationRemote)ctx.lookup("BatMonitor_EAR/BatMonitorEJB/Communication!connexion.CommunicationRemote");
		remote.createBat(batlvls.getBatteryLvl(), " ");
        System.out.println("Enregistrement reussi!");
		
		
	}

}