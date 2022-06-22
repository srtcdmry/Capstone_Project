# Capstone Project

Bu projede Android retrofit, gson kütüphaneleri kullanılarak "https://fakestoreapi.com" ile login işlemleri, ürün bilgileri, kategoriler ve alışveriş sepeti çekilmiştir. Forgot password, Login, Sign Up, home, bag ve shop ekranları mevcuttur.

Uygulama ilk olarak Splash Screen ile açılır. Kullanıcının daha önce login olup olmadığı, room database ile kontrol edilir. Daha önce login olunmamışsa SignUp ekranlarına yönlendirilir. Login olmuş ise login ekranına yönlendirilir. Buradaki eMail ve password bilgileri Room databaseden çekilerek otomatik doldurulur.

Main activity üzerinde 5 adet fragment vardır. Geçişler bottom navigation tarafından sağlanmaktadır. Api servisleri çağırıldığında cevap gelene kadar ekranda LottieAnimation ile yükleniyor ekranı gösterilir.

Alışveriş sepeti çağırılan api tarafından doldurulur. Buradaki ürün sayısını güncelleme butonları aktiftir. CheckOut ile alışveriş tamamlanır. 

Kullanıcı sepetinde herhangi bir ürün unutup uygulamayı arkaplana aldığında WorkManager ile belirtilen süre sonrasında bildirim gönderilir. 



## Kullanılan Kütüphaneler

### Retrofit
```
   implementation 'com.squareup.retrofit2:retrofit:2.9.0'
   implementation 'com.squareup.retrofit2:adapter-rxjava:2.4.0'
   implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   implementation 'com.google.code.gson:gson:2.8.6'
	 
```
### Glide
```
   implementation 'com.github.bumptech.glide:glide:4.12.0'
   annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
```
### RecyclerView

```
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
```
### RoomDatabase 

```
   implementation 'android.arch.persistence.room:runtime:1.1.1'
   annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'
```
### WorkManager
```
   implementation 'androidx.work:work-runtime:2.7.1'
```
### LottieAnimation
```
    implementation 'com.airbnb.android:lottie:3.3.1'
```
### Background LifeCycle
```
    implementation "android.arch.lifecycle:extensions:1.1.1"
```
### Navigation
```
   implementation 'androidx.navigation:navigation-fragment:2.4.2'
   implementation 'androidx.navigation:navigation-ui:2.4.2'
```

## Splash Screen

![Screenshot_20220622-144059](https://user-images.githubusercontent.com/74022745/175020830-5c6e4b1b-6315-484f-9fb4-89536ad65899.jpg)

## Login, Sign Up, Forgot Password Screens

![Screenshot_20220622-143109](https://user-images.githubusercontent.com/74022745/175019793-a2c9a160-b6c8-4a9d-8ff0-f00b441a952b.jpg)
![Screenshot_20220622-143115](https://user-images.githubusercontent.com/74022745/175019804-50336900-0ccd-445e-901d-d885b2201ada.jpg)
![Screenshot_20220622-143102](https://user-images.githubusercontent.com/74022745/175019841-22353d4f-686a-4f50-9206-44591732b838.jpg)

## MainActivity (Home, Shop, Bag -> Complete - Favorite, Profile -> Optional)

![Screenshot_20220622-152013](https://user-images.githubusercontent.com/74022745/175027761-626c1693-c065-47d2-b631-8ba8a22d6147.jpg)
![Screenshot_20220622-143042](https://user-images.githubusercontent.com/74022745/175020170-a3d13fc9-ba37-4ab5-acd5-3cc5a077c31f.jpg)
![Screenshot_20220622-143025](https://user-images.githubusercontent.com/74022745/175020190-55feae11-7182-4e5c-9946-4676c91d7399.jpg)

## Payment Success

![Screenshot_20220622-143029](https://user-images.githubusercontent.com/74022745/175020503-3f7d18e5-d6a3-4623-b2eb-085eb4f94e0a.jpg)

## Loading LottieAnimation

![Screenshot_20220622-132705](https://user-images.githubusercontent.com/74022745/175020929-81866b95-0e5f-4549-86bf-56260762c883.jpg)

## Work Manager

![Screenshot_20220622-144439_One UI Home](https://user-images.githubusercontent.com/74022745/175021513-aa9f63ea-1e39-4925-9be4-c21643666b1f.jpg)

