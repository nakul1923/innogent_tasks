package com.innogent.student_course.exception.customException;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){

        super(msg);
    }
}
