# MCPitanLib
## License
- MIT License

## 日本語
MCPitanLibはライブラリです。 1つのjarで複数のMCバージョンを対応させるためのものです。 <br />
開発中であるため、予告なく仕様が変更されることがあったり、不具合があるかもしれません。 <br />
私専用につくっているため、他の方が使うことをあまり想定していません。 <br />

クロスバージョンかつクロスプラットフォームを目指しています。

### 対応状況
- アイテムの追加
- ブロックの追加
- エンティティの追加
- ブロックエンティティの追加
- クリエイティブタブの追加
- GUIの追加
- サウンドの追加
- ポーション / 醸造レシピの追加
- エンチャントの追加
- レシピ / カスタムレシピタイプの追加
- コマンドの追加
- パケット / ネットワーク
- イベントリスナー
- エネルギー/流体/アイテム転送API
- タグ / テキスト / スタイル / NBT / コンポーネント
- タイマー / タスクスケジューラー

となります。

### 前提MOD
- MODローダー
  - [FabricMC](https://fabricmc.net/)
  - [Minecraft Forge](https://files.minecraftforge.net/)
  - [NeoForge](https://neoforged.net/)
- Architectury API ([CurseForge](https://www.curseforge.com/minecraft/mc-mods/architectury-api) | [Modrinth](https://modrinth.com/mod/architectury-api))

### ダウンロード
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/mcpitanlibarch)
- [Modrinth](https://modrinth.com/mod/mcpitanlibarch)

### 使い方
`build.gradle`に以下のように記述してください。
```groovy
repositories {
    maven {
        url = "https://maven.pitan76.net/"
    }
}

dependencies {
    // FabricMC
    modImplementation "net.pitan76:mcpitanlib-fabric:${rootProject.mcpitanlib_version}"
    
    // Minecraft Forge
    //modImplementation "net.pitan76:mcpitanlib-forge:${rootProject.mcpitanlib_version}"
    
    // NeoForge
    //modImplementation "net.pitan76:mcpitanlib-neoforge:${rootProject.mcpitanlib_version}"
}
```

`gradle.properties`に以下のように記述してください。
```properties
# Example: mcpitanlib_version=+1.18.2:3.3.3
mcpitanlib_version=+x.x.x:x.x.x
```

バージョンは[GitHub Packages](https://github.com/PTOM76/maven/packages/?q=net.pitan76.mcpitanlib) (3.3.3以降) or [maven.pitan76.net](https://maven.pitan76.net/net/pitan76/) (3.3.3以前)で確認してください。

MCPitanLibを用いたMOD開発は「common/src/main/java/net/pitan76/mcpitanlib/test/ExampleMod.java」を参考にしてください。

## English
MCPitanLib is a library. It is for supporting multiple MC versions with one jar. <br />
It is under development, so the specification may change without notice, and there may be bugs. <br />
I am creating it for my own use, so I don't expect others to use it much. <br />

It aims to be cross-version and cross-platform.

### Supported features
- Adding items
- Adding blocks
- Adding entities
- Adding block entities
- Adding creative tabs
- Adding GUIs / screens / menu handlers
- Adding sounds
- Adding potions & brewing recipes
- Adding enchantments
- Adding custom recipes & recipe types
- Adding commands
- Custom packets & networking
- Event listeners
- Energy, fluid & item transfer API
- Tag, Text, Style, NBT, Component
- Timers & task schedulers

### Required MOD
- MOD Loader
  - [FabricMC](https://fabricmc.net/)
  - [Minecraft Forge](https://files.minecraftforge.net/)
  - [NeoForge](https://neoforged.net/)
- [Architectury API](https://www.curseforge.com/minecraft/mc-mods/architectury-api) ([CurseForge](https://www.curseforge.com/minecraft/mc-mods/architectury-api) | [Modrinth](https://modrinth.com/mod/architectury-api))

### Download
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/mcpitanlibarch)
- [Modrinth](https://modrinth.com/mod/mcpitanlibarch)

### How to use
Write as follows in `build.gradle`.
```groovy
repositories {
    maven {
        url = "https://maven.pitan76.net/"
    }
}

dependencies {
    // FabricMC
    modImplementation "net.pitan76:mcpitanlib-fabric${rootProject.mcpitanlib_version}"
    
    // Minecraft Forge
    //modImplementation "net.pitan76:mcpitanlib-forge${rootProject.mcpitanlib_version}"
    
    // NeoForge
    //modImplementation "net.pitan76:mcpitanlib-neoforge${rootProject.mcpitanlib_version}"
}
```

Write as follows in `gradle.properties`.
```properties
# Example: mcpitanlib_version=+1.18.2:3.2.4
mcpitanlib_version=+x.x.x:x.x.x
```

Please check the version at [GitHub Packages](https://github.com/PTOM76/maven/packages/?q=net.pitan76.mcpitanlib) (for versions 3.3.3 and later) or [maven.pitan76.net](https://maven.pitan76.net/net/pitan76/) (for versions before 3.3.3).

For MOD development using MCPitanLib, refer to "common/src/main/java/net/pitan76/mcpitanlib/test/ExampleMod.java".

## Note
### Auto Cherry-Pick (GitHub Actions)
コミットメッセージに [cp] を含むと、Actionsで自動的にCherry-Pickを行います。 <br />
そのコミットしたブランチからそれより下位のバージョンのブランチにCherry-Pickし、コンフリを起こした場合はそこで停止し、PRを作成します。<br />
今のところ、そのPRは使えないので閉じる必要があります。(今後はissueにする予定です。)

