/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.municipio;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pais")
@Data
public class PaisModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais", unique = true, nullable = false)
    private Long id;

    @Column(name = "nm_pais")
    private String nmPais;

    @Column(name = "nm_pais_ptbr")
    private String nmPaisPtBr;

    @Column(name = "sg_pais")
    private String sgPais;

    @Column(name = "nr_bacen")
    private String nrBacen;

    @Column(name = "ds_usuario_ultima_alteracao")
    private String dsUsuarioUltimaAlteracao;

    @Column(name = "dt_ultima_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAlteracao;

}
