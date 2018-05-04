/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.municipio;

import lombok.Data;
import br.com.wedosoft.global.auditoria.Auditado;
import br.com.wedosoft.global.auditoria.ListenerAudit;
import br.com.wedosoft.global.resource.EntityId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "municipio")
@Data
@EntityListeners(ListenerAudit.class)
public class MunicipioModel implements Serializable, EntityId<Long>, Auditado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio", unique = true, nullable = false)
    private Long id;

    @Column(name = "nm_municipio", length = 120)
    private String nmMunicipio;

    @Column(name = "cd_ibge")
    private Long cdIbge;

    @ManyToOne
    @JoinColumn(name = "fid_estado", referencedColumnName = "id_estado")
    private EstadoModel estado;

    @Column(name = "ds_usuario_ultima_alteracao")
    private String dsUsuarioUltimaAlteracao;

    @Column(name = "dt_ultima_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAlteracao;

    @Override
    public void setAuditoria(String usuario, Date dataAlterado) {
        this.dsUsuarioUltimaAlteracao = usuario;
        this.dtUltimaAlteracao = dataAlterado;
    }
}
