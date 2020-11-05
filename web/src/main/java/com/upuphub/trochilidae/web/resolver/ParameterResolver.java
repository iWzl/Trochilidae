package com.upuphub.trochilidae.web.resolver;

import com.upuphub.trochilidae.web.common.entity.RequestParamManager;

import java.lang.reflect.Parameter;

/**
 * 参数装换解析
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-04 10:48
 **/
public interface ParameterResolver {

    /**
     * 将请求中的参数属性，解析成需要处理的Bean的参数属性
     *
     * @param parameter 需要解析成的属性信息
     * @param requestParamManager 请求需要的属性参数
     * @return 转换处理后的解析处理结果
     */
    Object resolve(Parameter parameter,RequestParamManager requestParamManager);
}
