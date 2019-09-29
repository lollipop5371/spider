package com.lollipop.spider.service.impl;

import com.lollipop.spider.api.PokemonApi;
import com.lollipop.spider.pokedex.pokemon.EncounterResponse;
import com.lollipop.spider.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IPokemonService implements PokemonService {
    @Override
    public EncounterResponse getPokemonEncounterList(String pokeName) {
        return PokemonApi.get(pokeName, new EncounterResponse.EncounterHandler());
    }
}
