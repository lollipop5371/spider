package com.lollipop.spider.service;

import com.lollipop.spider.pokedex.encounter.Gen8EncounterResponse;
import com.lollipop.spider.pokedex.pokemon.EncounterResponse;

public interface PokemonService {
    EncounterResponse getPokemonEncounterList(String pokeName);

    Gen8EncounterResponse getGen8EncounterList(int page);
}
