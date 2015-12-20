# annokit
Java annotion in action.

Everyone can wirte an annotation step by step.

annokit has completed three annotations now:<br/>
**@Factory**: Factory design model<br/>
**@Setter**: generate set method automaticly<br/>
**@Getter**: generate get method automaticly<br/>
but,Setter and Getter annotation have a barrier to remoce, thus, generating method during compile stage, and cannot write the generated method to file, otherwise, this will got a confilct with .class by javac. so, I must to modify the AST in annotation resolving stage. I have not so much time, this have not finished. and I have read the source code of [lombok][1], which has realized that.

By hackersun
<br/>2015.12.20

[1]: https://github.com/rzwitserloot/lombok
