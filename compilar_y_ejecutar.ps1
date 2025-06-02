# Agregar el bin del JDK al PATH temporalmente (solo durante esta sesión de PowerShell)
$env:Path += ";C:\Program Files\Java\jdk-24\bin"

# Eliminar archivos antiguos si existen
Remove-Item .\AbidosCalculatorGUI.class -ErrorAction SilentlyContinue
Remove-Item .\AbidosCalculatorGUI$*.class -ErrorAction SilentlyContinue
Remove-Item .\AbidosCalculator.jar -ErrorAction SilentlyContinue
Remove-Item .\manifest.txt -ErrorAction SilentlyContinue

# Compilar el archivo Java
javac .\AbidosCalculatorGUI.java

# Crear el archivo manifest.txt correctamente
@"
Main-Class: AbidosCalculatorGUI
"@ | Out-File -Encoding ASCII manifest.txt

# Crear el JAR incluyendo todos los archivos .class relacionados
jar cfm AbidosCalculator.jar manifest.txt AbidosCalculatorGUI*.class

# Ejecutar el JAR
java -jar AbidosCalculator.jar

Pause
