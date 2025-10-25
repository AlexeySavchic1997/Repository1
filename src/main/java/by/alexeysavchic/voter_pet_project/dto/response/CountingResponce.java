package by.alexeysavchic.voter_pet_project.dto.response;

public class CountingResponce
{
    String option;

    Integer count;

    public CountingResponce() {
    }

    public CountingResponce(String option, Integer count) {
        this.option = option;
        this.count = count;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
