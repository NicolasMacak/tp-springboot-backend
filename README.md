## Recent záležitosti
Podľa mňa by sme mali vždy vracať ResponseEntity. Niekedy to otvára nové možnosti. Napríklad poslanie 204 (no content) ak neexistujú žiadne produkty.
Trošku som sa vyhaluzil s verifikáciou fieldov. Keď niečo zadáš zle, hodí to error popisujúci čo je zle a kde. vcelku sa mi to pozdáva. Možno sa to dá spraviť elegantnejšie tho.
<br>
Asi musíme nejako pobrainstormovať architektúru projektu. Podľa mňa je to škaredé :D 
Napadlo mi že by sme mali priečinok pre každý model a v ňom by sme mali prislúchajúce repository, service a controller. 

## H2 databejs
Táto H2 configurácie je strašne naspeedovaná, pretože je v memory. Ale vymaže sa po tom ako stopneš service.
Tiež sa na ňu nedá pripojiť cez intellij, pretože už je na ňu napojená runtime service.
Neviem či to platí pre memory konfiguráciu len tejto H2, alebo šeckých. 
Ak sa na ňu chceme pripojiť cez intellij, treba zmeniť jej konfiguráciu tak aby bola na HDD. čo som skúšal, ale nevidalo.
Jediné pozitívum z HDD db je že sa na ňu môžeme pripojiť cez intellij pokiaľ viem.
<hr>

toto je config file. <br>
![image](https://user-images.githubusercontent.com/45353526/121641549-9a223800-ca8f-11eb-9463-df4cbeb8a13a.png)

<br>
Na db sa pripája cez tento link: http://localhost:8080/h2-console/login.jsp

Hodí ťa to potom sem a úplne zadarmo si môžeš nastaviť slovenský jazyk. Zadáš intel z config súboru a vybafčo. <br>
![image](https://user-images.githubusercontent.com/45353526/121642980-8d9edf00-ca91-11eb-8f25-f4b4f4fa718a.png)

## Odrazový mostík

Čítal som si nejaké veci a toto je nejaký rešerš.
<b> Controller </b> <br> najvrchnejšia úroveň logiky. Validuje vstupy, obsahuje logiku na výber správnych parametrov do service funcií. Môže pracovať s viacerými servicami. <br>
<b> Service </b> <br> Handluje db dátami. Validuje db related veci. Môže obsahovať viacero repozitárov ak majú medzi sebou nejakú väzbu (Client a Contact). Netreba robiť service pre každú Entitu. <br>
<b> Repozitár </b> <br>iba ťahá dáta cez basic CRUD metódy

<br>

Väčšina vecí je tu intuitívna. Pre mňa bola trošku menej @Autowired. Už som predtým pracoval s dependency injection. Poučka pre mudrosráčov je že,
keď je deklarácia nejakého beanu (@Bean) v inom beane, tak automaticky vznikne inštancia. Pretože beany sú súčasťou "live contextu" aplikácie.@Controller @Service aj @Repository sú tiež beany. Šade samé fazule. <br>
Ejkejej, automaticky sa vytvoria inštancie objektov ktoré sú označené s @Autowired.




## Heroku
[mohla som sem hodit rovno url ale takto to je viac fancy](https://data.heroku.com/datastores/140e5ef5-943d-4568-a9ed-83539b4611eb)

| neviem        | spravit tabulku bez headera| 
| :------------- |:-------------| 
| <b>username</b>| kirschovapetra@gmail.com |
| <b>password</b>|          Teamproject123  |

## Login do datbazy

| never        | gonna give you up| 
| :------------- |:-------------| 
|<b>Host</b>        |ec2-52-50-171-4.eu-west-1.compute.amazonaws.com|
|<b>Database</b>    |dqp28p5rb1cea|
|<b>User</b>        |spabozzmnjxlkn|
|<b>Port</b>        |5432|
|<b>Password</b>    |e5824ab5f40977c4fab8c0d2e9f2bcda266ef462ee1486b006fc3227e0e1a588|
|<b>URI</b>         |postgres://spabozzmnjxlkn:e5824ab5f40977c4fab8c0d2e9f2bcda266ef462ee1486b006fc3227e0e1a588<span>@</span>ec2-52-50-171-4.eu-west-1.compute.amazonaws.com:5432/dqp28p5rb1cea|
|<b>Heroku CLI</b>  |heroku pg:psql postgresql-flat-43469 --app teamproject123|


## Connect cez intellij 

<b>View -> Tool Windows -> Database -> + -> Data Source -> PostgreSQL</b>

### Extremny tutorial aj s obrazkom
![db](https://user-images.githubusercontent.com/49959692/121781234-1c9c1c00-cba4-11eb-9a42-b7afd70da152.png)

Ono to pozna aj emoji, hen kukaj :pizza: :cherries: :sunflower: :scream_cat:
