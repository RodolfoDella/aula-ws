/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverterEnum implements AttributeConverter<SexoEnum, String> {

    @Override
    public String convertToDatabaseColumn(SexoEnum tipoSexo) {
        if (tipoSexo == null) {
            return null;
        } else {
            switch (tipoSexo) {
                case MASCULINO:
                    return "M";
                case FEMININO:
                    return "F";
                case INDEFINIDO:
                    return "I";
                default:
                    throw new IllegalArgumentException("Valor desconhecido : " + tipoSexo);
            }
        }
    }

    @Override
    public SexoEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        } else {
            switch (dbData) {
                case "F":
                    return SexoEnum.FEMININO;
                case "M":
                    return SexoEnum.MASCULINO;
                case "I":
                    return SexoEnum.INDEFINIDO;
                default:
                    throw new IllegalArgumentException("Valor desconhecido: " + dbData);
            }
        }
    }
}
