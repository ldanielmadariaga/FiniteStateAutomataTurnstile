package switchandtableturnstile;

import java.util.ArrayList;

import controller.TurnstileController;

public class TableTurnstile implements Turnstile {

	private TurnstileState state = TurnstileState.LOCKED;
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	private TurnstileController controller;

	public TableTurnstile(TurnstileController controller) {
		this.controller = controller;
		String unlock = "unlock";
		String alarm = "alarm";
		String thankYou = "thank you";
		String lock = "lock";

		AddTransition(TurnstileState.LOCKED, TurnstileEvent.COIN, TurnstileState.UNLOCKED, unlock);
		AddTransition(TurnstileState.LOCKED, TurnstileEvent.PASS, TurnstileState.LOCKED, alarm);
		AddTransition(TurnstileState.UNLOCKED, TurnstileEvent.COIN, TurnstileState.UNLOCKED, thankYou);
		AddTransition(TurnstileState.UNLOCKED, TurnstileEvent.PASS, TurnstileState.LOCKED, lock);
	}

	@Override
	public TurnstileState getState() {
		return state;
	}

	@Override
	public void setState(TurnstileState state) {
		this.state = state;
	}

	@Override
	public void handleEvent(TurnstileEvent e) {
		for (Transition transition : transitions) {
			if (state == transition.getStartState() && e == transition.getTrigger()) {
				state = transition.getEndState();
				transition.action();

			}
		}

	}

	private void AddTransition(TurnstileState start, TurnstileEvent event, TurnstileState end, String action) {
		transitions.add(new Transition(start, event, end, action, controller));

	}

	private class Transition {

		private TurnstileState startState;
		private TurnstileState endState;
		private TurnstileEvent trigger;
		private String action;
		private TurnstileController controller;

		public Transition(TurnstileState start, TurnstileEvent e, TurnstileState end, String a,
				TurnstileController controller) {
			this.startState = start;
			this.trigger = e;
			this.endState = end;
			this.action = a;
			this.controller = controller;
		}

		public TurnstileState getStartState() {
			return startState;
		}

		public TurnstileState getEndState() {
			return endState;
		}

		public TurnstileEvent getTrigger() {
			return trigger;
		}

		public void action() {
			if (action.equals("unlock")) {
				controller.unlock();
			} else if (action.equals("alarm")) {
				controller.alarm();
			} else if (action.equals("thank you")) {
				controller.thankyou();
			} else if (action.equals("lock")) {
				controller.lock();
			}
		}
	}
}
