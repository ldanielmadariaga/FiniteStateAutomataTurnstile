package switchandtableturnstile;

import controller.TurnstileController;

public class SwitchTurnstile implements Turnstile {

	private TurnstileState state = TurnstileState.LOCKED;

	private TurnstileController turnstileController;

	public SwitchTurnstile(TurnstileController turnstileController) {
		this.turnstileController = turnstileController;
	}

	public TurnstileState getState() {
		return state;
	}

	public void setState(TurnstileState state) {
		this.state = state;
	}

	public void handleEvent(TurnstileEvent event) {

		switch (state) {
		case LOCKED:
			switch (event) {
			case COIN:
				state = TurnstileState.UNLOCKED;
				turnstileController.unlock();
				break;
			case PASS:
				turnstileController.alarm();
				break;
			}
			break;
		case UNLOCKED:
			switch (event) {
			case COIN:
				turnstileController.thankyou();
				break;
			case PASS:
				state = TurnstileState.LOCKED;
				turnstileController.lock();
				break;
			}
			break;
		}
	}
}
