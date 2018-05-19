package com.iqmsoft.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.iqmsoft.domain.RestErrorInfo;
import com.iqmsoft.exception.DataFormatException;
import com.iqmsoft.exception.ResourceNotFoundException;

import javax.servlet.http.HttpServletResponse;


//@ControllerAdvice?
public abstract class BaseRestHandler implements ApplicationEventPublisherAware {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";

    
    @ExceptionHandler(DataFormatException.class)
    public
    ResponseEntity<RestErrorInfo> handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());

        return new ResponseEntity<RestErrorInfo>(new RestErrorInfo(ex, "You messed up."), HttpStatus.BAD_REQUEST);
    }

   
    @ExceptionHandler(ResourceNotFoundException.class)
    public
    ResponseEntity<RestErrorInfo>
    handleResourceNotFoundException(ResourceNotFoundException ex, 
    		WebRequest request, HttpServletResponse response) {
        log.info("ResourceNotFoundException handler:" + ex.getMessage());

        return new ResponseEntity<RestErrorInfo>(new RestErrorInfo(ex, "Sorry I couldn't find it."), HttpStatus.NOT_FOUND);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

 
    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
        return resource;
    }

}