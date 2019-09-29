package com.lollipop.spider.service;

import com.lollipop.spider.pokedex.pokemon.EncounterResponse;

public interface PokemonService {
    EncounterResponse getPokemonEncounterList(String pokeName);
}
