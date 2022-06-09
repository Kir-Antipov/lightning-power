![Logo](media/logo.png)

# Lightning Power
[![GitHub tag](https://img.shields.io/github/v/tag/Kir-Antipov/lightning-power.svg?cacheSeconds=3600&sort=date)](https://github.com/Kir-Antipov/lightning-power/releases/latest)
[![GitHub build status](https://img.shields.io/github/workflow/status/Kir-Antipov/lightning-power/build-artifacts/1.17.x/dev?cacheSeconds=3600)](https://github.com/Kir-Antipov/lightning-power/actions/workflows/build-artifacts.yml)
[![Modrinth](https://img.shields.io/badge/dynamic/json?color=5da545&label=Modrinth&query=title&url=https://api.modrinth.com/api/v1/mod/lightning-power&style=flat&cacheSeconds=3600&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMSAxMSIgd2lkdGg9IjE0LjY2NyIgaGVpZ2h0PSIxNC42NjciICB4bWxuczp2PSJodHRwczovL3ZlY3RhLmlvL25hbm8iPjxkZWZzPjxjbGlwUGF0aCBpZD0iQSI+PHBhdGggZD0iTTAgMGgxMXYxMUgweiIvPjwvY2xpcFBhdGg+PC9kZWZzPjxnIGNsaXAtcGF0aD0idXJsKCNBKSI+PHBhdGggZD0iTTEuMzA5IDcuODU3YTQuNjQgNC42NCAwIDAgMS0uNDYxLTEuMDYzSDBDLjU5MSA5LjIwNiAyLjc5NiAxMSA1LjQyMiAxMWMxLjk4MSAwIDMuNzIyLTEuMDIgNC43MTEtMi41NTZoMGwtLjc1LS4zNDVjLS44NTQgMS4yNjEtMi4zMSAyLjA5Mi0zLjk2MSAyLjA5MmE0Ljc4IDQuNzggMCAwIDEtMy4wMDUtMS4wNTVsMS44MDktMS40NzQuOTg0Ljg0NyAxLjkwNS0xLjAwM0w4LjE3NCA1LjgybC0uMzg0LS43ODYtMS4xMTYuNjM1LS41MTYuNjk0LS42MjYuMjM2LS44NzMtLjM4N2gwbC0uMjEzLS45MS4zNTUtLjU2Ljc4Ny0uMzcuODQ1LS45NTktLjcwMi0uNTEtMS44NzQuNzEzLTEuMzYyIDEuNjUxLjY0NSAxLjA5OC0xLjgzMSAxLjQ5MnptOS42MTQtMS40NEE1LjQ0IDUuNDQgMCAwIDAgMTEgNS41QzExIDIuNDY0IDguNTAxIDAgNS40MjIgMCAyLjc5NiAwIC41OTEgMS43OTQgMCA0LjIwNmguODQ4QzEuNDE5IDIuMjQ1IDMuMjUyLjgwOSA1LjQyMi44MDljMi42MjYgMCA0Ljc1OCAyLjEwMiA0Ljc1OCA0LjY5MSAwIC4xOS0uMDEyLjM3Ni0uMDM0LjU2bC43NzcuMzU3aDB6IiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGZpbGw9IiM1ZGE0MjYiLz48L2c+PC9zdmc+)](https://modrinth.com/mod/lightning-power)
[![CurseForge](https://img.shields.io/badge/dynamic/json?color=%23f16436&label=CurseForge&query=title&url=https%3A%2F%2Fapi.cfwidget.com%2F631812)](https://www.curseforge.com/minecraft/mc-mods/lightning-power)
[![GitHub license](https://img.shields.io/github/license/Kir-Antipov/lightning-power.svg?cacheSeconds=36000)](https://github.com/Kir-Antipov/lightning-power#readme)

An utility mod that turns lightning rods into a power source acceptable by machines from popular tech mods (e.g., [Tech Reborn](https://www.curseforge.com/minecraft/mc-mods/techreborn), [Industrial Revolution](https://www.curseforge.com/minecraft/mc-mods/industrial-revolution), [Modern Industrialization](https://www.curseforge.com/minecraft/mc-mods/modern-industrialization), etc.)

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
- Minecraft `1.17.x`
- Fabric Loader `>=0.11.3`

You can download the mod from:

- [GitHub Releases](https://github.com/Kir-Antipov/lightning-power/releases/latest) <sup><sub>(recommended)</sub></sup>
- [Modrinth](https://modrinth.com/mod/lightning-power)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/lightning-power)
- [GitHub Actions](https://github.com/Kir-Antipov/lightning-power/actions/workflows/build-artifacts.yml) *(these builds may be unstable, but they represent the actual state of the development)*

## Building from sources

Requirements:
- JDK `16`

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
