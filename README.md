# RoofTop Food Delivery Service

## Overview

The RoofTop Food Delivery Service is a Spring Boot based REST API developed for managing food delivery driver
allocation for the restaurant **RoofTop**.

If all drivers are busy at the time of order placement, the system returns:

```text
No Food :-(
```

---

# API Details

## Base URL

```text
http://localhost:8080
```

---

# Allocate Drivers API

## Endpoint

```http
POST /api/v1/delivery/allocate
```

---

# Request Payload

```json
{
  "totalDrivers": 2,
  "orders": [
    {
      "orderTime": 1,
      "travelTime": 10
    },
    {
      "orderTime": 4,
      "travelTime": 20
    },
    {
      "orderTime": 15,
      "travelTime": 5
    },
    {
      "orderTime": 22,
      "travelTime": 20
    },
    {
      "orderTime": 24,
      "travelTime": 10
    },
    {
      "orderTime": 25,
      "travelTime": 10
    }
  ]
}
```

---

# Sample Response

```json
[
  {
    "customer": "C1",
    "allocatedDriver": "D1"
  },
  {
    "customer": "C2",
    "allocatedDriver": "D2"
  },
  {
    "customer": "C3",
    "allocatedDriver": "D1"
  },
  {
    "customer": "C4",
    "allocatedDriver": "D1"
  },
  {
    "customer": "C5",
    "allocatedDriver": "D2"
  },
  {
    "customer": "C6",
    "allocatedDriver": "No Food :-("
  }
]
```

---

# Design Patterns Used

## 1. Strategy Pattern

Used for implementing multiple driver allocation algorithms.

### Implementations

- LowestIndexDriverStrategy

---

## 2. Factory Pattern

Used for dynamically selecting the allocation strategy from configuration.

### Factory Class

```
DriverStrategyFactory
```

---

## 3. Builder Pattern

Used with Lombok to create entity objects cleanly.

---

# Author

Developed for RoofTop Food Delivery Driver Allocation Use Case using:

- Java 17
- Spring Boot 4
- Gradle
- Lombok