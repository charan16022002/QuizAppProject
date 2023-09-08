import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static final int TOTAL_QUESTIONS = 5;
    private static final int TIME_LIMIT_SECONDS = 10;
    private static final String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the 'Red Planet'?",
            "What is the largest mammal in the world?",
            "Who wrote the play 'Romeo and Juliet'?",
            "What is the chemical symbol for gold?"
    };
    private static final String[][] options = {
            { "Paris", "Berlin", "London", "Madrid" },
            { "Mars", "Venus", "Earth", "Jupiter" },
            { "Blue whale", "African elephant", "Giraffe", "Hippopotamus" },
            { "William Shakespeare", "Charles Dickens", "Jane Austen", "George Orwell" },
            { "Au", "Ag", "Fe", "Hg" }
    };
    private static final int[] correctAnswers = { 0, 0, 0, 0, 0 };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        System.out.println("Welcome to the Quiz!");

        for (currentQuestionIndex = 0; currentQuestionIndex < TOTAL_QUESTIONS; currentQuestionIndex++) {
            displayQuestion();
            startTimer(timer);

            int selectedOption = scanner.nextInt();

            timer.cancel(); // Cancel the timer when the user submits an answer

            if (selectedOption == correctAnswers[currentQuestionIndex]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: "
                        + options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
            }
        }

        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + TOTAL_QUESTIONS);

        scanner.close();
    }

    private static void displayQuestion() {
        System.out.println("Question " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            System.out.println((i + 1) + ". " + options[currentQuestionIndex][i]);
        }
        System.out.print("Choose an option (1-" + options[currentQuestionIndex].length + "): ");
    }

    private static void startTimer(Timer timer) {
        timer.schedule(new TimerTask() {
            private int secondsLeft = TIME_LIMIT_SECONDS;

            @Override
            public void run() {
                if (secondsLeft > 0) {
                    System.out.println("Time remaining: " + secondsLeft + " seconds");
                    secondsLeft--;
                } else {
                    System.out.println("Time's up!");
                    timer.cancel(); // Stop the timer
                }
            }
        }, 0, 1000);
    }
}
