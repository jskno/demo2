package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final char L2R = '\u202A';
    private static final char R2L = '\u202B';
    private static final char POP = '\u202C';

//    private static final String BEFORE_ARABIC = L2R;//+ ""; + R2L;
//    private static final String AFTER_ARABIC = POP; //+ " ";  + L2R;
    private static final char BEFORE_ARABIC = L2R;//+ ""; + R2L;
    private static final char AFTER_ARABIC = POP; //+ " ";  + L2R;

	@Test
	public void contextLoads() {
	}

	@Test
    public void joiningArabicWithEnglish() {

        String arabicWord = "السَّبت";
        String englishWord = "Success";
        String number = "12323";
        String englishWord2 = "Failure";

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            builder.append(englishWord + " ");
        }

        String completeText = builder.toString() + englishWord + " " + BEFORE_ARABIC + arabicWord + AFTER_ARABIC + number + " "  + englishWord + " "
                + LINE_SEPARATOR + BEFORE_ARABIC + arabicWord + AFTER_ARABIC + number + " " + englishWord2 + " " + number + " " + englishWord + " " + englishWord2;

        System.out.println(completeText);

    }

    @Test
    public void joiningListArabicWithEnglish() {

        List<WordElement> wordsList = getWordsList();

        String text = wordsList.stream().map(wordElement -> wordElement.getWord() + " ").reduce("", String::concat);
        System.out.println(text);

        String text2 = wordsList.stream().map(WordElement::getFormattedWord).reduce("", String::concat);
        System.out.println(text2);

        String text3 = wordsList.stream().map(WordElement::getFormattedWordWithNewLine).reduce("", String::concat);
        System.out.println(text3);

    }

	private List<WordElement> getWordsList() {
	    List wordsList = new ArrayList();
	    wordsList.add(new WordElement("En", "Success"));
        wordsList.add(new WordElement("En", "Failure"));
        wordsList.add(new WordElement("En", "Red"));
        wordsList.add(new WordElement("En", "Blue"));
        wordsList.add(new WordElement("En", "Green"));
        wordsList.add(new WordElement("En", "Yelow"));
        wordsList.add(new WordElement("Ar", "السَّبت"));
        wordsList.add(new WordElement("En", "1234"));
        wordsList.add(new WordElement("En", "Great"));
        wordsList.add(new WordElement("En", "That", true));

        wordsList.add(new WordElement("Ar", "السَّبت"));
        wordsList.add(new WordElement("En", "67567"));
        wordsList.add(new WordElement("En", "List"));
        wordsList.add(new WordElement("En", "Arrays"));
        wordsList.add(new WordElement("En", "645645"));
        wordsList.add(new WordElement("En", "Wrong"));
        wordsList.add(new WordElement("En", "Right", true));

        wordsList.add(new WordElement("En", "435345"));
        wordsList.add(new WordElement("Ar", "السَّبت"));
        //wordsList.add(new WordElement("En", "Success"));




	    return wordsList;
    }

	private class WordElement {

	    private String language;
	    private String word;
	    private boolean isNewLine = false;

	    public WordElement(String language, String word, boolean isNewLine) {
            this(language, word);
	        this.isNewLine = isNewLine;
        }

        public WordElement(String language, String word) {
	        this.language = language;
	        this.word = word;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public boolean isNewLine() {
            return isNewLine;
        }

        public void setNewLine(boolean newLine) {
            isNewLine = newLine;
        }

        public String getFormattedWord() {
	        if("Ar".equals(this.getLanguage())) {
	            return BEFORE_ARABIC + this.getWord() + AFTER_ARABIC + " ";
            }
            return L2R + this.getWord() + " ";
        }

        public String getFormattedWordWithNewLine() {
            if("Ar".equals(this.getLanguage())) {
                return BEFORE_ARABIC + this.getWord() + AFTER_ARABIC + getNewLine();
            }
            return L2R + this.getWord() + getNewLine();
        }

        private String getNewLine() {
	        return this.isNewLine() ? LINE_SEPARATOR : " ";
        }
    }


}
