# Better T-Card

公式アプリが使いづらすぎるので、起動してカードを表示するだけのアプリを作った。

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
