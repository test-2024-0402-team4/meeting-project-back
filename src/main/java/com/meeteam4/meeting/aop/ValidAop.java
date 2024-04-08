package com.meeteam4.meeting.aop;

import com.meeteam4.meeting.dto.SignupUserDto;
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
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        BeanPropertyBindingResult bindingResult = null;

//        System.out.println(args.getClass());
        for (Object arg : args) {
//            System.out.println(arg.getClass());
            if (arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }


        // ----------------------------------------------------------------------
//        // 중복확인
//        System.out.println(methodName);
        if (methodName.equals("signupUser")) {
            SignupUserDto signupUserDto = null;

            for (Object arg : args) {
                if (arg.getClass() == SignupUserDto.class) {
                    signupUserDto = (SignupUserDto) arg;
                }
            }

            if (userMapper.findByUsername(signupUserDto.getUsername()) != null) {
//                System.out.println(userMapper.findByUsername(signupUserDto.getUsername()));
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 아이디입니다.");
                bindingResult.addError(objectError);
//                System.out.println(objectError.getDefaultMessage());
            }

            if(userMapper.findByNickname(signupUserDto.getNickname()) != null) {
                ObjectError objectError = new FieldError("nickname", "nickname", "이미 존재하는 닉네임입니다.");
                bindingResult.addError(objectError);
            }

            if(userMapper.findByEmail(signupUserDto.getEmail()) != null) {
                ObjectError objectError = new FieldError("email", "email", "이미 존재하는 이메일입니다.");
                bindingResult.addError(objectError);
            }
        }

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
//              System.out.println(errorMap.getOrDefault(fieldName, message));
            }
            throw new ValidException(errorMap);
        }

        return proceedingJoinPoint.proceed();
    }
}
