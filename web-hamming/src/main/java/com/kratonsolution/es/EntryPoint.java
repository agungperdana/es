package com.kratonsolution.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class EntryPoint
{
    public static void main( String[] args )
    {
    	SpringApplication.run(EntryPoint.class, args);
    }
}
