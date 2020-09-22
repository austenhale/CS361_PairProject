package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fa.State;

public class DFAState extends State{
	
	private String name;
	private HashMap<Character, State> transitionMap; //holds all transitions, unsure if correct paramters
	

	public DFAState(String name) {
		this.name = name;
		transitionMap = new HashMap<Character, State>();
	}

	public void addTransition(char onSymb, State toState) {
		transitionMap.put(onSymb, toState);
		
	}
	public State transition(char onSymb) {
		if (transitionMap.containsKey(onSymb)) {
			return transitionMap.get(onSymb);
		}
		return null; //maybe return something other than null to let them know invalid transition
	}

}
