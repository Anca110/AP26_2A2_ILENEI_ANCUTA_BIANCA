package advanced.bot;

import advanced.model.Question;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class LLMBotPlayer implements BotPlayer {
    private BotDifficulty difficulty;
    private OpenAIClient client;

    public LLMBotPlayer(BotDifficulty difficulty) {
        this.difficulty = difficulty;
        this.client = OpenAIOkHttpClient.fromEnv();
    }

    @Override
    public String getName() {
        return "LLMBot-" + difficulty;
    }

    @Override
    public String answerQuestion(Question question) {
        String prompt = buildPrompt(question);

        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .model(ChatModel.GPT_5_2)
                    .input(prompt)
                    .build();

            Response response = client.responses().create(params);

            return response.output().get(0)
                    .asMessage()
                    .content().get(0)
                    .asOutputText()
                    .text()
                    .trim();

        } catch (Exception e) {
            System.out.println("API-ul extern nu este disponibil. Botul foloseste fallback local.");
            return fallbackAnswer(question);
        }
    }

    //pt cazuri in care api ul nu e disponibil
    private String fallbackAnswer(Question question) {
        String text = question.getText().toLowerCase();

        if (text.contains("capitala romaniei")) {
            return "Bucuresti";
        }

        if (text.contains("capitala frantei")) {
            return "Paris";
        }

        if (text.contains("2+2")) {
            return "4";
        }

        if (text.contains("limbaj")) {
            return "Java";
        }

        if (text.contains("saptamana")) {
            return "7";
        }

        return "Nu stiu";
    }

    private String buildPrompt(Question question) {
        if (difficulty == BotDifficulty.EASY) {
            return "Raspunde la intrebarea urmatoare scurt, dar ai voie sa gresesti uneori. Intrebare: "
                    + question.getText();
        }

        if (difficulty == BotDifficulty.MEDIUM) {
            return "Raspunde la intrebarea urmatoare cat mai corect, folosind un raspuns scurt. Intrebare: "
                    + question.getText();
        }

        return "Raspunde foarte precis la intrebarea urmatoare. Scrie doar raspunsul final, fara explicatii. Intrebare: "
                + question.getText();
    }
}