package state;

import controller.TurnstileController;

public class Turnstile {

	private static TurnstileState lockedState = new LockedTurnstileState();
	private static TurnstileState unlockedState = new UnlockedTurnstileState();

	private TurnstileController turnstileController;
	private TurnstileState state = unlockedState;

	public Turnstile(TurnstileController turnstileController) {
		this.turnstileController = turnstileController;
	}

	public void coin() {
		state.Coin(this);
	}

	public void pass() {
		state.Pass(this);
	}

	public void setLocked() {
		state = lockedState;
	}

	public void setUnlocked() {
		state = unlockedState;
	}

	public Boolean isLocked() {
		return state == lockedState;
	}

	public Boolean isUnlocked() {
		return state == unlockedState;
	}

	public void alarm() {
		turnstileController.alarm();
	}

	public void thankYou() {
		turnstileController.thankyou();
	}

	public void lock() {
		turnstileController.lock();
	}

	public void unlock() {
		turnstileController.unlock();
	}

}
