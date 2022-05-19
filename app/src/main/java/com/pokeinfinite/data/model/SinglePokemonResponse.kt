package com.pokeinfinite.data.model

import com.google.gson.annotations.SerializedName

data class SinglePokemonResponse(
    @SerializedName("abilities") val abilities: List<Ability?>?,
    @SerializedName("base_experience") val baseExperience: Int?,
    @SerializedName("forms") val forms: List<Form?>?,
    @SerializedName("game_indices") val gameIndices: List<GameIndice?>?,
    @SerializedName("height") val height: Int?,
    @SerializedName("held_items") val heldItems: List<HeldItem?>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("is_default") val isDefault: Boolean?,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String?,
    @SerializedName("moves") val moves: List<Move?>?,
    @SerializedName("name") val name: String?,
    @SerializedName("order") val order: Int?,
    @SerializedName("past_types") val pastTypes: List<PastType?>?,
    @SerializedName("species") val species: Species?,
    @SerializedName("sprites") val sprites: Sprites?,
    @SerializedName("stats") val stats: List<Stat?>?,
    @SerializedName("types") val types: List<Type?>?,
    @SerializedName("weight") val weight: Int?
) {
    data class Ability(
        @SerializedName("ability") val ability: Ability?,
        @SerializedName("is_hidden") val isHidden: Boolean?,
        @SerializedName("slot") val slot: Int?
    ) {
        data class Ability(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )
    }

    data class Form(
        @SerializedName("name") val name: String?,
        @SerializedName("url") val url: String?
    )

    data class GameIndice(
        @SerializedName("game_index") val gameIndex: Int?,
        @SerializedName("version") val version: Version?
    ) {
        data class Version(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )
    }

    data class HeldItem(
        @SerializedName("item") val item: Item?,
        @SerializedName("version_details") val versionDetails: List<VersionDetail?>?
    ) {
        data class Item(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )

        data class VersionDetail(
            @SerializedName("rarity") val rarity: Int?,
            @SerializedName("version") val version: Version?
        ) {
            data class Version(
                @SerializedName("name") val name: String?,
                @SerializedName("url") val url: String?
            )
        }
    }

    data class Move(
        @SerializedName("move") val move: Move?,
        @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail?>?
    ) {
        data class Move(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at") val levelLearnedAt: Int?,
            @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod?,
            @SerializedName("version_group") val versionGroup: VersionGroup?
        ) {
            data class MoveLearnMethod(
                @SerializedName("name") val name: String?,
                @SerializedName("url") val url: String?
            )

            data class VersionGroup(
                @SerializedName("name") val name: String?,
                @SerializedName("url") val url: String?
            )
        }
    }

    data class PastType(
        @SerializedName("generation") val generation: Generation?,
        @SerializedName("types") val types: List<Type?>?
    ) {
        data class Generation(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )

        data class Type(
            @SerializedName("slot") val slot: Int?,
            @SerializedName("type") val type: Type?
        ) {
            data class Type(
                @SerializedName("name") val name: String?,
                @SerializedName("url") val url: String?
            )
        }
    }

    data class Species(
        @SerializedName("name") val name: String?,
        @SerializedName("url") val url: String?
    )

    data class Sprites(
        @SerializedName("back_default") val backDefault: String?,
        @SerializedName("back_female") val backFemale: Any?,
        @SerializedName("back_shiny") val backShiny: String?,
        @SerializedName("back_shiny_female") val backShinyFemale: Any?,
        @SerializedName("front_default") val frontDefault: String?,
        @SerializedName("front_female") val frontFemale: Any?,
        @SerializedName("front_shiny") val frontShiny: String?,
        @SerializedName("front_shiny_female") val frontShinyFemale: Any?,
        @SerializedName("other") val other: Other?,
        @SerializedName("versions") val versions: Versions?
    ) {
        data class Other(
            @SerializedName("dream_world") val dreamWorld: DreamWorld?,
            @SerializedName("home") val home: Home?,
            @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?
        ) {
            data class DreamWorld(
                @SerializedName("front_default") val frontDefault: String?,
                @SerializedName("front_female") val frontFemale: Any?
            )

            data class Home(
                @SerializedName("front_default") val frontDefault: String?,
                @SerializedName("front_female") val frontFemale: Any?,
                @SerializedName("front_shiny") val frontShiny: String?,
                @SerializedName("front_shiny_female") val frontShinyFemale: Any?
            )

            data class OfficialArtwork(
                @SerializedName("front_default") val frontDefault: String?
            )
        }

        data class Versions(
            @SerializedName("generation-i") val generationI: GenerationI?,
            @SerializedName("generation-ii") val generationIi: GenerationIi?,
            @SerializedName("generation-iii") val generationIii: GenerationIii?,
            @SerializedName("generation-iv") val generationIv: GenerationIv?,
            @SerializedName("generation-v") val generationV: GenerationV?,
            @SerializedName("generation-vi") val generationVi: GenerationVi?,
            @SerializedName("generation-vii") val generationVii: GenerationVii?,
            @SerializedName("generation-viii") val generationViii: GenerationViii?
        ) {
            data class GenerationI(
                @SerializedName("red-blue") val redBlue: RedBlue?,
                @SerializedName("yellow") val yellow: Yellow?
            ) {
                data class RedBlue(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_gray") val backGray: String?,
                    @SerializedName("back_transparent") val backTransparent: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_gray") val frontGray: String?,
                    @SerializedName("front_transparent") val frontTransparent: String?
                )

                data class Yellow(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_gray") val backGray: String?,
                    @SerializedName("back_transparent") val backTransparent: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_gray") val frontGray: String?,
                    @SerializedName("front_transparent") val frontTransparent: String?
                )
            }

            data class GenerationIi(
                @SerializedName("crystal") val crystal: Crystal?,
                @SerializedName("gold") val gold: Gold?,
                @SerializedName("silver") val silver: Silver?
            ) {
                data class Crystal(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("back_shiny_transparent") val backShinyTransparent: String?,
                    @SerializedName("back_transparent") val backTransparent: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_transparent") val frontShinyTransparent: String?,
                    @SerializedName("front_transparent") val frontTransparent: String?
                )

                data class Gold(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_transparent") val frontTransparent: String?
                )

                data class Silver(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_transparent") val frontTransparent: String?
                )
            }

            data class GenerationIii(
                @SerializedName("emerald") val emerald: Emerald?,
                @SerializedName("firered-leafgreen") val fireredLeafgreen: FireredLeafgreen?,
                @SerializedName("ruby-sapphire") val rubySapphire: RubySapphire?
            ) {
                data class Emerald(
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_shiny") val frontShiny: String?
                )

                data class FireredLeafgreen(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_shiny") val frontShiny: String?
                )

                data class RubySapphire(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_shiny") val frontShiny: String?
                )
            }

            data class GenerationIv(
                @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl?,
                @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver?,
                @SerializedName("platinum") val platinum: Platinum?
            ) {
                data class DiamondPearl(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_female") val backFemale: Any?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                )

                data class HeartgoldSoulsilver(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_female") val backFemale: Any?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                )

                data class Platinum(
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_female") val backFemale: Any?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                )
            }

            data class GenerationV(
                @SerializedName("black-white") val blackWhite: BlackWhite?
            ) {
                data class BlackWhite(
                    @SerializedName("animated") val animated: Animated?,
                    @SerializedName("back_default") val backDefault: String?,
                    @SerializedName("back_female") val backFemale: Any?,
                    @SerializedName("back_shiny") val backShiny: String?,
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?,
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                ) {
                    data class Animated(
                        @SerializedName("back_default") val backDefault: String?,
                        @SerializedName("back_female") val backFemale: Any?,
                        @SerializedName("back_shiny") val backShiny: String?,
                        @SerializedName("back_shiny_female") val backShinyFemale: Any?,
                        @SerializedName("front_default") val frontDefault: String?,
                        @SerializedName("front_female") val frontFemale: Any?,
                        @SerializedName("front_shiny") val frontShiny: String?,
                        @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                    )
                }
            }

            data class GenerationVi(
                @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire?,
                @SerializedName("x-y") val xY: XY?
            ) {
                data class OmegarubyAlphasapphire(
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                )

                data class XY(
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                )
            }

            data class GenerationVii(
                @SerializedName("icons") val icons: Icons?,
                @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon?
            ) {
                data class Icons(
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?
                )

                data class UltraSunUltraMoon(
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?,
                    @SerializedName("front_shiny") val frontShiny: String?,
                    @SerializedName("front_shiny_female") val frontShinyFemale: Any?
                )
            }

            data class GenerationViii(
                @SerializedName("icons") val icons: Icons?
            ) {
                data class Icons(
                    @SerializedName("front_default") val frontDefault: String?,
                    @SerializedName("front_female") val frontFemale: Any?
                )
            }
        }
    }

    data class Stat(
        @SerializedName("base_stat") val baseStat: Int?,
        @SerializedName("effort") val effort: Int?,
        @SerializedName("stat") val stat: Stat?
    ) {
        data class Stat(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )
    }

    data class Type(
        @SerializedName("slot") val slot: Int?,
        @SerializedName("type") val type: Type?
    ) {
        data class Type(
            @SerializedName("name") val name: String?,
            @SerializedName("url") val url: String?
        )
    }
}
