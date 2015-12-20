package com.github.hackersun.processor.namechecker;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/18
 */
public class CheckHandler extends ElementScanner6<Void, Void>{
    private final Messager messager;

    CheckHandler(ProcessingEnvironment processingEnv){
        this.messager = processingEnv.getMessager();
    }

    @Override
    public Void visitVariable(VariableElement e, Void p){
        String name = "";
        if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null){
            name = "IF_" + e.getSimpleName().toString();
        }else{
            name = "ELSE_" + e.getSimpleName().toString();
        }
        messager.printMessage(Diagnostic.Kind.WARNING, name, e);
        return null;
    }


}
