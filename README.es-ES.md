

# annokit
Java anotación en acción.

Cualquiera puede escribir una anotación paso a paso.

annokit ya ha completado tres anotaciones ahora:<br/>
**@Factory**: Patrón de diseño Factory<br/>
**@Setter**: Genera métodos setter automáticamente<br/>
**@Getter**: Genera métodos getter automáticamente<br/>
Sin embargo, las anotaciones @Setter y @Getter tienen una barrera que eliminar; al generar el método durante la etapa de compilación, no es posible escribir el método generado en el archivo, ya que de lo contrario se produciría un conflicto con el archivo .class al compilar con javac. Por ello, debo modificar el AST en la etapa de resolución de anotaciones. No tengo mucho tiempo, esto no está terminado. He leído el código fuente de [lombok][1], el cual ha logrado implementarlo.


<br/>2015.12.20

[1]: https://github.com/rzwitserloot/lombok
