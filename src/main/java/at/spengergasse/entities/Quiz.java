

package at.spengergasse.entities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

    public class Quiz {
        private List<Question> questions;
        private int correctAnswers = 0;
        private Scanner scanner = new Scanner(System.in);

        public Quiz() {
            EntityManager em = Persistence.createEntityManagerFactory("demo").createEntityManager();
            TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
            this.questions = query.getResultList();
        }

        public void start() {
            for (Question q : questions) {
                showQuestionandAnswers(q);
            }
            showResults();
        }

        private void showQuestionandAnswers(Question q) {
            System.out.println("\n" + q.getText());

            List<Answer> answers = q.getAnswerList();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ") " + answers.get(i).getText());
            }

            System.out.print("Deine Antwort (1-" + answers.size() + "): ");
            int choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < answers.size() && answers.get(choice).isCorrect()) {
                System.out.println("Richtig!");
                correctAnswers++;
            } else {
                System.out.println("Falsch! Die richtige Antwort war:");
                for (Answer a : answers) {
                    if (a.isCorrect()) {
                        System.out.println(a.getText());
                    }
                }
            }
        }

        private void showResults() {
            System.out.println("\nDu hast " + correctAnswers + " von " + questions.size() + " Fragen richtig beantwortet.");
            double percentage = ((double)correctAnswers / questions.size()) * 100;  //Da habe ich nachgeschaut wie man das richtig schreibt (double).
            System.out.println("Erfolgsrate: " + String.format("%.2f", percentage) + "%");  //Hier habe ich auch nachgeschaut wie man da macht.
        }
    }




