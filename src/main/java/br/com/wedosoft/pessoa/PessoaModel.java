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
import br.com.wedosoft.global.enums.SexoEnum;
import br.com.wedosoft.global.resource.EntityId;
import br.com.wedosoft.imagem.ImagemModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(ListenerAudit.class)
@Table(name = "pessoa")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PessoaModel implements Serializable, EntityId<Long>, Auditado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", unique = true, nullable = false)
    private Long id;

    @Column(name = "nm_pessoa", length = 255, nullable = false)
    private String nome;

    @Column(name = "nm_fantasia", length = 255)
    private String nomeFantasia;

    @Column(name = "nr_rg_pessoa", length = 15)
    private String rg;

    @Column(name = "nr_cpf_cnpj_pessoa", length = 15, nullable = false)
    private String cpfCnpj;

    @Column(name = "dt_nascimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNascimento;

    @Column(name = "in_sexo", nullable = false)
    private SexoEnum sexo;

    @Column(name = "ds_telefone1", length = 20, nullable = false)
    private String telefone1;

    @Column(name = "ds_telefone2", length = 20)
    private String telefone2;

    @Column(name = "ds_email", length = 150)
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fid_endereco")
    private EnderecoModel endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fid_imagem", referencedColumnName = "id_imagem")
    private ImagemModel imagem;

    @Column(name = "nm_responsavel", length = 255)
    private String responsavel;

    @Column(name = "nr_cpf_cnpj_responsavel", length = 15, nullable = false)
    private String cpfCnpjResponsavel;

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

}
