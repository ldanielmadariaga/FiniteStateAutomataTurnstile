package state;

public class UnlockedTurnstileState implements TurnstileState {

	@Override
	public void Coin(Turnstile t) {
		t.thankYou();
	}

	@Override
	public void Pass(Turnstile t) {
		t.setLocked();
		t.lock();
	}

}
