package joprod.jitune.gui.events;

import joprod.jitune.JiTune;

public class JiTuneSubscription {

	private JiTuneEvent           evtType;
	private JiTuneEventListenener listener;
	

	public JiTuneSubscription(JiTuneEvent evtType,
			JiTuneEventListenener listener) {
		super();
		this.evtType = evtType;
		this.listener = listener;

		JiTune.EVENTBROKER.addSubscription(this);
	}


	public void notify(JiTuneEvent evt) {
		if ( evt.equals(evtType) ) {
			System.out.println("EVENT NOTIFICATION: " + evt );
			listener.eventRaised(evt);
		}
	}

	public void unsubscribe() {
		JiTune.EVENTBROKER.removeSubscription(this);
	}

}
