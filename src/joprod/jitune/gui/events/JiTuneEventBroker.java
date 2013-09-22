package joprod.jitune.gui.events;

import java.util.ArrayList;
import java.util.List;


public class JiTuneEventBroker {

	public void notify(JiTuneEvent evt) {
		for( JiTuneSubscription sub: subscriptions ) {
			sub.notify(evt);
		}
	}
	
	private List<JiTuneSubscription> subscriptions = new ArrayList<JiTuneSubscription>();

	protected void addSubscription(JiTuneSubscription sub) {
		subscriptions.add(sub);
	}

	protected void removeSubscription(JiTuneSubscription sub) {
		subscriptions.remove(sub);
	}
}
