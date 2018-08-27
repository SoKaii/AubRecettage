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
    private String equipElevation;

    private List<Question> tabQuestions;
    private Recap recap;

    public Recette(String recetteType, String ticketNumber, String ticketWriter, String roomName, String baieCall, String equipNumber, List<Question> tabQuestions) {
        this.recetteType = recetteType;
        this.ticketNumber = ticketNumber;
        this.ticketWriter = ticketWriter;
        this.roomName = roomName;
        this.baieCall = baieCall;
        this.equipNumber = equipNumber;
        this.tabQuestions = tabQuestions;
        this.recap = null;
    }

    public Recette(String recetteType, String roomName, String baieCall, String equipNumber, String equipElevation,List<Question> tabQuestions) {
        this.recetteType = recetteType;
        this.roomName = roomName;
        this.baieCall = baieCall;
        this.equipNumber = equipNumber;
        this.equipElevation = equipElevation;
        this.tabQuestions = tabQuestions;
        this.recap = null;
    }

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

    public Question getQuestion1()
    {
        return tabQuestions.get(0);
    }

    public Recap getRecap() {
        return recap;
    }

    public void setRecap(Recap recap) {
        this.recap = recap;
    }
}
