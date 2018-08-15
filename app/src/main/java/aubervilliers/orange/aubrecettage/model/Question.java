package aubervilliers.orange.aubrecettage.model;

import java.io.Serializable;

public class Question implements Serializable {

    private String questionLabel;
    private boolean buttonYesSelected;
    private boolean buttonNoSelected;
    private String commentary;
    private boolean isOpenQuestion;

    public boolean getButtonYesSelected() {
        return buttonYesSelected;
    }

    public void setButtonYesSelected(boolean buttonYeSelecteds) {
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

    public boolean getOpenQuestion() {
        return isOpenQuestion;
    }

    public void setOpenQuestion(boolean openQuestion) {
        isOpenQuestion = openQuestion;
    }

    public boolean getButtonNoSelected() {
        return buttonNoSelected;
    }

    public void setButtonNoSelected(boolean buttonNoSelected) {
        this.buttonNoSelected = buttonNoSelected;
    }
}
