# BookingModifyMapper

Interfaz de mapeo para operaciones de modificación de reservas. Proporciona métodos para mapear entre value objects de modificación y value objects de costes enmendados.

## Uso

### Ejemplo: Mapeo de Suplemento Modificado

```java
BookingModifyMapper mapper = new BookingModifyMapperImpl();

// Crear un coste modificado
ModifyCostVO modifiedCost = new ModifyCostVO("COST001", 150.00, "EUR", "Suplemento modificado");
String internalCode = "INT123";

// Mapear a suplemento enmendado (updated="1")
AmendCostSupplementVO result = mapper.mapModifiedSupplement(modifiedCost, internalCode);
```

### Ejemplo: Mapeo de Suplemento No Modificado

```java
BookingModifyMapper mapper = new BookingModifyMapperImpl();

// Crear un suplemento y su coste original
ModifyCostVO originalCost = new ModifyCostVO("COST002", 100.00, "USD", "Coste original");
ModifySupplementVO supplement = new ModifySupplementVO("SUPP001", "Desayuno", originalCost);

// Mapear a suplemento enmendado (updated="0")
AmendCostSupplementVO result = mapper.mapUnmodifiedSupplement(supplement, originalCost);
```

### Ejemplo: Mapeo de Coste

```java
BookingModifyMapper mapper = new BookingModifyMapperImpl();

// Crear un coste modificado
ModifyCostVO modifiedCost = new ModifyCostVO("COST003", 200.50, "GBP", "Cargo por servicio");

// Mapear a coste enmendado (updated="1")
AmendCostCostVO result = mapper.mapCost(modifiedCost);
```

## Métodos

### mapModifiedSupplement

Mapea un suplemento modificado a un suplemento de coste enmendado.

- **Parámetros**:
  - `modifiedCost`: Datos del coste modificado del cambio
  - `internalCode`: Código interno del suplemento
- **Retorna**: `AmendCostSupplementVO` con el campo `updated` establecido en "1"

### mapUnmodifiedSupplement

Mapea un suplemento no modificado a un suplemento de coste enmendado.

- **Parámetros**:
  - `supplement`: Datos del suplemento no modificado
  - `originalCost`: Datos del coste original del suplemento
- **Retorna**: `AmendCostSupplementVO` con el campo `updated` establecido en "0"

### mapCost

Mapea un coste a un coste enmendado.

- **Parámetros**:
  - `modifiedCost`: Datos del coste modificado
- **Retorna**: `AmendCostCostVO` con el campo `updated` establecido en "1"

## Value Objects

### ModifyCostVO
Representa un coste que ha sido modificado.

### ModifySupplementVO
Representa un suplemento que puede o no haber sido modificado.

### AmendCostSupplementVO
Representa un suplemento de coste enmendado con indicador de actualización.

### AmendCostCostVO
Representa un coste enmendado con indicador de actualización.

## Construcción

```bash
mvn clean install
```

## Pruebas

```bash
mvn test
```
