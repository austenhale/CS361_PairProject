package fa.dfa;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface{
	
	private HashSet<DFAState> statesSet; //holds all the states of the DFA
	private HashMap<DFAState, String> transitionMap; //holds all transitions
	private DFAState startState; //starting state
	private HashSet<DFAState> finalStates; //holds all final states

	@Override
	public void addStartState(String name) {
		startState = new DFAState(name);
		statesSet.add(startState);
		
	}

	@Override
	public void addState(String name) {
		DFAState state = new DFAState(name);
		if (!(state.equals(startState) && !(finalStates.contains(state)))){ //only add state if it is not startState or a finalState
			statesSet.add(state);
		}
		
		
	}

	@Override
	public void addFinalState(String name) {
		DFAState state = new DFAState(name);
		statesSet.add(state);
		finalStates.add(state); //add to final state set as well as overall state set
		
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		return statesSet;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		return finalStates;
	}

	@Override
	public State getStartState() {
		return startState;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DFA complement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean accepts(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

}
