package com.todo.test.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Elimane on Feb, 2019, at 18:58
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Todo {

    @Id
    private Long id;
    private String description;
    private boolean state;
    private Date creationDate;

}
