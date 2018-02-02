package com.li.tools.utils.classhandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lijuntao
 * @date 2016-9-23
 */
public class MethodParse {
	private Set<String> importClassName = new HashSet<String>();
	private String[] modifiers;
	private String methodReturn;
	private String methodName;
	private String[] methodParameters;
	private String[] zhiXingParameters;

	public String getMethSignatrueString() {
		StringBuffer buffer = new StringBuffer();
		for (String s : modifiers)
			buffer.append(s + " ");
		buffer.append(methodReturn + " ");
		buffer.append(methodName + "");
		int i = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (methodParameters != null && methodParameters.length != 0) {
			zhiXingParameters = new String[methodParameters.length];
			for (String s : methodParameters) {
				i++;
				if (i == 1)
					buffer.append("(");
				buffer.append(s + " ");
				Integer integer = map.get(s);
				if (integer != null && integer != 0) {
					integer++;
					map.put(s, integer);
					s = ifBaseTypeSubstring(s) + integer;
				} else {
					map.put(s, 1);
					s = ifBaseTypeSubstring(s);
				}
				zhiXingParameters[i - 1] = s.substring(0, 1).toLowerCase() + s.substring(1);
				buffer.append(zhiXingParameters[i - 1]);
				if (methodParameters.length == i)
					buffer.append(")");
				else
					buffer.append(",");
			}
		} else
			buffer.append("()");
		return buffer.toString();
	}

	private String ifBaseTypeSubstring(String s) {
		if (s.length() - 1 == s.lastIndexOf("]"))
			return s.substring(0, s.length() - 2) + "s";
		if ("byte".equalsIgnoreCase(s))
			s = "b";
		if ("char".equalsIgnoreCase(s))
			s = "c";
		if ("short".equalsIgnoreCase(s))
			s = "s";
		if ("int".equalsIgnoreCase(s))
			s = "i";
		if ("long".equalsIgnoreCase(s))
			s = "l";
		if ("float".equalsIgnoreCase(s))
			s = "f";
		if ("double".equalsIgnoreCase(s))
			s = "d";
		if ("boolean".equalsIgnoreCase(s))
			s = "b";
		if ("byte".equalsIgnoreCase(s))
			s = "b";
		return s;
	}

	public String parse(Method method) {
		parseMethodModifiers(method);
		parseMethodReturn(method);
		parseMethodName(method);
		parseMethodParameters(method);
		return getMethSignatrueString();
	}

	public String[] parseMethodModifiers(Method method) {
		int i = method.getModifiers();
		modifiers = MethodModifiers.transformModifiers(i);
		return modifiers;
	}

	public String parseMethodReturn(Method method) {
		Class<?> type = method.getReturnType();
		methodReturn = type.getSimpleName();
		if (!"void".equals(type.getName()))
			importClassName.add(type.getName());
		return this.methodReturn;
	}

	public String parseMethodName(Method method) {
		this.methodName = method.getName();
		return this.methodName;
	}

	public String[] parseMethodParameters(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes != null && parameterTypes.length != 0) {
			methodParameters = new String[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				importClassName.add(parameterTypes[i].getName());
				methodParameters[i] = parameterTypes[i].getSimpleName();
			}
		}
		return methodParameters;
	}

	public Set<String> getImportClassName() {
		return importClassName;
	}

	public String[] getModifiers() {
		return modifiers;
	}

	public String getMethodReturn() {
		return methodReturn;
	}

	public String getMethodName() {
		return methodName;
	}

	public String[] getMethodParameters() {
		return methodParameters;
	}

	public String[] getZhiXingParameters() {
		return zhiXingParameters;
	}

}
