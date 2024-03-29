package com.lollipop.spider.service.impl;

import com.lollipop.spider.api.HandBookApi;
import com.lollipop.spider.api.WikiApi;
import com.lollipop.spider.pokedex.encounter.Gen8EncounterResponse;
import com.lollipop.spider.pokedex.pokemon.EncounterResponse;
import com.lollipop.spider.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IPokemonService implements PokemonService {
    @Override
    public EncounterResponse getPokemonEncounterList(String pokeName) {
        return WikiApi.get(pokeName, new EncounterResponse.EncounterHandler());
    }

    @Override
    public Gen8EncounterResponse getGen8EncounterList(int page) {
        return HandBookApi.gen8Encounter(page, new Gen8EncounterResponse.EncounterHandler());
    }
}
