package com.demo.social.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.demo.social.social.SocialUserDetailsImpl;
 
@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
 
    @Autowired
    private UserDetailsService userDetailService;
 
    // After user created by ConnectionSignUpImpl.execute(Connection<?>)
    // This method is called by the Spring Social API.
    @Override
    public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException, DataAccessException {
 
        System.out.println("SocialUserDetailsServiceImpl.loadUserByUserId=" + userName);
 
        // See UserDetailServiceImpl.
        UserDetails userDetails = ((UserDetailsServiceImpl) userDetailService).loadUserByUsername(userName);
 
        return (SocialUserDetailsImpl) userDetails;
    }
 
}
