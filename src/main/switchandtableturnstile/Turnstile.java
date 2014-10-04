package switchandtableturnstile;


public interface Turnstile {

	public TurnstileState getState();

	public void setState(TurnstileState state);

	public void handleEvent(TurnstileEvent event);
}
