package br.com.wedosoft.global.resource;

import com.google.common.io.ByteStreams;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class EncoderDecoderBase64 {

    public String getBase64Imagem(Blob imagem) {
        if (imagem == null) {
            return null;
        } else {
            byte[] bytes = new byte[0];

            try {
                bytes = ByteStreams.toByteArray(imagem.getBinaryStream());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(bytes);
            return encodeImage;
        }
    }

    public Blob getBlobImagem(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        Blob blob = null;

        try {
            blob = new SerialBlob(decoded);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blob;
    }
}
