# Prueba Técnica Automatización - Abstracta

Automatización de pruebas end-to-end para la tienda en línea OpenCart, utilizando Java 18 Amazon Corretto, Selenium
WebDriver, TestNG, Cucumber (BDD), y generación de reportes.

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- Java 18 Amazon Corretto
- Apache Maven

## Configuración del Proyecto

1. Clona este repositorio en tu máquina local.

```bash
  git clone https://github.com/hdvergara/prueba_abstracta
  cd prueba_tecnica_abstracta
```

2. Instalar depedencias

```bash
  mvn clean install
```

## Ejecución de las pruebas

Para ejecutar todas las pruebas, utiliza el siguiente comando:

```bash
  mvn clean test -Dheadless=true 
```

Cambia el valor de -Dheadless por false si se quiere ver la interfza grafica

Tambien puede ejecutar las pruebas desde el TestRunner ubicado en la siguiente ubicacion: 

**src/test/java/framework/abstracta/runners/TestRunnerStore.java**

El reporte del resultado de las pruebas, se puede visualizar en la carpeta:

**target/cucumber-html-report/**
