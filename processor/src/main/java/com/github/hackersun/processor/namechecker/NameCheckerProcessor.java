package com.github.hackersun.processor.namechecker;

import com.google.auto.service.AutoService;

import com.github.hackersun.annotation.NameChecker;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/18
 */
@AutoService(Processor.class)
public class NameCheckerProcessor extends AbstractProcessor{

//    private CheckHandler checkHandler;

    @Override
    public void init(ProcessingEnvironment processingEnv){
        super.init(processingEnv);
//        checkHandler = new CheckHandler(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()){
            for(Element element : roundEnv.getElementsAnnotatedWith(NameChecker.class)){
//                checkHandler.scan(element);
                String name = element.getSimpleName().toString();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "++++" + name + "++++", element);
            }
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(NameChecker.class.getCanonicalName());
        return annotataions;
    }

}
