package com.project.eventmanagement.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utility {
    
    public static String getCurrentUser(){


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
        
        
    }
}
