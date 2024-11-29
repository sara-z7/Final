package application;

public class Flashcard {
	
	// fields
	private String term;
	private String definition;
	
	// constructors
	
	public Flashcard(String term, String definition) {
		this.term = term;
		this.definition = definition;
	}
	
	public Flashcard () {
		term = "";
		definition = "";
	}

	// getters
	
	public String getTerm() {
		return term;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	// setters
	public void setTerm(String term) {
		this.term = term;
	}
	
	public void setDefinition (String definition) {
		this.definition = definition;
	}
	
}
