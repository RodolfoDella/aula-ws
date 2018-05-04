package br.com.wedosoft.global.resource;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SortCriteria {

    @NonNull
    private String order;

    @NonNull
    private String key;
}
