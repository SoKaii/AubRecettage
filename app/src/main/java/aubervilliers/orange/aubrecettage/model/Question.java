package aubervilliers.orange.aubrecettage.model;

import java.io.Serializable;

public class Question implements Serializable {

    private String questionLabel;
    private Boolean buttonYesSelected;
    private String commentary;

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
}
