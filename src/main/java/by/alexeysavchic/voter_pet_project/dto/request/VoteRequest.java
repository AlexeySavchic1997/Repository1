package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VoteRequest
{
    @NotBlank
    @Size(min = 2, max = 100, message = "question must be between 2 and 100 symbols")
    String pollName;

    String OptionName;

    public VoteRequest() {
    }

    public VoteRequest(String pollName, String optionName) {
        this.pollName = pollName;
        OptionName = optionName;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public String getOptionName() {
        return OptionName;
    }

    public void setOptionName(String optionName) {
        OptionName = optionName;
    }
}
