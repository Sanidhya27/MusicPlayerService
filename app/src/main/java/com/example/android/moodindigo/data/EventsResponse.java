package com.example.android.moodindigo.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mrunz on 17/9/17.
 */

public class EventsResponse {

    @SerializedName("Competitions")
    private List<GenresResponse> competitions;
    @SerializedName("Arts and Ideas")
    private List<GenresResponse> artsAndIdeas;
    @SerializedName("Proshows")
    private List<GenresResponse> proshows;
    @SerializedName("Workshops")
    private List<GenresResponse> workshops;
    @SerializedName("Informals")
    private List<GenresResponse> informals;
    @SerializedName("Concerts")
    private List<GenresResponse> concerts;

    public List<GenresResponse> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<GenresResponse> competitions) {
        this.competitions = competitions;
    }

    public List<GenresResponse> getArtsAndIdeas() {
        return artsAndIdeas;
    }

    public void setArtsAndIdeas(List<GenresResponse> artsAndIdeas) {
        this.artsAndIdeas = artsAndIdeas;
    }

    public List<GenresResponse> getProshows() {
        return proshows;
    }

    public void setProshows(List<GenresResponse> proshows) {
        this.proshows = proshows;
    }

    public List<GenresResponse> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<GenresResponse> workshops) {
        this.workshops = workshops;
    }

    public List<GenresResponse> getInformals() {
        return informals;
    }

    public void setInformals(List<GenresResponse> informals) {
        this.informals = informals;
    }

    public List<GenresResponse> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<GenresResponse> concerts) {
        this.concerts = concerts;
    }
}
