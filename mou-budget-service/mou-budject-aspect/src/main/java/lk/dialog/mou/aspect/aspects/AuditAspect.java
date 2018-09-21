package lk.dialog.mou.aspect.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.dialog.mou.aspect.annotations.EnableAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roshane on 1/3/18.
 */
@Component
@Aspect
public class AuditAspect {

    private static final Logger auditLog = LoggerFactory.getLogger("auditLog");
    private static final String auditLogSeparator = "|";
    private static final ObjectMapper om = new ObjectMapper();
    private static final ObjectMapper omResult = new ObjectMapper();
    //@Before("@annotation(lk.dialog.mou.aspect.annotations.EnableAudit)")
    @Around("@annotation(lk.dialog.mou.aspect.annotations.EnableAudit)")
    public Object printAuditDetails(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        Map<String,Object> paramMap= new HashMap<>();
        StringWriter swInputParams = new StringWriter();
        StringWriter swResultReturned = new StringWriter();
        if (proceedingJoinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();
            EnableAudit enableAudit = method.getDeclaredAnnotation(EnableAudit.class);
            String description = enableAudit.description();
            CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
            String[] parameters = codeSignature.getParameterNames();
            Object[] args = proceedingJoinPoint.getArgs();

            Map<String,Object> methodParamValueMap= new HashMap<>();
            paramMap.put("description", description);
            paramMap.put("action", signature.getName());
            for(int i=0; i<parameters.length; i++){
                String paramName = parameters[i];
                if(paramName != null){
                    if(!paramName.equals("user")){
                        methodParamValueMap.put(parameters[i],args[i]);
                    }
                }
                if(parameters[i].equals("user"))
                paramMap.put(parameters[i],args[i]);
            }



            try {
                long startTime = System.currentTimeMillis();
                result = proceedingJoinPoint.proceed();
                long endTime = System.currentTimeMillis();
                long timeTaken = (endTime - startTime);
                paramMap.put("result", result);
                paramMap.put("startTime", startTime);
                paramMap.put("endTime", endTime);
                paramMap.put("duration", timeTaken);
                om.writeValue(swInputParams,methodParamValueMap);
                omResult.writeValue(swResultReturned, paramMap.get("result"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            //String user = SecurityContextHolder.getContext().getAuthentication().getName();
            //auditLog.info("API CALL : {}", sw.toString());
        }
        auditLog.debug("User : {} {} action : {} {} Description : {} {} InputParams : {} {} StartTime : {} {} EndTime : {} {} DurationInMS : {} {} Returned : {}",
                paramMap.get("user"),
                auditLogSeparator,
                paramMap.get("action"),
                auditLogSeparator,
                paramMap.get("description"),
                auditLogSeparator,
                swInputParams,
                auditLogSeparator,
                paramMap.get("startTime"),
                auditLogSeparator,
                paramMap.get("endTime"),
                auditLogSeparator,
                paramMap.get("duration"),
                auditLogSeparator,
                swResultReturned);
        return result;
    }
}
