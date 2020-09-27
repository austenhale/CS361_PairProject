package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fa.State;

/**
 * This class implements a DFAState, which holds each transition for a state, as well as each
 * DFAState's name.
 * @author austen hale, sammie fullmer
 *
 */
public class DFAState extends State{
	
	private String name;
	private HashMap<Character, DFAState> transitionMap; //holds all transitions, unsure if correct paramters
	

	public DFAState(String name) {
		this.name = name;
		transitionMap = new HashMap<Character, DFAState>();
	}

	/**
	 * Adds a transition to the DFAState.
	 * @param onSymb - character required to transition to toState
	 * @param toState - state that will be transition to.
	 */
	public void addTransition(char onSymb, DFAState toState) {
		transitionMap.put(onSymb, toState);
		
	}
	/**
	 * Returns the state that is transitioned to with the given character.
	 * @param onSymb - character required to transition to state
	 * @return - returns state that is reached by onSymb, null if no valid transition state
	 */
	public State transition(char onSymb) {
		if (transitionMap.containsKey(onSymb)) {
			return transitionMap.get(onSymb);
		}
		return null; //maybe return something other than null to let them know invalid transition
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the map containing the transitions.
	 * @return transitionMap
	 */
	public HashMap getMap() {
		return transitionMap;
	}

}
