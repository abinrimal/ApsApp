# ApsApp 

An integrated data warehouse project implementing the ETL (Extract, Transform, Load) process using Java and MySQL.

The system extracts data from CSV/XML files, transforms and cleans them using Java, and loads them into a relational database.

---

## Architecture Overview

```
CSV / XML Files → Java Parser → DataTransformer → MySQL (apsWarehouse)
```

---

## ⚙️ Tech Stack

- Java (ETL Processing)
- MySQL (Data Warehouse)
- JDBC (Database Connectivity)
- CSV/XML Parsing

---

## 🔄 DataTransformer (ETL Layer)

Responsible for cleaning and standardizing raw data before database loading.

---

## Accounts Transformation

**Method:**
```java
transformAccounts(List<List<String>> accounts)
```

**Rules:**
- Company name → UPPERCASE
- Sector → lowercase + fix spelling (`technolgy → technology`)
- Country fix (`Philipines → Philippines`)
- Ensure `subsidiary_of` field exists

---

## Products Transformation

**Method:**
```java
transformProducts(List<List<String>> products)
```

**Rules:**
- Series → trim + UPPERCASE
- Price → trim, empty → NULL

---

## Sales Pipeline Transformation

**Method:**
```java
transformSalesPipeline(List<List<String>> sales)
```

**Rules:**
- Trim all text fields
- Fix product names:
  - GTXPro → GTX Pro
  - GTXPlus → GTX Plus
  - MGAdvanced → MG Advanced
- Empty dates → NULL
- Empty close_value → NULL

---

## 🗄️ Database

- Database: `apsWarehouse.sql`
- Schema: `DB_Schema.png`
- Tables:
  - accounts
  - products
  - sales_pipeline
  - sales_teams

---

## Key Principle

> Transformer = Data Cleaning  
> Loader = Database Insert Only (No Logic)

---

## Status

✔ ETL Pipeline Working  
✔ Data Cleaning Implemented  
✔ MySQL Integration Done  
