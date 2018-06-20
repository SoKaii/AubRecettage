package aubervilliers.orange.aubrecettage.model;

import java.io.Serializable;
import java.util.List;

public class Recette implements Serializable {

    private String recetteType;
    private String ticketNumber;
    private String ticketWriter;
    private String roomName;
    private String baieCall;
    private String equipNumber;
    private List<Question> tabQuestions;

    public String getRecetteType() {
        return recetteType;
    }

    public void setRecetteType(String recetteType) {
        this.recetteType = recetteType;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketWriter() {
        return ticketWriter;
    }

    public void setTicketWriter(String ticketWriter) {
        this.ticketWriter = ticketWriter;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBaieCall() {
        return baieCall;
    }

    public void setBaieCall(String baieCall) {
        this.baieCall = baieCall;
    }

    public List<Question> getTabQuestions() {
        return tabQuestions;
    }

    public void setTabQuestions(List<Question> tabQuestions) {
        this.tabQuestions = tabQuestions;
    }

    public String getEquipNumber() {
        return equipNumber;
    }

    public void setEquipNumber(String equipNumber) {
        this.equipNumber = equipNumber;
    }
}
