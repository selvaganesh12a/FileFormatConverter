# FileFormatConverter

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5.6-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

A **modular Java project** providing multiple file conversion utilities.
Supports converting between **TXT → CSV**, **XML → JSON**, and **JSON → XML** formats.
Designed to demonstrate file parsing, data transformation, and practical Java/Spring Boot skills.

---

## 🚀 Features

* **TXT → CSV Conversion**

  * Converts structured text files into CSV format for easier data handling.
* **XML → JSON Conversion**

  * Handles nested objects and arrays
  * Supports string, number, boolean, and null types
  * Custom XML tag mapping to JSON keys
* **JSON → XML Conversion**

  * Converts JSON files into XML format
  * Supports nested objects and arrays
* **Modular design**

  * Each conversion task is organized into separate modules for maintainability.

---

## 📂 Project Structure

```
FileFormatConverter/
├── text-to-csv/          # TXT → CSV conversion module
├── xml-to-json/          # XML → JSON conversion module
├── json-to-xml/          # JSON → XML conversion module
└── README.md
```

---

## 💻 Prerequisites

* Java 21+ (JDK 21 recommended)
* Maven 3.8+
* IDE (IntelliJ / Eclipse / VS Code) optional

---

## ⚡ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/selvaganesh12a/FileFormatConverter.git
cd FileFormatConverter
```

### 2. Build & run

```bash
mvn clean install
mvn spring-boot:run
```

---

## 📝 Module Usage Examples

### TXT → CSV

**Input TXT (example.txt):**

```
Name|Age|City
Alice|25|New York
Bob|30|Los Angeles
```

**Run conversion** (via Java main method in module or CLI):

```
TXT file is read and converted into CSV format
```

**Output CSV (example.csv):**

```
Name,Age,City
Alice,25,New York
Bob,30,Los Angeles
```

---

### XML → JSON

**Input XML:**

```xml
<object>
  <object name="organization">
    <string name="name">Securin</string>
    <string name="type">Inc</string>
    <number name="building_number">4</number>
  </object>
  <boolean name="security_related">true</boolean>
</object>
```

**POST request via curl:**

```bash
curl -X POST -F "file=@path/to/sample.xml" http://localhost:8080/convert/xml-to-json
```

**Output JSON:**

```json
{
  "organization": {
    "name": "Securin",
    "type": "Inc",
    "building_number": 4
  },
  "security_related": true
}
```

---

### JSON → XML

**Input JSON:**

```json
{
  "organization": {
    "name": "Securin",
    "type": "Inc",
    "building_number": 4
  },
  "security_related": true
}
```

**Run conversion** (via Java main method or module endpoint):

```
Converts JSON into equivalent XML format
```

**Output XML:**

```xml
<object>
  <object name="organization">
    <string name="name">Securin</string>
    <string name="type">Inc</string>
    <number name="building_number">4</number>
  </object>
  <boolean name="security_related">true</boolean>
</object>
```

---

## 🛠️ Notes & Future Improvements

* Add unit tests for all modules
* Enhance parsing for complex nested structures
* Extend support for additional formats (CSV → JSON, etc.)
* Improve error handling for invalid input

---

## 📜 License

MIT License © Selva Ganesh

---

> Created with ❤️ by **Selva Ganesh**
