/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.imagem;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.wedosoft.global.auditoria.Auditado;
import br.com.wedosoft.global.auditoria.ListenerAudit;
import br.com.wedosoft.global.resource.EncoderDecoderBase64;
import br.com.wedosoft.global.resource.EntityId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Entity
@EntityListeners(ListenerAudit.class)
@Table(name = "imagem")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ImagemModel implements Serializable, EntityId<Long>, Auditado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem", unique = true, nullable = false)
    private Long id;

    @Column(name = "ds_imagem")
    private String descricao;

    @Lob
    @Column(name = "ol_imagem", nullable = false)
    @Setter(AccessLevel.NONE)
    private Blob imagem;

    @Column(name = "tp_imagem", nullable = false)
    private String tipo;

    @Column(name = "ds_usuario_ultima_alteracao", nullable = false)
    private String dsUsuarioUltimaAlteracao;

    //@Version //pode disparar uma OptimisticLockException
    @Column(name = "dt_ultima_alteracao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAlteracao;

    @Override
    public void setAuditoria(String usuario, Date dataAlterado) {
        this.dsUsuarioUltimaAlteracao = usuario;
        this.dtUltimaAlteracao = dataAlterado;
    }

    public String getImagem() {
        return new EncoderDecoderBase64().getBase64Imagem(imagem);
    }

    public void setImagem(String imagem) {
        this.imagem = new EncoderDecoderBase64().getBlobImagem(imagem);
    }
}
