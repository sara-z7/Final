package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlashcardApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	List<Flashcard> flashcards = new ArrayList<>();
	File file;
	
	public void addFlashcard(String term, String definition) {
		flashcards.add(new Flashcard(term, definition));
	}
	
	public void editTerm(int i, String newTerm) {
		flashcards.get(i).setTerm(newTerm);
	}
	
	public void editDefinition(int i, String newDefinition) {
		flashcards.get(i).setDefinition(newDefinition);
	}
	
	public void deleteFlashcard(int i) {
		flashcards.remove(i);
	}
	
	
	// binary search 
	public Flashcard search(String keyword) {
		
		int low = 0;
		int high = flashcards.size() - 1;
		int mid = flashcards.size() / 2;
		
		
		while (low <= high) {
			if (flashcards.get(mid).getTerm().contains(keyword)) {
				return flashcards.get(mid);
			} else if (flashcards.get(mid).getTerm().compareTo(keyword) < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		return new Flashcard();
	}
	
	// insertion sort (alphabetically)
	public void sort () {
		 
		 for (int i = 1; i < flashcards.size(); i++) {
			
			 int j = i - 1;
			 while (j >= 0 && flashcards.get(j).getTerm().compareToIgnoreCase(flashcards.get(i).getTerm()) > 0) {
				flashcards.set(j + 1, flashcards.get(j));
			 }
			 
			 flashcards.set(j + 1, flashcards.get(i));
		 }
	}
	
	
	

}
