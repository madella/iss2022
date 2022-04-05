package it.unibo.radarSystem22_4.appl.main;

import java.util.ArrayList;
import it.unibo.radarSystem22.domain.interfaces.*;
import it.unibo.radarSystem22_4.appl.RadarSystemConfig;
import it.unibo.radarSystem22_4.appl.proxy.SonarProxy;
import it.unibo.radarSystem22_4.comm.ProtocolType;
import it.unibo.radarSystem22_4.comm.interfaces.IApplication;
import it.unibo.radarSystem22_4.comm.utils.BasicUtils;
import it.unibo.radarSystem22_4.comm.utils.ColorsOut;
import it.unibo.radarSystem22_4.comm.utils.CommSystemConfig;

public class UseSonarFromPc extends SonarObservable implements IApplication {
 	private ISonar  sonar ;
 	private int trigger;
 	
	@Override
	public String getName() {
		return this.getClass().getName() ; 
	}
	
	@Override
	public void doJob(String domainConfig, String systemConfig ) {
		setup(domainConfig,systemConfig);
		configure();
		execute();
		terminate();
	}
	
	public void setup( String domainConfig, String systemConfig )  {
		ColorsOut.outappl(" === " + getName() + " ===", ColorsOut.MAGENTA);
		RadarSystemConfig.DLIMIT           = 80;
		RadarSystemConfig.ctxServerPort    = 8756;
		CommSystemConfig.protcolType = ProtocolType.udp;
	}
	
	protected void configure() {		
		String host           = RadarSystemConfig.raspAddr;
		ProtocolType protocol = CommSystemConfig.protcolType;
		String ctxport        = ""+RadarSystemConfig.ctxServerPort;
		sonar    		      = new SonarProxy("sonarPxy", host, ctxport, protocol );
 	}
	
	private void checkUpdate(int last,int d) {
		int result=Math.abs(last-d);
		if (result > trigger) {
			notify(String.valueOf(result));
		}
	}

	public void execute() {
		ColorsOut.out("activate the sonar");
		int last=0;
		sonar.activate();
		BasicUtils.delay(1000);
		//BasicUtils.waitTheUser();
		boolean sonarActive = sonar.isActive();
		ColorsOut.out("sonarActive="+sonarActive);
		if( sonarActive ) {
			for( int i=1; i<=3; i++ ) {
				int d = sonar.getDistance().getVal();
				checkUpdate(last,d);
				last=d;
				ColorsOut.out("sonar distance="+d);
				BasicUtils.delay(1000);			
			}
		}
    }

	public void terminate() {
		sonar.deactivate();
  		//System.exit(0);
	}	
	
	public static void main( String[] args) throws Exception {
		new UseSonarFromPc().doJob(null,null);
 	}
	
}
