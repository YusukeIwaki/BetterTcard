# Better T-Card

公式アプリが使いづらすぎるので、起動してカードを表示するだけのアプリを作った。
http://yusukeiwaki.hatenablog.com/entry/2019/02/04/T%E3%82%AB%E3%83%BC%E3%83%89%E3%82%92%E8%A1%A8%E7%A4%BA%E3%81%99%E3%82%8B%E3%81%A0%E3%81%91%E3%81%AE%E3%82%A2%E3%83%97%E3%83%AA%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F

![image](https://user-images.githubusercontent.com/11763113/52181360-4efb1900-2834-11e9-921a-445474d7116e.png)


* 初回だけはYahooログインを求められます
* 時々、「pc_busy」みたいなページに飛ばされるみたいで、カードがすぐに表示できないことがあります。その際には数秒後に再起動すれば使えます。

## How to Build

### カード番号と誕生日情報をpropertiesファイルに置く

```
cp app/tcard.properties.sample app/tcard.properties
vi app/tcard.properties # カード番号と誕生日を編集してください
```

### ビルド

```
./gradlew assembleDebug
```

## 注意事項

Tカードのトークン取得に[Shufoo](http://www.shufoo.net/)のAPIを勝手に使っています。
その点はご承知の上で、自己責任でお使いください。
