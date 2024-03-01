package org.lucycato.common;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class PrintStackTraceManager {

    public String getStackTraceAsString(Throwable tx) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        tx.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
