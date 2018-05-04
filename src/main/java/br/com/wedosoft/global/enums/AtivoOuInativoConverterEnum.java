package br.com.wedosoft.global.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AtivoOuInativoConverterEnum implements AttributeConverter<AtivoOuInativoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AtivoOuInativoEnum ativoOuInativo) {
        if (ativoOuInativo== null) {
            return null;
        } else {
            switch (ativoOuInativo) {
                case ATIVO:
                    return 1;
                case INATIVO:
                    return 0;
                default:
                    throw new IllegalArgumentException("Valor desconhecido : " + ativoOuInativo);
            }
        }
    }

    @Override
    public AtivoOuInativoEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        } else {
            switch (dbData) {
                case 1:
                    return AtivoOuInativoEnum.ATIVO;
                case 0:
                    return AtivoOuInativoEnum.INATIVO;
                default:
                    throw new IllegalArgumentException("Valor desconhecido: " + dbData);
            }
        }
    }
}
