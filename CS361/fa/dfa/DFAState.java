package fa.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fa.State;

public class DFAState extends State{
	
	private String name;
	private HashMap<Character, State> transitionMap; //holds all transitions, unsure if correct paramters
	private HashSet<Character> alphabet;

	public DFAState(String name) {
		this.name = name;
		alphabet = new HashSet<>();
		transitionMap = new HashMap<Character, State>();
	}

	public void addTransition(char onSymb, State toState) {
		transitionMap.put(onSymb, toState);
		alphabet.add(onSymb);
		
	}
	
	public Set<Character> getAlphabet() {
		return alphabet;
	}

}
