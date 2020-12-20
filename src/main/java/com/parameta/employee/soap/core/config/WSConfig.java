package com.parameta.employee.soap.core.config;

import lombok.extern.slf4j.Slf4j;
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

import javax.annotation.PostConstruct;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Configuration
@EnableWs
@Slf4j
public class WSConfig  extends WsConfigurerAdapter
{

    @PostConstruct
    private void init()
    {
        try(final DatagramSocket socket = new DatagramSocket())
        {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            var ip = socket.getLocalAddress().getHostAddress();
            log.info("The ip of this machine is: {}", ip);
        }
        catch (Exception ex)
        {
            log.error("IP ERROR", ex);
        }
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "employees")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema articlesSchema)
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("EmployeePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://parameta-employee-soap.herokuapp.com/ws/gen");
        wsdl11Definition.setSchema(articlesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema articlesSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xsds/employee.xsd"));
    }
}
