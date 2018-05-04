/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.auditoria;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Stateless
public class ListenerAudit {

    @Inject
    private HttpServletRequest httpRequest;

    @PrePersist
    @PreUpdate
    public void setDadosPessoa(Auditado audit) {
        audit.setAuditoria(httpRequest.getHeader("usuario"), new Date());

    }
}
