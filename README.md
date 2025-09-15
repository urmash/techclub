# 🎓 Techclub

**Techclub** on Java Spring Boot rakendus, mis võimaldab hallata hobigruppe, nende tegevusi ja osalejaid.  
Projekt on loodud hariduslikus kontekstis, et demonstreerida **CRUD**-operatsioonide, andmebaasi modelleerimise ja REST API-de loomise oskusi.

---

## 🚀 Tehnoloogiad

- **Java 21**
- **Spring Boot 3.4**
- **Gradle**
- **HSQLDB** (in-memory andmebaas)
- **Spring Data JPA**
- **Lombok**
- **Spring Validation**
- **Swagger UI** (springdoc-openapi)

---

## 📂 Projekti struktuur

src/ 
├── main/  
│ ├── java/com/example/...  
│ │ ├── model/ # JPA entiteedid  
│ │ ├── dto/ # Andmeedastusobjektid (DTO-d)  
│ │ ├── repository/ # Spring Data JPA repository liidesed  
│ │ ├── service/ # Äriloogika  
│ │ └── controller/ # REST API kontrollerid  
│ └── resources/  
│ ├── application.properties # Rakenduse seadistus  
│ ├── data.sql # Näidisandmed  
│ └── schema.sql # (valikuline) skeemi loomise skript 
└── test/ # Testid


---

## 🗄 Andmemudel

**Põhitabelid:**
- `hobby_group` – hobigrupid
- `participant` – osalejad
- `activity` – tegevused (seotud ühe hobigrupiga)
- `membership` – seostabel gruppide ja osalejate vahel (M:N)

**Seosed:**
- Üks `hobby_group` → mitu `activity`
- M:N seos `hobby_group` ↔ `participant` läbi `membership` tabeli

![ER Diagram](docs/Techclub.er.png)

---

## 📦 Käivitamine

1. **Klooni repo**
   ```bash
   git clone https://github.com/urmash/techclub.git
   cd techclub

Swagger UI (API testimiseks ja dokumentatsiooniks) http://localhost:8080/swagger-ui.html

HSQLDB konsool (valikuline) http://localhost:8080/h2-console
JDBC URL: jdbc:hsqldb:mem:techclubdb
Kasutaja: sa
Parool: (tühi)

📜 Autor
UrmasH – Lõputöö / Graduation Project 2025