package compulsory.server;

import compulsory.model.Player;
import compulsory.model.Question;
import compulsory.repository.PlayerRepository;
import compulsory.repository.QuestionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientThread extends Thread {

    private Socket socket;
    private GameServer server;

    private PlayerRepository playerRepository = new PlayerRepository();
    private QuestionRepository questionRepository = new QuestionRepository();

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String request = in.readLine();

            if (request == null) {
                socket.close();
                return;
            }

            if (request.equals("stop")) {
                out.println("Serverul s-a oprit.");
                server.stop();
            } else if (request.startsWith("player ")) {
                addPlayer(request, out);
            } else if (request.startsWith("question ")) {
                addQuestion(request, out);
            } else if (request.equals("players")) {
                showPlayers(out);
            } else if (request.equals("questions")) {
                showQuestions(out);
            } else {
                out.println("Comanda necunoscuta: " + request);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlayer(String request, PrintWriter out) {
        String name = request.substring("player ".length());

        Player player = new Player(name);
        playerRepository.save(player);

        out.println("Jucator salvat in baza de date: " + player.getName());
    }

    private void addQuestion(String request, PrintWriter out) {
        String data = request.substring("question ".length());

        String[] parts = data.split("\\|");

        if (parts.length != 2) {
            out.println("Format gresit. Foloseste: question text intrebare|raspuns corect");
            return;
        }

        String text = parts[0];
        String correctAnswer = parts[1];

        Question question = new Question(text, correctAnswer);
        questionRepository.save(question);

        out.println("Intrebare salvata in baza de date: " + question.getText());
    }

    private void showPlayers(PrintWriter out) {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            out.println("Nu exista jucatori in baza de date.");
            return;
        }

        StringBuilder result = new StringBuilder("Jucatori: ");

        for (Player player : players) {
            result.append(player.getName())
                    .append("(score=")
                    .append(player.getScore())
                    .append(") ");
        }

        out.println(result.toString());
    }

    private void showQuestions(PrintWriter out) {
        List<Question> questions = questionRepository.findAll();

        if (questions.isEmpty()) {
            out.println("Nu exista intrebari in baza de date.");
            return;
        }

        StringBuilder result = new StringBuilder("Intrebari: ");

        for (Question question : questions) {
            result.append("[")
                    .append(question.getText())
                    .append(" -> ")
                    .append(question.getCorrectAnswer())
                    .append("] ");
        }

        out.println(result.toString());
    }
}

//cand clientul trimite player Ana=> serverul face:
//Player player = new Player(name);
//playerRepository.save(player);

//daca trimite question Capitala Romaniei?|Bucuresti=>
//serverul creaza o intrebare si o salveaza