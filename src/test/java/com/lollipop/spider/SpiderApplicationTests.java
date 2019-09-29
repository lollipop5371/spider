package com.lollipop.spider;

import com.alibaba.fastjson.JSON;
import com.lollipop.spider.pokedex.pokemon.EncounterResponse;
import com.lollipop.spider.service.PokemonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {
    @Autowired
    PokemonService pokemonService;


    @Test
    public void contextLoads() {
        EncounterResponse response = pokemonService.getPokemonEncounterList("拉普拉斯");
        if (response != null && response.getCode() == 0) {
            System.out.println(JSON.toJSONString(response.getData(), true));
        }
    }

}
