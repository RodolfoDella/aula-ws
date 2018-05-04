/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.auditoria;

import java.util.Date;

public interface Auditado {

    void setAuditoria(String usuario, Date dataAlterado);

}
