package fa.dfa;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;

public class DFA implements DFAInterface{
	
	private LinkedHashSet<DFAState> statesSet; //holds all the states of the DFA
	private DFAState startState; //starting state
	private HashSet<DFAState> finalStates; //holds all final states
	private HashSet<Character> alphabet; //includes all letters used
	
	public DFA() {
		statesSet = new LinkedHashSet<>();
		finalStates = new HashSet<>();
		alphabet = new HashSet<>();
	}

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
		DFAState state1 = get(new DFAState(fromState), statesSet);
		DFAState state2 = get(new DFAState(toState), statesSet);
		if (!alphabet.contains(onSymb)) {
			alphabet.add(onSymb); //if the symbol isn't in alphabet, include it
		}
		
		state1.addTransition(onSymb, state2);
		
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
		return alphabet;
	}

	@Override
	public DFA complement() {
		DFA complement = new DFA();
		HashSet<DFAState> statesSetComplement = new HashSet<>(); //holds all the states of the DFA
		DFAState startStateComplement = startState; //starting state
		HashSet<DFAState> finalStatesComplement = new HashSet<>(); //holds all final states
		
		//Building up lists
		for (DFAState state : statesSet) { //go through every state in original
			if (finalStates.contains(state)) { //if the original state had this state as a final state
				statesSetComplement.add(state); //add it to just the states set
			}else { //else if it wasn't in final states (i.e. wasn't a final state of the original)
				finalStatesComplement.add(state); //add it to the complement's final states
				statesSetComplement.add(state); //also add final state to overall state collection
			}
			
		}
		
		//setting lists
		for (DFAState state : finalStatesComplement) {
			complement.addFinalState(state.getName());
		}
		complement.addStartState(startStateComplement.getName());
		for (DFAState state : statesSetComplement) {
			complement.addState(state.getName());
		}
		
		return complement;
	}

	@Override
	public boolean accepts(String s) {
		//s is an input string that goes through the DFA machine
		//returns true if valid string, false otherwise
		
		//starts on start state
		//calls getToState to go to next state, if valid
			//if getToState returns null, return false
		//if valid move, update current state to new state
		//continue process until no more characters
		//check if current state is a final state
			//if it is, return true, else false
		
		DFAState currentState = startState;
		State nextState;
		for (int i = 0; i < s.length(); i++) {
			nextState = getToState(currentState, s.charAt(i));
			if (nextState == null) {
				return false;
			}
			currentState = (DFAState) nextState;
		}
		if (finalStates.contains(currentState)) {
			return true;
		}
		return false;
	}

	@Override
	public State getToState(DFAState from, char onSymb) {
		return from.transition(onSymb);
	}
	
	public String toString() {
		String formatedString = "";
	
		//printing the whole set of states
		formatedString += "Q = { ";
		Object[] statesArray  = statesSet.toArray();
		for (int i = 0; i < statesArray.length; i++) {
			formatedString += statesArray[i] + " ";
		}
		formatedString += "} \n";
		
		//printing alphabet
		formatedString += "Sigma = { ";
		Object[] alphabetArray = alphabet.toArray();
		for (int i = 0; i < alphabetArray.length; i++) {
			formatedString += alphabetArray[i] + " ";
		}
		formatedString += "} \n";
		
		//printing delta
		formatedString += "delta =  \n\t\t";
		for (int i = 0; i < alphabetArray.length; i++) { //for every character in alphabet
			formatedString += alphabetArray[i] + "\t";
			
		}
		formatedString += "\n\t";
		
		for (int j = 0; j < statesArray.length; j++) { //for every state
			formatedString += statesArray[j] + "\t";
			for (int i = 0; i < alphabetArray.length; i++) {
				//adds the resulting state of the transition to the output string
				formatedString += getToState((DFAState)statesArray[j], (Character)alphabetArray[i]); 
				formatedString += "\t";
			}
			//get transition given state and character
			
			formatedString += "\n\t"; //go onto next state
		}
		formatedString += "\n";
		//printing start state
		formatedString += "q0 = " + startState + "\n";
		
		//printing final state
		formatedString += "F = { ";
		Object[] finalArray = finalStates.toArray();
		for (int i = 0; i < finalArray.length; i++) {
			formatedString += finalArray[i] + " ";
		}
		formatedString += "} \n";
		
		
		
		return formatedString;
	}
	
	/**
	 * This will get an element from the set
	 * @param stateToGet
	 * @param set
	 * @return
	 */
	private DFAState get(DFAState stateToGet, Set<DFAState> set) {
		for (DFAState state : set) {
			if (state.toString().equals((stateToGet.toString()))) {
				return state;
			}
		}
		return null;
	}

}
