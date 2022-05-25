package com.pokeinfinite.data.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(

	@field:SerializedName("evolution_chain")
	val evolutionChain: EvolutionChain? = null,

	@field:SerializedName("genera")
	val genera: List<GeneraItem?>? = null,

	@field:SerializedName("habitat")
	val habitat: Habitat? = null,

	@field:SerializedName("color")
	val color: Color? = null,

	@field:SerializedName("egg_groups")
	val eggGroups: List<EggGroupsItem?>? = null,

	@field:SerializedName("capture_rate")
	val captureRate: Int? = null,

	@field:SerializedName("pokedex_numbers")
	val pokedexNumbers: List<PokedexNumbersItem?>? = null,

	@field:SerializedName("forms_switchable")
	val formsSwitchable: Boolean? = null,

	@field:SerializedName("growth_rate")
	val growthRate: GrowthRate? = null,

	@field:SerializedName("flavor_text_entries")
	val flavorTextEntries: List<FlavorTextEntriesItem>,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("is_baby")
	val isBaby: Boolean? = null,

	@field:SerializedName("order")
	val order: Int? = null,

	@field:SerializedName("is_legendary")
	val isLegendary: Boolean? = null,

	@field:SerializedName("pal_park_encounters")
	val palParkEncounters: List<PalParkEncountersItem?>? = null,

	@field:SerializedName("shape")
	val shape: Shape? = null,

	@field:SerializedName("is_mythical")
	val isMythical: Boolean? = null,

	@field:SerializedName("base_happiness")
	val baseHappiness: Int? = null,

	@field:SerializedName("names")
	val names: List<NamesItem?>? = null,

	@field:SerializedName("varieties")
	val varieties: List<VarietiesItem?>? = null,

	@field:SerializedName("gender_rate")
	val genderRate: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("has_gender_differences")
	val hasGenderDifferences: Boolean? = null,

	@field:SerializedName("hatch_counter")
	val hatchCounter: Int? = null,

	@field:SerializedName("form_descriptions")
	val formDescriptions: List<Any?>? = null,

	@field:SerializedName("evolves_from_species")
	val evolvesFromSpecies: Any? = null
)

data class Pokemon(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Language(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Pokedex(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class PalParkEncountersItem(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("base_score")
	val baseScore: Int? = null,

	@field:SerializedName("rate")
	val rate: Int? = null
)

data class NamesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("language")
	val language: Language? = null
)

data class VarietiesItem(

	@field:SerializedName("pokemon")
	val pokemon: Pokemon? = null,

	@field:SerializedName("is_default")
	val isDefault: Boolean? = null
)

data class GeneraItem(

	@field:SerializedName("genus")
	val genus: String? = null,

	@field:SerializedName("language")
	val language: Language? = null
)

data class Shape(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class EggGroupsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class PokedexNumbersItem(

	@field:SerializedName("entry_number")
	val entryNumber: Int? = null,

	@field:SerializedName("pokedex")
	val pokedex: Pokedex? = null
)

data class EvolutionChain(

	@field:SerializedName("url")
	val url: String? = null
)

data class Habitat(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Color(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Area(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class GrowthRate(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class FlavorTextEntriesItem(

	@field:SerializedName("language")
	val language: Language? = null,

	@field:SerializedName("version")
	val version: Version? = null,

	@field:SerializedName("flavor_text")
	val flavorText: String
)
