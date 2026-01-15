# Documentație Design Patterns

1. **Singleton** (în `StockMarket` `TradingPlatform` și`UserManager`)  
   **Motivație:** Se asigură că există o singură instanță a pieței și a platformei în întreaga aplicație.  
   Toți utilizatorii trebuie să vadă aceleași prețuri, iar toate tranzacțiile trebuie să se facă în același loc.


2. **Command** (în `BuyCommand` și `SellCommand`)  
   **Motivație:** Fiecare acțiune (cumpărare sau vânzare) este încapsulată într-un obiect separat.  
   Cumpărarea și vânzarea sunt acțiuni distincte, pot fi salvate în istoric și pot fi extinse ulterior.


3. **Strategy** (în `TradingStrategy` și clasele concrete: `DayTradingStrategy`, `LongTermInvestingStrategy`, `AutoTradingStrategy`)  
   **Motivație:** Definește modul în care se realizează tranzacțiile, nu doar ce se face.  
   Există mai multe stiluri de tranzacționare, iar strategia poate fi schimbată fără a afecta restul aplicației.


4. **Observer** (în `StockMarket` `PriceObserver` `TradableAsset` `User`)  
   **Motivație:** Permite notificarea automată a componentelor interesate când se schimbă prețurile.  
   Prețurile se schimbă frecvent și mai multe părți ale aplicației trebuie să fie la curent fără a crea legături strânse între ele.


5. **Composite** (în `Portfolio` și `Holding`)  
   **Motivație:** Permite tratarea uniformă a unui singur activ sau a unui grup de active.  
   Un portofoliu poate conține mai multe active și trebuie gestionat și calculat ca un tot unitar.


6. **Factory** (în `AssetFactory`)  
   **Motivație:** Responsabilă pentru crearea obiectelor activelor, în loc să fie instantiate peste tot în cod. Apariția unor noi tipuri de active trebuie să poată fi gestionată fără modificări în mai multe locuri din cod.


7. **Facade** `TradingPlatform`
**Motivație:** Oferă o interfață simplă peste subsisteme complexe (utilizatori, piață, comenzi).

