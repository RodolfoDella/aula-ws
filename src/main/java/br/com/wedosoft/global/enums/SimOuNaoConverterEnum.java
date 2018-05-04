package br.com.wedosoft.global.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SimOuNaoConverterEnum implements AttributeConverter<SimOuNaoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SimOuNaoEnum simOuNao) {
        if (simOuNao == null) {
            return null;
        } else {
            switch (simOuNao) {
                case SIM:
                    return 1;
                case NAO:
                    return 0;
                default:
                    throw new IllegalArgumentException("Valor desconhecido : " + simOuNao);
            }
        }
    }

    @Override
    public SimOuNaoEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        } else {
            switch (dbData) {
                case 1:
                    return SimOuNaoEnum.SIM;
                case 0:
                    return SimOuNaoEnum.NAO;
                default:
                    throw new IllegalArgumentException("Valor desconhecido: " + dbData);
            }
        }
    }
}
