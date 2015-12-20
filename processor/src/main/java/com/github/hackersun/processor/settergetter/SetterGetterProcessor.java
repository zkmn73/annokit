package com.github.hackersun.processor.settergetter;

import com.github.hackersun.annotation.Getter;
import com.github.hackersun.annotation.Setter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.*;

/**
 * Desc:
 * Author:sunguoli@meituan.com
 * Date:15/12/19
 */
@AutoService(Processor.class)
public class SetterGetterProcessor extends AbstractProcessor {

    private Messager messager;
    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;

    @Override
    public void init(ProcessingEnvironment processingEnv){
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        typeUtils = processingEnv.getTypeUtils();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();
        annotations.add(Setter.class.getCanonicalName());
        annotations.add(Getter.class.getCanonicalName());
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()){
            Map<Element, AnnoType> annotatedElementMp = new HashMap<Element, AnnoType>();
            for(Element annotatedElement : roundEnv.getElementsAnnotatedWith(Setter.class)){
                annotatedElementMp.put(annotatedElement, AnnoType.SET);
            }
            for(Element annotatedElement : roundEnv.getElementsAnnotatedWith(Getter.class)){
                if(annotatedElementMp.containsKey(annotatedElement)){
                    annotatedElementMp.put(annotatedElement, AnnoType.SETGET);
                }else{
                    annotatedElementMp.put(annotatedElement, AnnoType.GET);
                }
            }

            for(Element annotatedElement : annotatedElementMp.keySet()){
                TypeElement typeElement = (TypeElement) annotatedElement;

                String pkgName = typeElement.getEnclosingElement().toString();
                messager.printMessage(Diagnostic.Kind.WARNING, "======>>>PackageName=" + pkgName);
                messager.printMessage(Diagnostic.Kind.WARNING, "======>>>ClassName=" + typeElement.getSimpleName().toString());

                TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(typeElement.getSimpleName().toString());

                AnnoType annoType = annotatedElementMp.get(annotatedElement);

                for(Element enclosed : annotatedElement.getEnclosedElements()) {
                    if(enclosed.getKind() != ElementKind.FIELD){
                        continue;
                    }
                    switch(annoType){
                        case GET:
                            typeSpecBuilder.addMethod(buildGetMethod(enclosed));
                            break;
                        case SET:
                            typeSpecBuilder.addMethod(buildSetMethod(enclosed));
                            break;
                        case SETGET:
                            typeSpecBuilder.addMethod(buildSetMethod(enclosed));
                            typeSpecBuilder.addMethod(buildGetMethod(enclosed));
                    }

                    //typeSpecBuilder.addOriginatingElement(executableElement);//

//                    MethodDeclaration methodDec = (MethodDeclaration)method;
                    ////=====
//                    List<JavacNode> childNodes = new ArrayList<JavacNode>();
//                    for (JCTree.JCAnnotation annotation : method.mods.annotations) addIfNotNull(childNodes, buildAnnotation(annotation, false));
//                    for (JCTree.JCVariableDecl param : method.params) addIfNotNull(childNodes, buildLocalVar(param, Kind.ARGUMENT));
//                    if (method.body != null && method.body.stats != null) {
//                        for (JCTree.JCStatement statement : method.body.stats) addIfNotNull(childNodes, buildStatement(statement));
//                    }
                    //return putInMap(new JavacNode(this, method, childNodes, Kind.METHOD));

                }

                try {
                    //TODO: Write to file will conflict with .class file
                    //TODO: so, It needs to modify AST here.
                    //TODO: lombok has realized AST modifying, but I have read the source code, it's so long..
                    JavaFile.builder(pkgName, typeSpecBuilder.build()).build()/*.writeTo(filer)*/;
                }catch (Exception e){
                    messager.printMessage(Diagnostic.Kind.ERROR, "==>>Setter&Getter Annotation Error: "+ e.getMessage());
                }

                processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "--------finished--------", annotatedElement);
            }
        }
        return false;
    }

    private enum AnnoType{
        SET,GET,SETGET
    }

    private MethodSpec buildSetMethod(Element element){
        messager.printMessage(Diagnostic.Kind.WARNING, "======>>>building set method for " + element.getSimpleName().toString());
        VariableElement variableElement = (VariableElement)element;

        String fieldName = variableElement.getSimpleName().toString();
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase();
        if (fieldName.length() > 1) {
            methodName += fieldName.substring(1);
        }

        MethodSpec.Builder method = MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(TypeName.get(variableElement.asType()), fieldName, Modifier.FINAL).build())
                .addStatement("this.$N = $N", fieldName, fieldName)
                .returns(TypeName.VOID);

        messager.printMessage(Diagnostic.Kind.WARNING, "======>>>" + methodName + " build complete.");
        return method.build();
    }

    private MethodSpec buildGetMethod(Element element){
        messager.printMessage(Diagnostic.Kind.WARNING, "======>>>building get method for " + element.getSimpleName().toString());
        VariableElement variableElement = (VariableElement)element;

        String fieldName = variableElement.getSimpleName().toString();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase();
        if (fieldName.length() > 1) {
            methodName += fieldName.substring(1);
        }

        MethodSpec.Builder method = MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return this.$N", fieldName)
                .returns(TypeName.get(variableElement.asType()));

        messager.printMessage(Diagnostic.Kind.WARNING, "======>>>" + methodName + " build complete.");
        return method.build();
    }

}
