package com.kratonsolution.es.web.util;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class Page {
 
    public static int getPage(int rowPerPage, int total) {
        
        int page = 1;
        
        if(total > rowPerPage) {
            
            int mod = total % rowPerPage;
            page = (total-mod)/rowPerPage+(mod > 0?1:0);
        }
        
        return page;
    }
}
