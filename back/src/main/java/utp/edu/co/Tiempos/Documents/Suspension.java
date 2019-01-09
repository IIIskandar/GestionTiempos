/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.co.Tiempos.Documents;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author C-Lug
 */
@Document(collection = "Suspensiones")
public class Suspension {
    @Id
    private String id;
    @DBRef(lazy = true)
    private List<WC> WCs;
    @DBRef(lazy = true)
    private List<Snack> snacks;
    @DBRef(lazy = true)
    private List<Meeting> meetings;
    private Long tiempoWC;
    private Long tiempoSnacks;
    private Long tiempoMeeting;
    
    protected Suspension(){
        this.WCs = new ArrayList<>();
        this.meetings = new ArrayList<>();
        this.snacks = new ArrayList<>();
    }

    public Suspension(List<WC> WCs, List<Snack> snacks, List<Meeting> meetings, Long tiempoWC, Long tiempoSnacks, Long tiempoMeeting) {
        this.WCs = WCs;
        this.snacks = snacks;
        this.meetings = meetings;
        this.tiempoWC = tiempoWC;
        this.tiempoSnacks = tiempoSnacks;
        this.tiempoMeeting = tiempoMeeting;
    }

    public String getId() {
        return id;
    }
    
    public List<WC> getWCs() {
        return WCs;
    }

    public void setWCs(List<WC> WCs) {
        this.WCs = WCs;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<Snack> snacks) {
        this.snacks = snacks;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Long getTiempoWC() {
        return tiempoWC;
    }

    public void setTiempoWC(Long tiempoWC) {
        this.tiempoWC = tiempoWC;
    }

    public Long getTiempoSnacks() {
        return tiempoSnacks;
    }

    public void setTiempoSnacks(Long tiempoSnacks) {
        this.tiempoSnacks = tiempoSnacks;
    }

    public Long getTiempoMeeting() {
        return tiempoMeeting;
    }

    public void setTiempoMeeting(Long tiempoMeeting) {
        this.tiempoMeeting = tiempoMeeting;
    }
    
}
