/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.resource;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SearchCriteria {
    @NonNull
    private String key;

    @NonNull
    private String operation;

    @NonNull
    private Object value;
}
