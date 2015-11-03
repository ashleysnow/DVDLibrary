package com.swcguild.dvdlibrarymvc.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Dvd {

    private int dvdId;

    @NotEmpty(message = "You must supply a value for title.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String title;

    @NotEmpty(message = "You must supply a value for release date.")
    @Length(max = 50, message = "Release date must be no more than 50 characters in length.")
    private String releaseDate;

    @NotEmpty(message = "You must supply a value for director.")
    @Length(max = 50, message = "Director must be no more than 50 characters in length.")
    private String director;

    @NotEmpty(message = "You must supply a value for studio.")
    @Length(max = 50, message = "Studio must be no more than 50 characters in length.")
    private String studio;

    private String comments;

    public Dvd() {
    }

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.dvdId;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + Objects.hashCode(this.releaseDate);
        hash = 37 * hash + Objects.hashCode(this.director);
        hash = 37 * hash + Objects.hashCode(this.studio);
        hash = 37 * hash + Objects.hashCode(this.comments);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (this.dvdId != other.dvdId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {

            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        return true;
    }

}
