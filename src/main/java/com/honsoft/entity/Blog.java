package com.honsoft.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blogs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    private int blogId;
    private String blogTitle;
    private String blogCreator;
    private int yearOfPost;
// No-Args and Parametrized Constructor
//Getters and Setters
}
