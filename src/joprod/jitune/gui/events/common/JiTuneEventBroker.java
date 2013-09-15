package joprod.jitune.gui.events.common;


public class JiTuneEventBroker {

	public void notify(JiTuneEvent evt) {
		// TODO notif
	}
	
	public JiTuneSubscription subscribe(JiTuneEventListenener listener) {
		JiTuneSubscription sub = new JiTuneSubscription(listener);
		// TODO addListener
		return sub;
	}
}
