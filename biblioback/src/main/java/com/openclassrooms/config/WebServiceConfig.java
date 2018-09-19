package com.openclassrooms.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "test")
    public DefaultWsdl11Definition TestDefaultWsdl11Definition(XsdSchema testSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TestPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://test.ws.biblioback.openclassrooms.com");
        wsdl11Definition.setSchema(testSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema testSchema() {
        return new SimpleXsdSchema(new ClassPathResource("test.xsd"));
    }


    /*
    @Bean(name = "book")
    public DefaultWsdl11Definition BookDefaultWsdl11Definition(XsdSchema booksSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BooksPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://book.ws.biblioback.openclassrooms.com");
        wsdl11Definition.setSchema(booksSchema);
        return wsdl11Definition;
    }

    @Bean(name = "borrowing")
    public DefaultWsdl11Definition BorrowingDefaultWsdl11Definition(XsdSchema borrowingsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BorrowingsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://borrowing.ws.biblioback.openclassrooms.com");
        wsdl11Definition.setSchema(borrowingsSchema);
        return wsdl11Definition;
    }

    @Bean(name = "appuser")
    public DefaultWsdl11Definition AppUserDefaultWsdl11Definition(XsdSchema appUserSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AppUsersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://appuser.ws.biblioback.openclassrooms.com");
        wsdl11Definition.setSchema(appUserSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema booksSchema() {
        return new SimpleXsdSchema(new ClassPathResource("book.xsd"));
    }

    @Bean
    public XsdSchema appUserSchema() {
        return new SimpleXsdSchema(new ClassPathResource("appuser.xsd"));
    }

    @Bean
    public XsdSchema borrowingsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("borrowing.xsd"));
    }
    */
}