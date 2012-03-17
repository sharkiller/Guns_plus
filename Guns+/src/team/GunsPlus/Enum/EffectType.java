package team.GunsPlus.Enum;

import java.util.HashMap;



public enum EffectType {

	EXPLOSION(), LIGHTNING(), SMOKE(), POTION(), PUSH(), DRAW(), PLACE(), BREAK(), SPAWN(), FIRE(), NONE(); 
	
	
	private HashMap<String, Object> arguments = new HashMap<String, Object>();
	
	public HashMap<String, Object> getArguments() {
		return arguments;
	}
	
	public Object getArgument(String id){
		return this.arguments.containsKey(id)?this.arguments.get(id):null;
	}

	public void addArgument(String id, Object argument) {
		this.arguments.put(id, argument);
	}
	
	
}