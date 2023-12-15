![Logo](media/logo.png)

# Lightning Power

[![GitHub Build Status](https://img.shields.io/github/actions/workflow/status/Kir-Antipov/lightning-power/build-artifacts.yml?style=flat&logo=github&cacheSeconds=3600)](https://github.com/Kir-Antipov/lightning-power/actions/workflows/build-artifacts.yml)
[![Version](https://img.shields.io/github/v/release/Kir-Antipov/lightning-power?sort=date&style=flat&label=version&cacheSeconds=3600)](https://github.com/Kir-Antipov/lightning-power/releases/latest)
[![Modrinth](https://img.shields.io/badge/dynamic/json?color=00AF5C&label=Modrinth&query=title&url=https://api.modrinth.com/v2/project/lightning-power&style=flat&cacheSeconds=3600&logo=modrinth)](https://modrinth.com/mod/lightning-power)
[![CurseForge](https://img.shields.io/badge/dynamic/json?color=F16436&label=CurseForge&query=title&url=https://api.cfwidget.com/631812&cacheSeconds=3600&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/lightning-power)
[![License](https://img.shields.io/github/license/Kir-Antipov/lightning-power?style=flat&cacheSeconds=36000)](https://github.com/Kir-Antipov/lightning-power/blob/HEAD/LICENSE.md)

A utility mod that turns lightning rods into a power source acceptable by machines from popular tech mods, e.g., [Tech Reborn](https://www.curseforge.com/minecraft/mc-mods/techreborn).

----

## Config

If you have [Cloth Config](https://www.curseforge.com/minecraft/mc-mods/cloth-config) installed, you can customize the behavior of the mod. A config is usually located at `./config/lightning_power.json` and by default looks like this:

```json
{
  "accurateRedstonePower": false,
  "emittedEnergyAmountPerTick": 4096
}
```

| Name | Description | Default value |
| ---- | ----------- | ------------- |
| `accurateRedstonePower` | Redstone signal emitted by a lightning rod will gradually decrease from 15 to 0 | `false` |
| `emittedEnergyAmountPerTick` | Emitted energy amount (per tick) | `4096` |

You can edit any of these values directly in the config file or via [ModMenu](https://github.com/TerraformersMC/ModMenu).

----

## Installation

Requirements:
- Minecraft `1.19.x`
- Fabric Loader `>=0.14.0`

You can download the mod from:

- [GitHub Releases](https://github.com/Kir-Antipov/lightning-power/releases/latest) <sup><sub>(recommended)</sub></sup>
- [Modrinth](https://modrinth.com/mod/lightning-power)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/lightning-power)
- [GitHub Actions](https://github.com/Kir-Antipov/lightning-power/actions/workflows/build-artifacts.yml) *(these builds may be unstable, but they represent the actual state of the development)*

## Building from sources

Requirements:
- JDK `17`

### Linux/MacOS

```cmd
git clone https://github.com/Kir-Antipov/lightning-power.git
cd lightning-power

chmod +x ./gradlew
./gradlew build
cd build/libs
```
### Windows

```cmd
git clone https://github.com/Kir-Antipov/lightning-power.git
cd lightning-power

gradlew build
cd build/libs
```
