/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.Setter;

import javax.ejb.EJBException;
import java.io.IOException;

public class PartialJsonObjectPatch implements ObjectPatch {

    @Setter
    private ObjectMapper objectMapper;

    @Setter
    private JsonNode patch;

    @Override
    public <T> T apply(T target) {

        ObjectReader reader = objectMapper.readerForUpdating(target);

        try {
            return reader.readValue(patch);
        } catch (IOException e) {
            throw new EJBException(e);
        }
    }

}
