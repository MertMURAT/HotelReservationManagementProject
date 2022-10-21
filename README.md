# FinalProject

Video : 

https://user-images.githubusercontent.com/58560920/197275898-a7b06954-a64b-4ca3-a809-c561a981fc7e.mp4



ODA REZERVASYON YÖNETİMİ

-Projemizin local adresini tarayıcıda açınca  bizi "Home" ekranı karşılıyor.
-Ana ekranda üst araç çubuğunda Login, Register(Authentication) ve Home olmak üzere 3 sayfa operasyonumuz bulunmaktadır.

-Home ekranında bulunan oda rezervasyonu listesindeki önümüze çıkan iki adet rezervasyon service katmanındaki
DataInitialization class' ında tanımlanmış olup hafızada yer aldığı için her açılışta sabit olarak görünecektir.

-Search By alanındaki Start Date ve End Date alanlarına tanımlayacağımız tarih aralığında rezervasyonları filtre işlemi uygular.

-Kullanıcı rolü olarak service katmanında DataInitialization class' ında tanımladığımız Admin ve User isminde 
iki adet rolümüz bulunmaktadır.

Admin rolüne;
Oda tanımlama,değiştirme ve silme işlemleri
Yeni kullanıcı(User) tanımlama,değiştirme ve silme işlemleri
Rezervasyon tanımlama,değiştirme,silme ve rezervasyon onayı-onay iptali işlemleri
yetkileri atanmıştır.

Kullanıcı(User) rolüne ise;
Rezervasyon tanımlama,değiştirme,silme işlemleri
yetkileri atanmıştır.

-Kullanıcı Rezervasyonunu tanımladıktan sonra admin kendi arayüz yetkilendirmesinden onaylayıp reddederse
kendi kullanıcı arayüzündeki rezervasyon sayfa sekmesinden onay durumunu görüntüleyebilir.


Kullanılan Teknolojiler:


 
 --Back-end--
  
 ->Java (Programming Language)
 
 ->Spring Boot (Application Platform)
 
 ->Spring Data JPA (Data Persistance)
 
 ->Swagger
 
 ->Maven
 
 ->Spring Security-Authentication
 
 --Front-end--
 
 ->Thymeleaf
 
 ->Javascript
 
 ->Html
 
 ->Css
 
 ->Bootstrap
 
 --Database--
 
 ->Mysql
 
