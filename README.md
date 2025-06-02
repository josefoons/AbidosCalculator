
# 🧮 Abidos Crafting Calculator

Una aplicación de escritorio en Java Swing que permite calcular de forma precisa cuántos **lotes de 10 Abidos** puedes fabricar a partir de tus recursos actuales. Optimiza el uso de madera (gris, verde, azul), energía, oro y tiempo.

---

## 📦 Características

- Cálculo automático de:
  - Lotes posibles con inventario base.
  - Transformaciones de madera a polvitos.
  - Compra de madera azul usando polvitos.
  - Recursos necesarios (oro, energía, tiempo).
  - Distribución en **4 ventanas de crafteo simultáneo**.
- Configuración avanzada:
  - Energía por lote.
  - Tiempo por lote.
  - Oro por lote.
- Validación de campos numéricos.
- Área de resultados de solo lectura.
- Mensajes informativos para lotes adicionales fuera de las 4 ventanas.

---

## 🧱 Requisitos por lote

| Madera Azul | Madera Verde | Madera Gris |
|-------------|--------------|-------------|
| 33          | 45           | 86          |

Cada lote fabrica **10 Abidos**.

---

## ⚙️ Configuración avanzada

Estos parámetros pueden modificarse directamente desde la interfaz:

| Parámetro             | Valor por defecto | Descripción                        |
|----------------------|-------------------|------------------------------------|
| Energía por lote      | `257`             | Energía que consume fabricar un lote |
| Tiempo por lote       | `46`              | Minutos necesarios por lote         |
| Oro por lote          | `344`             | Oro consumido por lote              |

---

## 🔄 Conversión de maderas a polvitos

Puedes transformar madera excedente en polvitos para comprar madera azul:

| Madera usada  | Por conversión | Polvitos obtenidos | Azules comprables |
|---------------|----------------|--------------------|--------------------|
| Verde         | 50 unidades    | 80 polvitos        | 8 unidades         |
| Gris          | 100 unidades   | 80 polvitos        | 8 unidades         |

> ⚠️ Se prioriza la conversión de **verde**, ya que la gris es el recurso más valioso y no puede conseguirse por otros medios.

---

## 📊 Ejemplo de salida

```
Inventario inicial:
Gris: 999999, Verde: 9999, Azul: 99

Lotes posibles sin transformar: 3

--- CONVERSIÓN A POLVOS ---
✔ Verde reservada para 3 lotes base: 135
Verdes transformados: 1950 → 3120 polvitos
 → 39 compras de 50 maderas verdes
 → Verde restante: 7914

✔ Gris reservada para 3 lotes base: 258
Grises transformados: 0 → 0 polvitos
 → 0 compras de 100 maderas grises
 → Gris restante: 999741

Total polvitos obtenidos: 3120
Total polvitos utilizados: 3120
Azules comprados: 3120 (en 312 compras de 10)

Inventario después de convertir polvitos y comprar:
Azul: 3219
Verde: 8049
Gris: 999999

PUEDES FABRICAR: 97 lotes de 10 Abidos (total: 970)
ORO requerido: 33368
ENERGÍA requerida: 24929

TIEMPO total de crafteo (máximo por ventana):
7 horas y 40 minutos (460 minutos en total)

Crafteos por ventana:
Ventana 1: 10 crafteos (7h 40min)
Ventana 2: 10 crafteos (7h 40min)
Ventana 3: 10 crafteos (7h 40min)
Ventana 4: 10 crafteos (7h 40min)

⚠️ Tienes más lotes disponibles, pero las 4 ventanas están llenas. Podrías hacer 57 lotes adicionales.
⏱️ Tiempo adicional necesario (máximo por ventana): 11 horas y 30 minutos
💰 Oro necesario adicional: 19608
⚡ Energía necesaria adicional: 14649
```

---

## 🧑‍💻 Tecnologías usadas

- Java 8+
- Swing (`javax.swing.*`)

---

## 🚀 Ejecución

1. Compila el archivo `.java`:

```bash
javac AbidosCalculatorGUI.java
```

2. Ejecuta la aplicación:

```bash
java AbidosCalculatorGUI
```

---

## 📌 Notas finales

- No se permite convertir gris en verde o azul directamente.
- La prioridad de recursos se basa en su escasez relativa.
- El cálculo de crafteos adicionales también considera la restricción de **4 ventanas simultáneas**.

---

## 🛠️ Futuras mejoras (opcional)

- Guardar configuraciones personalizadas.
- Soporte multilenguaje.
- Modo oscuro.
