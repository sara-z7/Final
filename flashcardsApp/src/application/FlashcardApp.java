package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlashcardApp {
	
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
		sort();
		
		int low = 0;
		int high = flashcards.size() - 1;
		
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (flashcards.get(mid).getTerm().contains(keyword)) {
				return flashcards.get(mid);
			} else if (flashcards.get(mid).getTerm().compareTo(keyword) < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		System.out.println("Flashcard not found!");
		return null;
	}
	
	// insertion sort (alphabetically)
	public void sort () {
		 
		 for (int i = 1; i < flashcards.size(); i++) {
			
			 int j = i - 1;
			 while (j >= 0 && flashcards.get(j).getTerm().compareToIgnoreCase(flashcards.get(i).getTerm()) > 0) {
				flashcards.set(j + 1, flashcards.get(j));
				j--;
			 }
			 
			 flashcards.set(j + 1, flashcards.get(i));
		 }
	}
	
	public void quiz (List<Flashcard> remainingCards, int i, Scanner scan) {
		if (remainingCards.size() == 0) {
			System.out.println("Quiz complete!");
			return;
		}
		
		System.out.println("_________ is " + remainingCards.get(i).getDefinition());
		String answer = scan.nextLine();
		
		if (answer.equals(remainingCards.get(i).getTerm())) {
			System.out.println("Correct!");
			remainingCards.remove(i);
			
			if (remainingCards.size() == 0) {
				System.out.println("Quiz complete!");
				return;
			}
		} else {
			System.out.println("Incorrect. The answer is: " + remainingCards.get(i).getTerm());
		} 
		
		i = (int) (Math.random() * (remainingCards.size()));
		quiz(remainingCards, i, scan);
		
	}
	
	public void saveFlashcards(String file) {
		List<String> lines = new ArrayList<>();
		Path outputFile = Paths.get(file);
		
		for (int i = 0; i < flashcards.size(); i++) {
			lines.add(flashcards.get(i).getTerm() + ": " + flashcards.get(i).getDefinition());
		}
		
		try {
			Files.write(outputFile, lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
					
	}
	
	public List<Flashcard> importFlashcards (String fileName) {
		
		Path file = Paths.get(fileName);
		ArrayList<String> lines = new ArrayList<String>(); // ArrayList that holds the lines from the file

		if (Files.exists(file)) {
								
			try {
				Scanner fileScanner = new Scanner(file);
					
				// add each line to the lines arrayList until there are no more lines
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					lines.add(line);
				}
				fileScanner.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("File not found. Please try again.\n");
		}
		
		for (String line : lines) {
			String[] parts = line.split(": ");
			if (parts.length == 2) { // Ensure the line is properly formatted
                flashcards.add(new Flashcard(parts[0].trim(), parts[1].trim()));
            } else {
                System.out.println("Skipping invalid line: " + line);
            }			
		}
		
		return flashcards;
	}
	
	
	

}
