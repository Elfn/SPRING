package com.rcp.recipe.services;

import com.rcp.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by Elimane on Jan, 2018, at 05:28
 */
public interface UnitOfMeasureService {
    public Set<UnitOfMeasureCommand> listAllUoms();
}
