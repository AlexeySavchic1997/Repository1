package by.alexeysavchic.voter_pet_project.dto.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PollRequest
{
    @NotBlank
    @Size(min = 2, max = 100, message = "question must be between 2 and 100 symbols")
    private String question;
    @Size(max = 300, message = "description must be less than 300 symbols")
    private String description;


    @Min(value = 1, message = "Duration must be at least 1 day")
    @Max(value = 365, message = "Duration can't be more than 365 days")
    private int duration;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


}
