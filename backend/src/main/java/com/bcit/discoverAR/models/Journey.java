package com.bcit.discoverAR.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name="journeys")
public class Journey extends DateAudit {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ApplicationUser user;

    private ArrayList<Discovery> discoveries = new ArrayList<>();

    public Journey() {
        super();
        this.title = "Untitled";
    }

    public Journey(String title) {
        super();
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public ArrayList<Discovery> getDiscoveries() {
        return discoveries;
    }

    public void setDiscoveries(ArrayList<Discovery> discoveries) {
        this.discoveries = discoveries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(id, journey.id) &&
                Objects.equals(user, journey.getUser()) &&
                Objects.equals(title, journey.getTitle()) &&
                Objects.equals(discoveries, journey.getDiscoveries());
    }
}
