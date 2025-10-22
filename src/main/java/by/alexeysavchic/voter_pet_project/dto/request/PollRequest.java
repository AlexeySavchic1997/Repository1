package by.alexeysavchic.voter_pet_project.dto.request;


import by.alexeysavchic.voter_pet_project.entity.Option;

import java.util.List;

public class PollRequest
{
    private String question;

    private String description;

    private List<String> options;

    public PollRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
