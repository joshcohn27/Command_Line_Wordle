import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
    private final List<String> words;
    private final Set<String> wordsSet;
    private final int maxAttempts;
    private int attempt;
    private Set<Character> unused;
    private String personalFile;

    public Wordle() throws IOException {
        this.words = new ArrayList<>();
        this.wordsSet = new HashSet<>();
        this.maxAttempts = 6;
        this.attempt = 1;
        this.unused = new HashSet<>();
        this.personalFile = null;
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char letter : letters) {
            unused.add(letter);
        }
        fillWords("words.txt", false);
    }

    public Wordle(String filename) throws IOException {
        this.words = new ArrayList<>();
        this.wordsSet = new HashSet<>();
        this.maxAttempts = 6;
        this.attempt = 1;
        this.unused = new HashSet<>();
        this.personalFile = filename;
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char letter : letters) {
            unused.add(letter);
        }
        fillWords("words.txt", true);
    }

    private void fillWords(String filename, Boolean personal) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fileReader);
        String word = reader.readLine();
        while (word != null) {
            if (!personal) {
                words.add(word); // Array List with all possible words that could be the answer
            }
            wordsSet.add(word); // Set with all valid 5 letter words
            word = reader.readLine();
        }
        reader.close();

        if (personal) {
            FileReader fileReader2 = new FileReader(personalFile);
            BufferedReader reader2 = new BufferedReader(fileReader2);
            String personal_word = reader2.readLine();
            while (personal_word != null) {
                words.add(personal_word);
                wordsSet.add(personal_word);
                personal_word = reader2.readLine();
            }
            reader2.close();
        }
    }

    public void rules() {
        System.out.println("Enter 5 letter words until you guess the Wordle!");
        System.out.println("Letters in the correct spot will be marked with a blank space");
        System.out.println("Letters that are in the word but in the wrong spot will be marked with a dash (-)");
        System.out.println("Incorrect letters are marked with an X\n\n");
    }

    public void play() {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        String guess;
        Boolean correct = false;
        String answer = words.get(random.nextInt(words.size())).toLowerCase();
        rules();
        while (attempt <= maxAttempts) {
            System.out.println(unused);
            System.out.println("Guess " + attempt + ": ");
            guess = in.nextLine().toLowerCase();
            if (!wordsSet.contains(guess)) {
                System.out.println("Invalid guess");
                continue;
            }

            if (guess.equals(answer)) {
                System.out.println();
                correct = true;
                break;
            } else {
                String res = "";
                if (guess.charAt(0) == answer.charAt(0)) {
                    res += " ";
                } else if (answer.contains(Character.toString(guess.charAt(0)))) {
                    res += "-";
                } else {
                    res += "X";
                    if (unused.contains(guess.charAt(0))){
                        unused.remove(guess.charAt(0));
                    }
                }

                if (guess.charAt(1) == answer.charAt(1)) {
                    res += " ";
                } else if (answer.contains(Character.toString(guess.charAt(1)))) {
                    res += "-";
                } else {
                    res += "X";
                    if (unused.contains(guess.charAt(1))){
                        unused.remove(guess.charAt(1));
                    }
                }
                
                if (guess.charAt(2) == answer.charAt(2)) {
                    res += " ";
                } else if (answer.contains(Character.toString(guess.charAt(2)))) {
                    res += "-";
                } else {
                    res += "X";
                    if (unused.contains(guess.charAt(2))){
                        unused.remove(guess.charAt(2));
                    }
                }
                
                if (guess.charAt(3) == answer.charAt(3)) {
                    res += " ";
                } else if (answer.contains(Character.toString(guess.charAt(3)))) {
                    res += "-";
                } else {
                    res += "X";
                    if (unused.contains(guess.charAt(3))){
                        unused.remove(guess.charAt(3));
                    }
                }
                
                if (guess.charAt(4) == answer.charAt(4)) {
                    res += " ";
                } else if (answer.contains(Character.toString(guess.charAt(4)))) {
                    res += "-";
                } else {
                    res += "X";
                    if (unused.contains(guess.charAt(4))){
                        unused.remove(guess.charAt(4));
                    }
                }

                System.out.println(res + "\n");
                
            }
            attempt++;

        }
        if (correct) {
            System.out.println("Congrats! That's it!");
            System.out.println("You guessed it in " + attempt + "/" + maxAttempts);
        } else {
            System.out.println("Sorry! The word was: " + answer);
        }
        in.close();

    }

    public static void main(String[] args) throws IOException {
        // Wordle game = new Wordle();
        // game.play();

        Wordle game = new Wordle("r_words.txt");
        game.play();
    }

}