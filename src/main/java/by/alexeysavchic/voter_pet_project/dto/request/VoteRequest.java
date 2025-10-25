package by.alexeysavchic.voter_pet_project.dto.request;

public class VoteRequest
{
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
