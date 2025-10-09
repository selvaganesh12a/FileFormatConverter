# FileFormatConverter

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5.6-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

A **modular Java project** for converting files between formats such as **TXT → CSV**, **XML → JSON**, and **JSON → XML**.
Designed to showcase file parsing, data transformation, and practical Java/Spring Boot skills.

---

## 🚀 Features

* **TXT to CSV Conversion**
* **XML to JSON Conversion**

  * Handles nested objects and arrays
  * Supports strings, numbers, booleans, and null values
  * Works with custom XML tags and attributes
* **JSON to XML Conversion**
* **Modular design** for easy extension

---

## 📂 Project Structure

```
FileFormatConverter/
├── xml-to-json/          # XML → JSON module
├── text-to-csv/          # TXT → CSV module
├── json-to-xml/          # JSON → XML module
├── pom.xml               # Maven project file
└── README.md
```

---

## 💻 Prerequisites

* Java 21+
* Maven 3.8+
* IDE (IntelliJ / Eclipse / VS Code) optional

---

## ⚡ How to Run

1. **Clone the repo**

```bash
git clone https://github.com/selvaganesh12a/FileFormatConverter.git
cd FileFormatConverter
```

2. **Build & run**

```bash
mvn clean install
mvn spring-boot:run
```

3. **Convert XML → JSON**

```bash
curl -X POST -F "file=@path/to/sample.xml" http://localhost:8080/convert/xml-to-json
```

**Response:**

```json
{
  "message": "Conversion successful",
  "output_file_path": "/path/to/output/output_123456.json",
  "converted_json": {
    "organization": {
      "name": "Securin",
      "type": "Inc",
      "building_number": 4
    },
    "security_related": true
  }
}
```

4. **Other converters**

* Check `text-to-csv/` or `json-to-xml/` folders for usage instructions.

---

## 📝 Sample XML Input

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

**Converted JSON Output:**

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

## 🛠️ Notes & Future Improvements

* Add unit tests for all modules
* Enhance XML parsing for complex nested structures
* Extend support for additional formats (CSV → JSON, etc.)
* Improve error handling for invalid input

---

## 📜 License

MIT License © Selva Ganesh

---

> Created with ❤️ by **Selva Ganesh**
