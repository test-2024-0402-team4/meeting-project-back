package com.meeteam4.meeting.aop;

import com.meeteam4.meeting.dto.SignupStudentReqDto;
import com.meeteam4.meeting.dto.SignupTeacherReqDto;
import com.meeteam4.meeting.entity.Student;
import com.meeteam4.meeting.exception.ValidException;
import com.meeteam4.meeting.repository.UserMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidAop {

    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(com.meeteam4.meeting.aop.annotation.ValidAspect)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;

//        System.out.println(args.getClass());
        for(Object arg : args) {
//            System.out.println(arg.getClass());
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }


        // ----------------------------------------------------------------------
        // 중복확인 - 학생
//        System.out.println(methodName);
//        if(methodName.equals("signupStudent")) {
//            SignupStudentReqDto signupStudentReqDto = null;
//
//            for(Object arg : args) {
//                if(arg.getClass() == SignupStudentReqDto.class) {
//                    signupStudentReqDto = (SignupStudentReqDto) arg;
//                }
//            }
//            if(userMapper.findStudentUsername(signupStudentReqDto.getUsername()) != null) {
////                System.out.println(userMapper.findStudentUsername(signupStudentReqDto.getUsername()));
//                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 아이디입니다.");
//                bindingResult.addError(objectError);
////                System.out.println(bindingResult);
//            }
//            if(userMapper.findStudentNickname(signupStudentReqDto.getNickname()) != null) {
//                ObjectError objectError = new FieldError("nickname", "nickname", "이미 존재하는 닉네임입니다.");
//                bindingResult.addError(objectError);
//            }
//            if(userMapper.findStudentEmail(signupStudentReqDto.getEmail()) != null) {
//                ObjectError objectError = new FieldError("email", "email","이미 존재하는 이메일입니다.");
//                bindingResult.addError(objectError);
//            }
//
//        }
        // 중복확인 - 선생
//        if(methodName.equals("signupTeacher")) {
//            SignupTeacherReqDto signupTeacherReqDto = null;
//
//            for(Object arg : args) {
//                if(arg.getClass() == SignupTeacherReqDto.class) {
//                    signupTeacherReqDto = (SignupTeacherReqDto) arg;
//                }
//            }
//            if(userMapper.findTeacherUsername(signupTeacherReqDto.getUsername()) != null) {
//                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 아이디입니다");
//                bindingResult.addError(objectError);
//                System.out.println(objectError.getDefaultMessage());
//            }
//            if(userMapper.findTeacherNickname(signupTeacherReqDto.getNickname()) != null) {
//                ObjectError objectError = new FieldError("nickname", "nickname", "이미 존재하는 닉네임입니다.");
//                bindingResult.addError(objectError);
//                System.out.println(objectError.getDefaultMessage());
//            }
//            if(userMapper.findTeacherEmail(signupTeacherReqDto.getEmail()) != null) {
//                ObjectError objectError = new FieldError("email", "email","이미 존재하는 이메일입니다.");
//                bindingResult.addError(objectError);
//                System.out.println(objectError.getDefaultMessage());
//            }
//        }



        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
            }
            throw new ValidException(errorMap);
        }

        return proceedingJoinPoint.proceed();
    }
}
