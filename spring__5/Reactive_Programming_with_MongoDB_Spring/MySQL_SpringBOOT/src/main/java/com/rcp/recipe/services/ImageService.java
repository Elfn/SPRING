package com.rcp.recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Elimane on Jan, 2018, at 08:14
 */
public interface ImageService {
    public void saveImageFile(Long recipeId, MultipartFile file);

}
