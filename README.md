Lince
=====

Lince, aplicación informática para la automatización de registros observacionales.

Build Date: 17/02/2015
Version number: 1.4

Este sofware forma parte de la investigación: “Avances tecnológicos y metodológicos en la automatización de estudios observacionales en deporte” que ha sido subvencionado por la Dirección General de Investigación, Ministerio de Ciencia e Innovación (PSI2008-01179), durante el trienio 2008-2011

------------
Alberto Soto
------------
Guia basica de desarrollo

Existe una limitación en el desarrollo bastante importante generada por un conflicto de versiones en la libreria de VLC.
La libreria se carga en el inicio a partir del directorio de instalación de VLC y si el JRE no es de 32 bits,
falla automaticamente al arrancar el software.

Para solventarlo se debe configurar maven y los jdks de desarrollo a la versión 1.6 o superior de 32bits


