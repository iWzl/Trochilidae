package com.upuphub.trochilidae.web.factory;

import com.upuphub.trochilidae.core.factory.ClassFactory;
import com.upuphub.trochilidae.web.annotation.*;
import com.upuphub.trochilidae.web.common.entity.MethodDetail;
import com.upuphub.trochilidae.web.common.lang.HttpMethod;
import com.upuphub.trochilidae.web.common.util.UrlUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 路由与控制器方法的映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-18 16:05
 **/
public class RouteMethodMapper {
    /**
     * request url -> target method.
     * eg: "^/rest/[\u4e00-\u9fa5_a-zA-Z0-9]+/?$" -> RestController.get(java.lang.Integer)
     */
    private static final Map<HttpMethod, Map<String, Method>> REQUEST_METHOD_MAPPINGS = new HashMap<>(8);

    /**
     * formatted get request url -> original url
     * eg : "^/url/[\u4e00-\u9fa5_a-zA-Z0-9]+/?$" -> /url/{id}
     */
    private static final Map<HttpMethod, Map<String, String>> REQUEST_URL_MAPPINGS = new HashMap<>(8);

    private static void init(){
        REQUEST_METHOD_MAPPINGS.put(HttpMethod.GET,new HashMap<>(128));
        REQUEST_METHOD_MAPPINGS.put(HttpMethod.POST,new HashMap<>(128));
        REQUEST_METHOD_MAPPINGS.put(HttpMethod.DELETE,new HashMap<>(128));
        REQUEST_METHOD_MAPPINGS.put(HttpMethod.PUT,new HashMap<>(128));

        REQUEST_URL_MAPPINGS.put(HttpMethod.GET,new HashMap<>(128));
        REQUEST_URL_MAPPINGS.put(HttpMethod.POST,new HashMap<>(128));
        REQUEST_URL_MAPPINGS.put(HttpMethod.DELETE,new HashMap<>(128));
        REQUEST_URL_MAPPINGS.put(HttpMethod.PUT,new HashMap<>(128));
    }

    public static void loadRoutes() {
        init();
        Set<Class<?>> classes = ClassFactory.CLASSES.get(RestController.class);
        for (Class<?> aClass : classes) {
            RestController restController = aClass.getAnnotation(RestController.class);
            if (null != restController) {
                Method[] methods = aClass.getDeclaredMethods();
                String baseUrl = restController.value();
                for (Method method : methods) {
                    Annotation[] annotations = method.getAnnotations();
                    if(null == annotations ||0 == annotations.length){
                        continue;
                    }
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        GetMapping getMapping = method.getAnnotation(GetMapping.class);
                        if (null != getMapping) {
                            String url = baseUrl + getMapping.value();
                            insertRequestMapping(HttpMethod.GET,url,method);
                        }
                    }
                    if (method.isAnnotationPresent(PostMapping.class)) {
                        PostMapping postMapping = method.getAnnotation(PostMapping.class);
                        if (null != postMapping) {
                            String url = baseUrl + postMapping.value();
                            insertRequestMapping(HttpMethod.POST,url,method);
                        }
                    }
                    if (method.isAnnotationPresent(DeleteMapping.class)) {
                        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                        if (null != deleteMapping) {
                            String url = baseUrl + deleteMapping.value();
                            insertRequestMapping(HttpMethod.DELETE,url,method);
                        }
                    }
                    if (method.isAnnotationPresent(PutMapping.class)) {
                        PutMapping putMapping = method.getAnnotation(PutMapping.class);
                        if (null != putMapping) {
                            String url = baseUrl + putMapping.value();
                            insertRequestMapping(HttpMethod.PUT,url,method);
                        }
                    }
                }
            }
        }
    }

    public static MethodDetail getMethodDetail(String requestPath, HttpMethod httpMethod) {
        MethodDetail methodDetail = new MethodDetail();
        methodDetail.build(requestPath, REQUEST_METHOD_MAPPINGS.get(httpMethod), REQUEST_URL_MAPPINGS.get(httpMethod));
        return methodDetail;
    }

    private static void insertRequestMapping(HttpMethod httpMethod,String url,Method method){
        String formattedUrl = UrlUtil.formatUrl(url);
        REQUEST_METHOD_MAPPINGS.get(httpMethod).put(formattedUrl,method);
        REQUEST_URL_MAPPINGS.get(httpMethod).put(formattedUrl,url);
    }
}
