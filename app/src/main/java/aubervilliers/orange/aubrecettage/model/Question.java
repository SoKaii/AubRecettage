package aubervilliers.orange.aubrecettage.model;

import java.io.Serializable;

public class Question implements Serializable {

    private String questionLabel;
    private Boolean buttonYesSelected;
    private String commentary;
    private Boolean isOpenQuestion;

    public Boolean getButtonYesSelected() {
        return buttonYesSelected;
    }

    public void setButtonYesSelected(Boolean buttonYeSelecteds) {
        this.buttonYesSelected = buttonYesSelected;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(String questionLabel) {
        this.questionLabel = questionLabel;
    }

    public Boolean getOpenQuestion() {
        return isOpenQuestion;
    }

    public void setOpenQuestion(Boolean openQuestion) {
        isOpenQuestion = openQuestion;
    }
}
