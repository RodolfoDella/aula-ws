/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.municipio;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "estado")
@Data
public class EstadoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", unique = true, nullable = false)
    private Long id;

    @Column(name = "nm_estado", length = 75)
    @NotNull
    private String nmEstado;

    @Column(name = "sg_estado", length = 2)
    private String sgEstado;

    @Column(name = "cd_ibge")
    private Long cdIbge;

    @Column(name = "nr_sl")
    private Long nrSl;

    @Column(name = "cd_ddd", length = 50)
    private String cdDDD;

    @ManyToOne
    @JoinColumn(name = "fid_pais", referencedColumnName = "id_pais")
    private PaisModel pais;

    @Column(name = "ds_usuario_ultima_alteracao")
    private String dsUsuarioUltimaAlteracao;

    @Column(name = "dt_ultima_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAlteracao;


}
