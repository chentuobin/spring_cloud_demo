package com.interestgroup.microservicecompile.services;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCompilerImpl extends AbstractCompiler {
    private String code;
    private StringBuilder compileError = new StringBuilder();
    private StringBuilder runResult = new StringBuilder();
    private String runtimeError = null;

    public JavaCompilerImpl(String code) {
        code = "import java.util.*;\n" + code;
        this.code = code;
    }

    @Override
    boolean compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> oDiagnosticCollector = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(oDiagnosticCollector, null, null);
        //TODO handle the io exception properly
        try {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(new File(getClassOutput())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        AbstractCompiler.StringSourceJavaObject sourceObject = new StringSourceJavaObject(className, code);
        Iterable<? extends JavaFileObject> fileObjects = Collections.singletonList(sourceObject);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, oDiagnosticCollector, null, null, fileObjects);
        boolean result = task.call();
        if (!result) {
            setCompileError(oDiagnosticCollector);
            return false;
        }
        return true;
    }

    @Override
    boolean run() {
        PrintStream ps = null;
        BufferedReader br = null;
        InputStream stdIn = System.in;
        PrintStream stdOut = System.out;
        try {
            Class<?> clazz = Class.forName(className);

            URL[] urls = {clazz.getProtectionDomain().getCodeSource().getLocation()};
            ClassLoader delegateParent = clazz.getClassLoader().getParent();
            try (URLClassLoader cl = new URLClassLoader(urls, delegateParent)) {
                clazz = cl.loadClass(clazz.getName());
            }

            Method method = clazz.getMethod(methodName, String[].class);

            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ps = new PrintStream(bao);
            System.setOut(ps);
            method.invoke(null, new Object[]{null});

            br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bao.toByteArray())));
            String line;
            StringBuilder runResult = new StringBuilder();
            while ((line = br.readLine()) != null)
                runResult.append(line).append("\n");
            setRunResult(runResult);
        } catch (Exception e) {
            setRuntimeError(e);
            return false;
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.setIn(stdIn);
            System.setOut(stdOut);
        }
        return true;
    }

    private void setCompileError(DiagnosticCollector<JavaFileObject> oDiagnosticCollector) {
        Pattern p = Pattern.compile("Main.java\\D*(\\d+):", Pattern.DOTALL);
        for (Diagnostic<? extends JavaFileObject> oDiagnostic : oDiagnosticCollector.getDiagnostics()) {
            Matcher m = p.matcher("Compiler Error: " + oDiagnostic.getMessage(null));
            if (m.find()) {
                this.compileError.append(m.replaceAll("Main.java " + String.valueOf(Integer.valueOf(m.group(1)) - 1))).append(":").append("\n");
            } else {
                this.compileError.append(oDiagnostic.getMessage(null)).append(" Line:").append(oDiagnostic.getLineNumber() - 1).append("\n");
            }
        }
    }

    private void setRuntimeError(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        Pattern p = Pattern.compile("Main.java\\D*(\\d+)", Pattern.DOTALL);
        Matcher m = p.matcher(sw.toString());
        if (m.find()) {
            this.runtimeError = m.replaceAll("Main.java " + String.valueOf(Integer.valueOf(m.group(1)) - 1) + ":");
        } else {
            this.runtimeError = sw.toString();
        }
    }

    private void setRunResult(StringBuilder runResult) {
        this.runResult = runResult;
    }

    @Override
    String getCompileError() {
        return this.compileError.toString();
    }

    @Override
    String getRunResult() {
        return this.runResult.toString();
    }

    @Override
    String getRuntimeError() {
        return this.runtimeError;
    }
}
