package at.spengergasse.entities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    Scanner scanner = new Scanner(System.in);
    List<Question> questionList=new ArrayList<>();
    int nextQuestion=0;

    public Quiz() {
        EntityManager em = Persistence.createEntityManagerFactory("demo")
                .createEntityManager();
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
        List<Question> questions = query.getResultList();

    }

    public boolean showQuestionsandAnswers() {
        Question q;
        if (nextQuestion >= questionList.size()) {
            finish();
            return false;
        }
        for(int i = 0;i<questionList.size();i++)
        {
            q = questionList.get(i);
            System.out.println(q);

            System.out.println(i+1 + ") " + q.getAnswerList());
            System.out.println("Please enter the number of the correct answer: ");
            int answer = scanner.nextInt();
            checkCorrectAnswer(q, answer);

        }
        return true;
    }

    private void checkCorrectAnswer(Question q, int answer) {
        if (q.getAnswerList().get(answer-1).isCorrect()) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
        }
    }

    private void finish() {
    }
}
