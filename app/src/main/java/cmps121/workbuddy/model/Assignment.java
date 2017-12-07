package cmps121.workbuddy.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Assignment {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("due_at")
    private String dueAt;
    @SerializedName("points_possible")
    private Integer pointsPossible;
    @SerializedName("has_submitted_submissions")
    private Boolean hasSubmittedSubmissions;



    public Assignment(Integer id,
                      String name,
                      String description
    ) {
        this.id = id;
        this.name =  name;
        this.description = description;
        this.dueAt = dueAt;
        this.pointsPossible = pointsPossible;
        this.hasSubmittedSubmissions = hasSubmittedSubmissions;



    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }
    public Integer getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Integer pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    public Boolean getHasSubmittedSubmissions() {
        return hasSubmittedSubmissions;
    }

    public void setHasSubmittedSubmissions(Boolean hasSubmittedSubmissions) {
        this.hasSubmittedSubmissions = hasSubmittedSubmissions;
    }






}

