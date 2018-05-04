package br.com.wedosoft.global.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ResultList<T> {

    private int numberOfListElements;
    private List<T> listElements = new ArrayList<T>();

}
