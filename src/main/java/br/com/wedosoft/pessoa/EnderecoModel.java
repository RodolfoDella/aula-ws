/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.pessoa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.wedosoft.global.auditoria.Auditado;
import br.com.wedosoft.global.auditoria.ListenerAudit;
import br.com.wedosoft.global.resource.EntityId;
import br.com.wedosoft.municipio.MunicipioModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(ListenerAudit.class)
@Table(name = "endereco")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class EnderecoModel implements Serializable, EntityId<Long>, Auditado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", unique = true, nullable = false)
    private Long id;

    @Column(name = "ds_logradouro", length = 256, nullable = false)
    private String logradouro;

    @Column(name = "ds_numero", length = 10)
    private String numero;

    @Column(name = "ds_complemento", length = 50)
    private String dsComplemento;

    @Column(name = "ds_bairro", length = 256, nullable = false)
    private String bairro;

    @Column(name = "ds_cep", length = 15, nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "fid_municipio", referencedColumnName = "id_municipio")
    private MunicipioModel municipio;

    @Column(name = "ds_usuario_ultima_alteracao", nullable = false)
    private String dsUsuarioUltimaAlteracao;

    @Column(name = "dt_ultima_alteracao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimaAlteracao;

    @Override
    public void setAuditoria(String usuario, Date dataAlterado) {
        this.dsUsuarioUltimaAlteracao = usuario;
        this.dtUltimaAlteracao = dataAlterado;
    }

}
