/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wedosoft.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public JacksonObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        return Squiggly.init(new ObjectMapper(), new RequestSquigglyContextProvider() {

            @Override
            protected String customizeFilter(String filter, HttpServletRequest request, Class beanClass) {
                filter = request.getParameter("fields");

                //SquigglyRequestHolder.removeRequest();                
                filter = "link,firstResult,maxResults,numberOfElements,numberOfListElements,content[" + filter + "]," + filter;

                return filter;
            }
        });
    }

}
