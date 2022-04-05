

import it.unibo.is.interfaces.IObserver;

public interface ISonarObservable  extends ISonar{
	  void register( IObserver obs );
	  void unregister( IObserver obs );
	  void notify( Integer data);
}
