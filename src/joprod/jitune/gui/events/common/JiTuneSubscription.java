package joprod.jitune.gui.events.common;

public class JiTuneSubscription {
	private JiTuneEventListenener listener;

	protected JiTuneSubscription(JiTuneEventListenener listener) {
		super();
		this.listener = listener;
	}
	
}
