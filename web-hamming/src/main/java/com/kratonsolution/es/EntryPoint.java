package com.kratonsolution.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
public class EntryPoint
{
    public static void main( String[] args )
    {
    	SpringApplication.run(EntryPoint.class, args);
    }
}
