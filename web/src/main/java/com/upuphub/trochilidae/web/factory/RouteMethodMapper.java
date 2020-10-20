package com.upuphub.trochilidae.web.factory;

import com.upuphub.trochilidae.core.factory.ClassFactory;
import com.upuphub.trochilidae.web.annotation.*;
import com.upuphub.trochilidae.web.common.entity.RequestMethodDetail;
import com.upuphub.trochilidae.web.common.entity.RequestMappingDetail;
import com.upuphub.trochilidae.web.common.lang.HttpMethod;
import com.upuphub.trochilidae.web.common.util.UrlUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 路由与控制器方法的映射
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-18 16:05
 **/
public class RouteMethodMapper {
    /**
     * request url -> target request method.
     * eg: "^/rest/[\u4e00-\u9fa5_a-zA-Z0-9]+/?$" -> RestController.get(java.lang.Integer)
     */
    private static final Map<HttpMethod, Map<String, RequestMappingDetail>> REQUEST_METHOD_MAPPINGS = new HashMap<>(8);

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
                String rootUrl = restController.value();
                for (Method method : methods) {
                    Annotation[] annotations = method.getAnnotations();
                    if(null == annotations ||0 == annotations.length){
                        continue;
                    }
                    List<RequestMappingDetail> requestMappingDetailList = buildRequestMappingDetailListByMethod(method);
                    if(requestMappingDetailList.isEmpty()){
                        continue;
                    }
                    for (RequestMappingDetail requestMappingDetail : requestMappingDetailList) {
                        if(!"".equals(rootUrl)){
                            requestMappingDetail.setPath(Arrays.stream(
                                    requestMappingDetail.getPath())
                                    .map(path-> String.format("%s/%s",rootUrl,path))
                                    .toArray(String[]::new));
                        }
                        if(requestMappingDetail.getPath().length == 0){
                            String path = "/";
                            String formatUrl = UrlUtil.formatUrl(path);
                            REQUEST_METHOD_MAPPINGS.get(requestMappingDetail.getHttpMethod()).put(formatUrl,requestMappingDetail);
                            REQUEST_URL_MAPPINGS.get(requestMappingDetail.getHttpMethod()).put(formatUrl,path);
                        }else{
                            for (String path : requestMappingDetail.getPath()) {
                                REQUEST_METHOD_MAPPINGS.get(requestMappingDetail.getHttpMethod()).put(path,requestMappingDetail);
                                REQUEST_URL_MAPPINGS.get(requestMappingDetail.getHttpMethod()).put(UrlUtil.formatUrl(path),path);
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isRequestMappingAnnotation(Annotation annotation){
        return annotation instanceof GetMapping || annotation instanceof PostMapping
                || annotation instanceof PutMapping || annotation instanceof DeleteMapping;

    }

    private static List<RequestMappingDetail> buildRequestMappingDetailListByMethod(Method method){
        List<RequestMappingDetail> requestMappingDetailList = new ArrayList<>();
        for (Annotation annotation : method.getAnnotations()) {
            if(isRequestMappingAnnotation(annotation)){
                RequestMappingDetail requestMappingDetail = new RequestMappingDetail();
                if(annotation instanceof GetMapping){
                    requestMappingDetail.setHttpMethod(HttpMethod.GET);
                    requestMappingDetail.setConsumes(((GetMapping) annotation).consumes());
                    requestMappingDetail.setHeaders(((GetMapping) annotation).headers());
                    requestMappingDetail.setProduces(((GetMapping) annotation).produces());
                    requestMappingDetail.setName(((GetMapping) annotation).name());
                    requestMappingDetail.setPath(((GetMapping) annotation).path());
                }else if(annotation instanceof PutMapping){
                    requestMappingDetail.setHttpMethod(HttpMethod.PUT);
                    requestMappingDetail.setConsumes(((PutMapping) annotation).consumes());
                    requestMappingDetail.setHeaders(((PutMapping) annotation).headers());
                    requestMappingDetail.setProduces(((PutMapping) annotation).produces());
                    requestMappingDetail.setName(((PutMapping) annotation).name());
                    requestMappingDetail.setPath(((PutMapping) annotation).path());
                }else if(annotation instanceof PostMapping){
                    requestMappingDetail.setHttpMethod(HttpMethod.POST);
                    requestMappingDetail.setConsumes(((PostMapping) annotation).consumes());
                    requestMappingDetail.setHeaders(((PostMapping) annotation).headers());
                    requestMappingDetail.setProduces(((PostMapping) annotation).produces());
                    requestMappingDetail.setName(((PostMapping) annotation).name());
                    requestMappingDetail.setPath(((PostMapping) annotation).path());
                }else if(annotation instanceof DeleteMapping){
                    requestMappingDetail.setHttpMethod(HttpMethod.DELETE);
                    requestMappingDetail.setConsumes(((DeleteMapping) annotation).consumes());
                    requestMappingDetail.setHeaders(((DeleteMapping) annotation).headers());
                    requestMappingDetail.setProduces(((DeleteMapping) annotation).produces());
                    requestMappingDetail.setName(((DeleteMapping) annotation).name());
                    requestMappingDetail.setPath(((DeleteMapping) annotation).path());
                }
                requestMappingDetailList.add(requestMappingDetail);
            }
        }
        return requestMappingDetailList;
    }

    public static RequestMethodDetail getRequestMethodDetail(String requestPath,RequestMappingDetail requestMappingDetail) {
        RequestMethodDetail requestMethodDetail = new RequestMethodDetail();
        requestMethodDetail.build(requestPath,requestMappingDetail);
        return requestMethodDetail;
    }
}
