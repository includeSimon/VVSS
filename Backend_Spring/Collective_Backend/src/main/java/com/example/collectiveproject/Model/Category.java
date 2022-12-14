package com.example.collectiveproject.Model;

import com.example.collectiveproject732.Model.Color;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Category implements Serializable {

    @Id
    @NotNull
    public String nameCategory;

    @NotNull
    public Color color;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "category"
    )
    public List<Task> tasks = new ArrayList<>();


    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String findByNameCategory(){
        return this.nameCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
//                "id=" + id +
                ", nameCategory='" + nameCategory + '\'' +
                ", color=" + color +
                ", tasks=" + tasks +
                '}';
    }
}
