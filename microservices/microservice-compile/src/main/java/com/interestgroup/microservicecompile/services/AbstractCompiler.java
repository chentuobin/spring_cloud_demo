package com.interestgroup.microservicecompile.services;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public abstract class AbstractCompiler implements Compiler {
    static final String className = "Test";
    static final String methodName = "main";

    abstract boolean compile();
    abstract boolean run();
    abstract String getCompileError();
    abstract String getRunResult();
    abstract String getRuntimeError();


    public String compileAndRun(){
        if(!compile()){
            return  "Compile Error: \n" + getCompileError();
        }
        if(!run()){
            return  "Runtime Error: \n" + getRuntimeError();
        }else{
            return "Running Result: \n" + getRunResult();
        }
    }

    String getClassOutput() {
        return this.getClass().getResource("/").getPath();
    }


    protected class StringSourceJavaObject extends SimpleJavaFileObject {
        private String content = null;

        public StringSourceJavaObject(String name, String content) {
            super(URI.create(name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }
}
