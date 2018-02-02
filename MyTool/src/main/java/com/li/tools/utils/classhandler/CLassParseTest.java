package com.li.tools.utils.classhandler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author lijuntao
 * @date 2016-9-24
 */
public class CLassParseTest {
	public static void main(String[] args) {
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		javaCompiler.hashCode();
	}
}
