package lk.dialog.mou.aspect.aspects;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.aspect.annotations.EnableAuth;
import lk.dialog.mou.aspect.exception.UnAuthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aux072 on 14/09/2018.
 */
@Component
@Aspect
public class AuthAspect {
    @Before("@annotation(lk.dialog.mou.aspect.annotations.EnableAuth)")
    public void authRequest(JoinPoint joinPoint) {
        if (joinPoint.getSignature() instanceof MethodSignature) {
            //start
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            EnableAuth enableAuth = method.getDeclaredAnnotation(EnableAuth.class);
            String [] parameterNames = enableAuth.paramNames();
            Object[] args = joinPoint.getArgs();
            Map<String,Object> paramMap= new HashMap<>();
            for(int i=0;i<parameterNames.length;i++){
                paramMap.put(parameterNames[i],args[i]);
            }
            if(isValidRequest(paramMap)){
                return;
            }else{
                throw new UnAuthorizedException("Un Authorized Request");
            }
        }
    }

    private boolean isValidRequest(Map<String, Object> paramMap) {
        System.out.println("User : "+paramMap.get("user"));
        System.out.println("Password : "+paramMap.get("password"));
        if(null != paramMap.get("user") && null != paramMap.get("password")){
            return true;
        }

        return false;

    }
}
