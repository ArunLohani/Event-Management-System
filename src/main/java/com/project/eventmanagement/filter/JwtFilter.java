package com.project.eventmanagement.filter;

import com.project.eventmanagement.model.User;
import com.project.eventmanagement.repository.UserRepo;
import com.project.eventmanagement.services.impl.UserDetailServiceImpl;
import com.project.eventmanagement.utility.AuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailServiceImpl userDetailService;
    private final AuthUtil authUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      try{


          final String tokenRequestHeader = request.getHeader("Authorization");

          if(tokenRequestHeader == null || !tokenRequestHeader.startsWith("Bearer")){
              filterChain.doFilter(request,response);
              return;
          }

          String token = tokenRequestHeader.split(" ")[1];
          String email = authUtil.getEmailFromToken(token);
          System.out.println("TOKEN " + token );
          System.out.println("EMAIL " + email );

          if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

            ;

              UserDetails userDetails = userDetailService.loadUserByUsername(email) ;



              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                      new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }

          filterChain.doFilter(request,response);
      } catch (Exception e) {

          handlerExceptionResolver.resolveException(request,response,null,e);
      }

    }
}
