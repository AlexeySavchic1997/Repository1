package by.alexeysavchic.voter_pet_project.dto.response;

public class AllPollsResponse
{
    Long id;

    String Question;

    public AllPollsResponse() {
    }

    public AllPollsResponse(Long id, String question) {
        this.id = id;
        Question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
