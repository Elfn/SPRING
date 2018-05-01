package com.rcp.recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
//COMMAND OBJECT TO TRANSFERT DATA FROM AND TO WEB FORMS
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
