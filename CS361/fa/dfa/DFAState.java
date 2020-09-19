package fa.dfa;

import java.util.HashMap;

import fa.State;

public class DFAState extends State{
	
	private String name;
	private HashMap<Character, State> transitionMap; //holds all transitions, unsure if correct paramters

	public DFAState(String name) {
		this.name = name;
	}

	public void addTransition(char onSymb, State toState) {
		transitionMap.put(onSymb, toState);
		
	}

}
