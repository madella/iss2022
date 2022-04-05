package it.unibo.radarSystem22_4.appl.main;

import java.util.ArrayList;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.IObserver;
import it.unibo.radarSystem22.domain.interfaces.ISonarObservable;

public class SonarObservable implements ISonarObservable {

	private ArrayList<IObserver> subList;
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDistance getDistance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(IObserver arg0) {
		// TODO Auto-generated method stub
		subList.add(arg0);
	}

	@Override
	public void unregister(IObserver arg0) {
		// TODO Auto-generated method stub
		subList.remove(arg0);
	}

	protected void notify(String data) {
		int count = 0; 		
	      while (subList.size() > count) {
	    	 System.out.println(subList.get(count));
	    	 subList.get(count).update(data);;
	         count++;
	      }
	}
}
